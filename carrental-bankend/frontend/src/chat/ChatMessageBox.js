import React, { Component } from 'react';

import Menu from '../menu-app-bar/MenuAppBar'
import Aside from '../aside/Aside'
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';
// Styling
import './ChatMessageBox.css';
// Default user image
import userImage from './userImage.png';
import TextField from '@material-ui/core/TextField';
// import backToTop from './backToTop.png';
import moment from "moment";
export class Chat extends React.Component {

  constructor(props) {
    super(props);
    this.state =
    {
      username: '',
      id: '',
      channelConnected: false,
      chatMessage: '',
      roomNotification: [],
      broadcastMessage: [],
      error: '',
      bottom: false,
      curTime: '',
      openNotifications: false,
      bellRing: false,
      users: [],
      selectedtoken: '',
      selectedusername: '',
      stompClient: null,
      vechileId: null
    };
  }

  connect = () => {

    const Stomp = require('stompjs')

    var SockJS = require('sockjs-client')

    SockJS = new SockJS('/ws')

    this.setState({ stompClient: Stomp.over(SockJS) });

  }

  onConnected = () => {
    // this.connect();
    this.setState({
      channelConnected: true
    })

    // Subscribing to the public topic
    if (this.state.stompClient.connected) {
      this.state.stompClient.subscribe(`/user/${this.state.username}/reply`, this.onMessageReceived);
      console.log("connected successfully");
    } else {
      this.sleep(5000).then(r => {
        this.onConnected();
      })
    }


    // Registering user to server as a public chat user
    // stompClient.send("/app/addUser", {}, JSON.stringify({ sender: this.state.username, type: 'JOIN' }))

  }

  sleep = (milliseconds) => {
    return new Promise(resolve => setTimeout(resolve, milliseconds))
  }

  sendMessage = () => {

    if (this.state.stompClient) {
      var chatMessage = {
        sender: this.state.username,
        content: this.state.chatMessage,
        type: 'CHAT',
        receiver: this.state.selectedusername,
        vechileId: this.state.vechileId,
        dateTime: moment(new Date()).format("DD/MM/YYYY hh:mm:ss")
      };
      this.setState({ broadcastMessage: [...this.state.broadcastMessage, chatMessage], chatMessage: '' })
      // send public message
      this.state.stompClient.send("/app/sendPrivateMessage", {}, JSON.stringify(chatMessage));
    }
  }

  onMessageReceived = (payload) => {
    var message = JSON.parse(payload.body);

    this.setState({ broadcastMessage: [...this.state.broadcastMessage, message] })

  }

  updateUserOnMenu = (user) => {
    this.setState({ selectedusername: user.username, selectedName: user.name }, () => {
      this.state.stompClient.connect({}, this.onConnected, this.onError);
      this.fetchChat(user.id);
    });
  }
  onError = (error) => {
    this.setState({
      error: 'Could not connect you to the Chat Room Server. Please refresh this page and try again!'
    })
  }

  fetchHostory = () => {
    alert('History Not Available!\nIt is Not Yet Implemented!');
  }

  scrollToBottom = () => {
    var object = this.refs.messageBox;
    if (object)
      object.scrollTop = object.scrollHeight;
  }


  componentDidUpdate() {
    if (this.state.error) {
      throw new Error('Unable to connect to chat room server.');
    }
    else {
      this.scrollToBottom();
    }
  }

  componentDidMount() {
    this.setState({
      curTime: new Date().toLocaleString()
    })
    this.connect();
    const query = new URLSearchParams(this.props.location.search);
    const vechileId = query.get('vechileId');
    const type = query.get('type')

    let username = sessionStorage.getItem("username");
    let id = sessionStorage.getItem("user_id");
    if (id && id != "null") {
      if (type == "renter") {
        fetch("http://localhost:8080/CarRental/carlist/owner/" + vechileId)
          .then((response) => response.json())
          .then((data) => {
            this.setState({ users: data, channelConnected: true });
            if (data != null && data.length > 0) {
              
              this.setState({ selectedusername: data[0].username, selectedName: data[0].name, username, id, vechileId }, () => {
                this.state.stompClient.connect({}, this.onConnected, this.onError);
                const toUserId = data[0].id;
                this.fetchChat(toUserId, vechileId, id);
              });
            }
          });
      } else {
        fetch("http://localhost:8080/CarRental/carlist/customer-detail/" + vechileId + "?login=" + username)
          .then((response) => response.json())
          .then((data) => {
            this.setState({ users: data, channelConnected: true });
            if (data != null && data.length > 0) {
              
              this.setState({ selectedusername: data[0].username, selectedName: data[0].name, username, id, vechileId }, () => {
                this.state.stompClient.connect({}, this.onConnected, this.onError);
                const toUserId = data[0].id;
                this.fetchChat(toUserId);
              });
            }
          });
      }
    }
  }

  fetchChat = (toUserId) => {
    fetch(`http://localhost:8080/CarRental/chat/${this.state.vechileId}/${this.state.id}/${toUserId}?page=0&number=1000`)
      .then((response) => response.json())
      .then((data) => {
        this.setState({ broadcastMessage: data.content });
      });
  }
  render() {

    return (
      <div>
        {this.state.channelConnected ?
          (
            <div>

              <Menu roomNotification={this.state.roomNotification}
                bellRing={this.state.bellRing}
                openNotifications={this.state.openNotifications}
                username={this.state.username}
                broadcastMessage={this.state.broadcastMessage}
                selectedName={this.state.selectedName}
                selectedusername={this.state.selectedusername}
              />

              <Paper elevation={5}>
                <Aside roomNotification={this.state.roomNotification}
                  openNotifications={this.state.openNotifications}
                  username={this.state.username}
                  broadcastMessage={this.state.broadcastMessage}
                  users={this.state.users}
                  updateUserOnMenu={this.updateUserOnMenu} />

              </Paper>
              <Paper elevation={5}>
                <ul id="chat" ref="messageBox">
                  {/* {this.state.broadcastMessage.length ?
                  [<div id="history"><div id="old" onClick={this.fetchHostory}>Older</div><hr /><div id="today">Today</div></div>] : ""} */}
                  {this.state.broadcastMessage.map((msg, i) =>
                    this.state.username === msg.receiver ?
                      <li className="you" key={i}>
                        <div className="entete">
                          <h2><img src={userImage} alt="Default-User" className="avatar" />
                            <span> </span>
                            <span className="sender"> {msg.senderName}</span></h2>
                          <span> </span>
                          {/* <span className="status green"></span> */}
                        </div>
                        <div className="triangle"></div>
                        <div className="message">
                          {msg.content}
                        </div>
                        <div><h3>{msg.dateTime}</h3></div>
                      </li>
                      :
                      <li className="others">
                        <div className="message">
                          {msg.content}
                        </div>
                        <div><h3>{msg.dateTime}</h3></div>
                      </li>
                  )}
                </ul>
                <div>
                  <div className="footerComponent">
                    <TextField
                      id="msg"
                      label="Type your message here..."
                      placeholder="Press enter to send message"
                      onChange={(event) => this.setState({ chatMessage: event.target.value })}
                      margin="normal"
                      value={this.state.chatMessage}
                      onKeyPress={event => {
                        if (event.key === 'Enter') {
                          this.sendMessage();
                        }
                      }}
                    />
                  </div>
                </div>


              </Paper>
            </div>


          ) : (
            ""

          )
        }
      </div>
    )
  }

}
