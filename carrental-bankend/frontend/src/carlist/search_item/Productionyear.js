import * as React from "react";
import 'jquery/src/jquery.js';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';

export class Productionyear extends React.Component {

  constructor() {
  	super();
  }

	render () {
		return (
      <div className="form-group">
        <label>Year of production :</label>
        <div className="input-group">
          <div className="input-group-prepend">
            <span className="input-group-text">From</span>
          </div>
          <input key="productionYearFrom" name="productionYearFrom" id="productionYearFrom" type="text" className="form-control" onChange={this.props.handleInputChange}/>

          <div className="input-group-prepend">
            <span className="input-group-text">To</span>
          </div>
          <input key="productionYearTo" name="productionYearTo" id="productionYearTo" type="text" className="form-control" onChange={this.props.handleInputChange}/>
        </div>
      </div>
		)
	}

}
