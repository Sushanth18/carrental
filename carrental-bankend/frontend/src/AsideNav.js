import * as React from "react";
import "jquery/src/jquery.js";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.js";
import { Link } from "react-router-dom";
import "./style.scss";

export class AsideNav extends React.Component {
  constructor() {
    super();
    this.state = {
      isAuthenticated: false,
    };
  }

  componentDidMount() {
    const url = `http://localhost:8080/CarRental/userdata/isauthenticated?username=${
      sessionStorage.getItem("username") &&
      sessionStorage.getItem("username") != "null"
        ? sessionStorage.getItem("username")
        : ""
    }`;
    fetch(url).then((response) => {
      response.json().then((json) => {
        this.setState({ isAuthenticated: json.isAuthenticated });
      });
    });
  }
  logout = () => {
    sessionStorage.clear();
    this.props.props.history.push({
      pathname: "/CarRental/",
      state: {},
    });
    window.location.reload();
  };

  render() {
    const isAuthenticated = this.state.isAuthenticated;
    return (
      <nav
        className="navbar navbar-expand navbar-dark loginNav"
        style={{ height: 30 }}
      >
        <ul className="navbar-nav pr-3 ml-auto">
          <li className="nav-item p-2 ">
            {isAuthenticated ? (
              <a href="/CarRental/profile" className="linkstyle nav-link">
                <small>Profile</small>
              </a>
            ) : (
              <a href="/CarRental/login" className="linkstyle nav-link">
                <small>Log in</small>
              </a>
            )}
          </li>
          <li className="nav-item p-2 ">
            {isAuthenticated ? (
              <a onClick={this.logout} className="linkstyle nav-link">
                <small>Log out</small>
              </a>
            ) : (
              <a href="/CarRental/registration" className="linkstyle nav-link">
                <small>Sign up</small>
              </a>
            )}
          </li>
        </ul>
      </nav>
    );
  }
}
