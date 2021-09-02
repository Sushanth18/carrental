import * as React from "react";
import { NavContainer } from "../src/nav/NavContainer";
import 'jquery/src/jquery.js';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';
import './static/css/main.css';

export class MainContent extends React.Component {

	render () {
		return (
			<div className="col-md-9 pl-0 pr-3" style= {{margin : "auto", marginTop : "10%"}}>
				<div className="card">
					<div className="card-header">
						<label className ="text-center" style ={{marginLeft : "50%"}}>Welcome</label>
						<NavContainer/>
					</div>
				</div>
			</div>
		)
	}

}
