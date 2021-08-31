import * as React from "react";
import "jquery/src/jquery.js";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.js";
import "../static/css/main.css";
import { UserData } from "./UserData.js";
import { SideNav } from "./SideNav.js";
// import { ProSidebar, Menu, MenuItem, SubMenu } from "react-pro-sidebar";
// import "react-pro-sidebar/dist/css/styles.css";

export class NavContainer extends React.Component {
  render() {
    return (
      <div className="col-12 ">
        <div id="accordion">
          <UserData />
          <SideNav />
          {/* <ProSidebar>
            <Menu iconShape="square">
              <MenuItem>Dashboard</MenuItem>
              <SubMenu title="Components">
                <MenuItem>Component 1</MenuItem>
                <MenuItem>Component 2</MenuItem>
              </SubMenu>
            </Menu>
          </ProSidebar> */}
        </div>
      </div>
    );
  }
}
