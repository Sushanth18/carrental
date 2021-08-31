import * as React from "react";
import 'jquery/src/jquery.js';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';
import '../static/css/main.css';
import {HeaderContainer} from '../tools/HeaderContainer.js'

export class Settings extends React.Component {

  constructor(){
    super();

    this.state={
      user_login:"",
      user_name:"",
      user_surname:"",
      user_email:"",
      user_phone:"",
      user_pesel:"",
      user_birth_date:"",
      user_password:"",
      loaded:false
    };
  }

  componentDidMount(){
    const url=`http://localhost:8080/CarRental/userdata/username?username=${sessionStorage.getItem("username")&&sessionStorage.getItem("username")!="null"?sessionStorage.getItem("username"):""}`;
    fetch(url)
    .then(response=>{
      response.json().then(json=>{
        this.setState({
          user_login:json.username,
          loaded:true
        });
      });
    });
  }




  createUserWrapper = () => {
    var item = {};
    item["id"] = this.state.user_id=="" ? null : this.state.user_id;
    item["login"] = this.state.user_login=="" ? null : this.state.user_login;
    item["name"] = this.state.user_name=="" ? null : this.state.user_name;
    item["surname"] = this.state.user_surname=="" ? null : this.state.user_surname;
    item["email"] = this.state.user_email=="" ? null : this.state.user_email;
    item["phone"] = this.state.user_phone=="" ? null : this.state.user_phone;
    item["pesel"] = this.state.user_pesel=="" ? null : this.state.user_pesel;
    item["birthDate"] = this.state.user_birth_date=="" ? null : this.state.user_birth_date;
    item["password"] = this.state.user_password=="" ? null : this.state.user_password;

    return item;
  }

  handleSubmit = (event) => {
			event.preventDefault();
      const userWrapper = this.createUserWrapper();
      const user_login = this.state.user_login;
      const url = 'http://localhost:8080/CarRental/userlist/'+user_login;

      fetch(url, {
        method: 'PUT',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body:JSON.stringify(userWrapper),
      });

      this.props.history.push({pathname: '/CarRental/profile'});
	}

  getMaxDate = () => {
    var today = new Date();
    var mm = today.getMonth() + 1;
    var dd = today.getDate();

    return [today.getFullYear(),
            (mm>9 ? '' : '0') + mm,
            (dd>9 ? '' : '0') + dd
          ].join('-');
  }

	handleInputChange = (event) => {
		const target = event.target;
		const name = target.name;
		const value = target.value;

		this.setState({
			[name]: value
		});
	}

  renderForm = () => {
    return(
        <div className="card-body">
          <form onSubmit={this.handleSubmit}>
            <div className="form-group">
              <div className="row">
                <label className="ml-5 mt-4 col-md-2">Login:</label>
                <div className="ml-4 mt-3 col-md-3">
                  <input type="text" disabled className="form-control"  name="user_login" value={this.state.user_login} onChange={this.handleInputChange}/>
                </div>
              </div>
            </div>

            <div className="form-group">
              <div className="row">
                <label className="ml-5 mt-4 col-md-2">Name:</label>
                <div className="ml-4 mt-3 col-md-3">
                  <input type="text" className="form-control"  name="user_name" value={this.state.user_name} onChange={this.handleInputChange}/>
                </div>
              </div>
            </div>

            <div className="form-group">
              <div className="row">
                <label className="ml-5 mt-4 col-md-2">Surname:</label>
                <div className="ml-4 mt-3 col-md-3">
                  <input type="text" className="form-control"  name="user_surname" value={this.state.user_surname} onChange={this.handleInputChange}/>
                </div>
              </div>
            </div>

            <div className="form-group">
              <div className="row">
                <label className="ml-5 mt-4 col-md-2">E-mail:</label>
                <div className="ml-4 mt-3 col-md-3">
                  <input type="email" className="form-control"  name="user_email" value={this.state.user_email} onChange={this.handleInputChange}/>
                </div>
              </div>
            </div>

            <div className="form-group">
              <div className="row">
                <label className="ml-5 mt-4 col-md-2">Phone:</label>
                <div className="ml-4 mt-3 col-md-3">
                  <input type="text" className="form-control"  name="user_phone" value={this.state.user_phone} onChange={this.handleInputChange}/>
                </div>
              </div>
            </div>

            <div className="form-group">
              <div className="row">
                <label className="ml-5 mt-4 col-md-2">Pesel:</label>
                <div className="ml-4 mt-3 col-md-3">
                  <input type="number" className="form-control"  name="user_pesel" value={this.state.user_pesel} onChange={this.handleInputChange}/>
                </div>
              </div>
            </div>

            <div className="form-group">
              <div className="row">
                <label className="ml-5 mt-4 col-md-2">Birth date:</label>
                <div className="ml-4 mt-3 col-md-3">
                  <input type="date" name="user_birth_date"  max={this.getMaxDate()} className="form-control" value={this.state.user_birth_date} onChange={this.handleInputChange} />
                </div>
              </div>
            </div>

            <div className="form-group">
              <div className="row">
                <label className="ml-5 mt-4 col-md-2">Password:</label>
                <div className="ml-4 mt-3 col-md-3">
                  <input type="password" className="form-control" name="user_password" onChange={this.handleInputChange}/>
                </div>
              </div>
            </div>

            <div className="ml-4 my-4 text-center">
              <input type="submit" value="Update" className="btn btn-primary"/>
            </div>
          </form>
        </div>
    );
  }

	render () {
    const loaded = this.state.loaded;

		return (
        <div className="col-md-9 pl-0 pr-3 mb-3 text-center">
          <div className="card">
            <HeaderContainer title={"User - edit"}/>
            {loaded ? this.renderForm() : <i className="fa fa-spinner fa-pulse fa-3x fa-fw "></i>}
          </div>
        </div>
		)
	}

}
