import * as React from "react";
import "jquery/src/jquery.js";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.js";
import "../static/css/main.css";
import userImage from "../static/img/user.png";
// import {
//   Dropdown,
//   DropdownToggle,
//   DropdownMenu,
//   DropdownItem,
//   Button,
//   Badge,
//   Card,
//   CardBody,
//   CardHeader,
//   Col,
//   Pagination,
//   PaginationItem,
//   PaginationLink,
//   Row,
//   Table,
//   CardFooter,
//   FormGroup,
//   Input,
//   Label,
// } from "reactstrap";

export class UserData extends React.Component {
  constructor() {
    super();

    this.state = {
      userRoles: null,
      username: null,
    };
  }

  componentDidMount() {
    fetch(`http://localhost:8080/CarRental/userdata/userroles?username=${sessionStorage.getItem("username")&&sessionStorage.getItem("username")!="null"?sessionStorage.getItem("username"):""}`)
      .then((response) => response.json())
      .then((data) => {
        this.setState({
          userRoles: data.userroles,
        });
      })
      .catch((error) => {});

    fetch(`http://localhost:8080/CarRental/userdata/username?username=${sessionStorage.getItem("username")&&sessionStorage.getItem("username")!="null"?sessionStorage.getItem("username"):""}`)
      .then((response) => response.json())
      .then((data) => {
        this.setState({
          username: data.username,
        });
      })
      .catch((error) => {});
  }

  render() {
    const username = this.state.username;

    return (
      <div>
        {/* <Card>
          <div className="card-header text-center">
            <img
              className=""
              src={require("../static/img/car_rental_logo_name.png")}
              alt=""
              style={{ width: "80%" }}
            />
          </div>
          <div className="card-body">
            <div className="row text-center">
              <div className="col-md-3">
                <img
                  src={require("../static/img/user.png")}
                  alt=""
                  className="mr-3 mt-3 rounded-circle"
                  style={{ height: 80 }}
                />
              </div>
              <div className="mx-auto mt-4 text-center my-auto">
                <strong>{username ? username : ""}</strong>
              </div>
            </div>
          </div>
        </Card> */}
        {/* ================================================================================================================================ */}
        <div className="card">
          <div className="card-header text-center">
            <img
              className=""
              src={require("../static/img/car_rental_logo_name.png")}
              alt=""
              style={{ width: "80%" }}
            />
          </div>
          <div className="card-body">
            <div className="row text-center">
              <div className="col-md-3">
                <img
                  src={userImage}
                  // src={require("../static/img/user.png")}
                  alt=""
                  className="mr-3 mt-3 rounded-circle"
                  style={{ height: 80 }}
                />
              </div>
              <div className="col-md-9  my-auto" style={{textAlign :"left"}}>
                <strong>{username ? username : ""}</strong>
              </div>
            </div>
          </div>
        </div>

        {/* ================================================================================================================================ */}
        {/* <div className="animated fadeIn">
          <Row>
            <Col xs="12" lg="12">
              <div className="add-div">
                {" "}
                <Button
                  onClick={(e) => {
                    this.props.history.push("/admin/master/state/create");
                  }}
                  className="btn btn-outline-primary addservice-button"
                >
                  <i className="icon-plus square"></i> Add State
                </Button>
              </div>

              <Card>
                <CardHeader>
                  <i className="fa fa-align-justify"></i> State
                </CardHeader>
                <CardBody>
                  <div>
                    <DataTable
                      tableHeader={tableHeader}
                      tableData={tableData.data}
                      totalCount={tableData.totalCount}
                      pageCount={tableData.pageCount}
                      initializeData={this.initializeData}
                      editDetails={this.editStateDetails}
                      viewButton="false"
                      editButton="true"
                    />
                  </div>
                </CardBody>
              </Card>
            </Col>
          </Row>
        </div> */}
      </div>
    );
  }
}
