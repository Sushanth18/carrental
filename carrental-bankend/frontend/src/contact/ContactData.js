import * as React from "react";
import "jquery/src/jquery.js";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.js";
import "../static/css/main.css";
import "../static/css/contact.css";

export class ContactData extends React.Component {
  render() {
    return (
      <div className="container col-md-3">
        <div id="contacts-data" className="card shadow">
          <div className="card-header bg-secondary text-white mb-2">
            <h3>Contact:</h3>
          </div>
          <div className="card-body" style={{ fontSize: 18 }}>
            <i className="fa fa-map-marker" style={{ color: "red" }}></i>
            <span style={{ marginLeft: "10px" }}>Car Rental</span>
            <br />
            <span style={{ marginLeft: "20px" }}>Mumbai 400 004</span>
            <i className="fa fa-phone">
              <span style={{ marginLeft: "10px" }}>1123 456 789</span>
            </i>
            <i className="fa fa-mobile">
              <span style={{ marginLeft: "10px" }}>987 654 3211</span>
            </i>
            <i className="fa fa-envelope">
              <span style={{ marginLeft: "10px" }}>car.rental@gmail.com</span>
            </i>
          </div>
        </div>
      </div>
    );
  }
}
