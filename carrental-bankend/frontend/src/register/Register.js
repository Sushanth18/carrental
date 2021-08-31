// import * as React from "react";
import React, { Component } from "react";
import "jquery/src/jquery.js";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.js";
import "../static/css/main.css";
import "../static/css/register.css";
import { Link } from "react-router-dom";
import car_rental_logo_name from "../static/img/car_rental_logo_name.png";
// import {
//   Button,
//   Card,
//   CardBody,
//   CardFooter,
//   CardHeader,
//   Col,
//   Row,
// } from "reactstrap";
class Register extends Component {
  handleSubmit = (e) => {
    e.preventDefault();
    console.log(e.target.elements.inputName.value);

    var formData = new FormData()
    const url = "http://localhost:8080/CarRental/registration";
      formData.append('name',e.target.elements.inputName.value);
      formData.append('surname',e.target.elements.inputLastName.value);
      formData.append('login',e.target.elements.inputUsername.value);
      formData.append('password',e.target.elements.inputPassword.value);
      formData.append('passwordMatches',e.target.elements.inputPasswordAgain.value);
      formData.append('email',e.target.elements.inputEmail.value);
     
    
      fetch(url, {
        method: 'POST',
        body: formData
      }).then((response) => {
        response.json().then((json) => {
        console.log("json::",json);
        });
      });
      this.props.history.push({pathname: '/CarRental/profile'});
    // this.props.history.push({
    //   pathname: "/CarRental/login",
    //   state: {},
    // });
  };

  render() {
    return (
      <div
        id="register-page-container"
        className="container my-5 full_body_register"
      >
        <div className="col-md-6 offset-md-3 card-body shadow-lg">
          <form onSubmit={this.handleSubmit}>
            <img
              className="mb-4"
              src={car_rental_logo_name}
              alt=""
              width="100%"
            />
            <h1 className="h3 mb-3 font-weight-normal">Register</h1>

            <div className="form-group">
              <label>Name:</label>
              <input
                type="text"
                className="form-control"
                id="inputName"
                name="inputName"
                placeholder="Name"
                required
                autoFocus
              />
            </div>

            <div className="form-group">
              <label>Last Name:</label>
              <input
                type="text"
                className="form-control"
                id="inputLastName"
                name="inputLastName"
                placeholder="Last Name"
                required
              />
            </div>

            <div className="form-group">
              <label>Username:</label>
              <input
                type="text"
                className="form-control"
                id="inputUsername"
                name="inputUsername"
                placeholder="Username"
                required
              />
            </div>

            <div className="form-group">
              <label>Email:</label>
              <input
                type="email"
                className="form-control"
                id="inputEmail"
                name="inputEmail"
                placeholder="Email"
                required
              />
            </div>

            <div className="form-group">
              <label>Password:</label>
              <input
                type="password"
                className="form-control"
                id="inputPassword"
                name="inputPassword"
                placeholder="Password"
                required
                autocomplete="off"
              />
            </div>

            <div className="form-group">
              <label>Confirm Password:</label>
              <input
                type="password"
                className="form-control"
                id="inputPasswordAgain"
                name="inputPasswordAgain"
                placeholder="Password"
                required
                autocomplete="off"
              />
            </div>
            <div>
              {/* <Row> */}
              <button
                className="btn btn-lg btn-danger btn-block mt-5"
                type="submit"
                style={{ width: "35%" }}
              >
                Register
              </button>
              <button
                onClick={(status) => {
                  this.props.history.push("/CarRental/");
                }}
                style={{ width: "35%" }}
                className="btn btn-lg btn-primary btn-block mt-5"
                type="submit"
              >
                Cancel
              </button>
              {/* </Row> */}
            </div>
          </form>
        </div>
      </div>
    );
  }
}
export default  Register;
