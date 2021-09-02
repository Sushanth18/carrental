import * as React from "react";
import 'jquery/src/jquery.js';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';
import '../static/css/main.css';
import {Link} from 'react-router-dom';

export class HeaderContainer extends React.Component {

	render () {
		return (
      <div className="card-header text-center row" style={{marginTop :"7%"}}>
		  <Link to={"/CarRental/profile/"} style={{marginRight : "25%"}} className=" fa fa-arrow-left linkstyle btn btn-lg btn-primary btn-block col-md-2 ml-5">    Back</Link>
        {this.props.title}
      </div>
		)
	}

}
