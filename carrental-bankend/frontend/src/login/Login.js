import * as React from "react";
import "jquery/src/jquery.js";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.js";
import "../static/css/main.css";
import "../static/css/login.css";
import { Link } from "react-router-dom";
import logo from "../static/img/car_rental_logo_name.png"; // with import

export class Login extends React.Component {
  constructor() {
    super();

    this.state = {
      username: "",
      password: "",
    };
  }

  handleSubmit = (e) => {
    console.log( this.props);
    let { username, password } = this.state;
    e.preventDefault();
    console.log(username, password);

    fetch(
      `http://localhost:8080/CarRental/login?username=${username}&password=${password}`,
      {
        method: "post",
      }
    )
      .then((response) => response.json())
      .then((data) => {
        if (data.login_success) {
          sessionStorage.setItem("username", data.username);
          sessionStorage.setItem("user_id", data.user_id);
          this.props.history.push({
            pathname: "/CarRental/",
            state: {},
          });
        } else window.alert("Invalid Username or Password");
      })
      .catch((error) => {});
  };

  render() {
    return (
      <div id="login-page-container" className="container full_body_login my-5">
        <div className="col-md-4 offset-md-4 card-body shadow-lg">
          <form onSubmit={this.handleSubmit}>
            <img className="mb-4" src={logo} alt="" width="100%" />
            <h1 className="h3 mb-3 font-weight-normal">Sign in</h1>

            <div className="form-group">
              <input
                type="text"
                className="form-control"
                id="inputLogin"
                placeholder="Username"
                required
                autoFocus
                onChange={(e) => {
                  this.setState({ username: e.target.value });
                }}
              />
            </div>

            <div className="form-group">
              <input
                type="password"
                className="form-control"
                id="inputPassword"
                placeholder="Password"
                required
                autocomplete="off"
                onChange={(e) => {
                  this.setState({ password: e.target.value });
                }}
              />
            </div>

            <div className="checkbox mb-3">
              <label>
                <input type="checkbox" value="remember-me" /> Remember me
              </label>
            </div>

            <button className="btn btn-lg btn-primary btn-block" type="submit">
              Sign in
            </button>
          </form>
          <div className="row">
            <p className="mt-3 login_link pl-3">
              <Link to={"/CarRental/"} className="linkstyle">
                Home
              </Link>
            </p>
            <p className="mt-3 ml-auto login_link pr-3">
              <Link to={"/CarRental/registration"} className="linkstyle">
                Register
              </Link>
            </p>
          </div>
        </div>
      </div>
    );
  }
}
