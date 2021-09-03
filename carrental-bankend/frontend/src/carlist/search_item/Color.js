import * as React from "react";
import 'jquery/src/jquery.js';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';

export class Color extends React.Component {

	constructor() {
		super();

		this.state = {
			colorList:null
		};
	}

	componentDidMount(){
		fetch("http://localhost:8080/CarRental/carlistsearch/colorlist")
		.then(response=>{
			console.log("response",response);
			response.json().then(json=>{
				console.log("json",json);
				this.setState({colorList:json});
			});
		});

	};

  optionsList = color=>{
      return <option key={color} name={color} id={color} value={color}>{color}</option>;
  }

	render () {
		const colorList = this.state.colorList;

		return (
      <div className="form-group">
        <label>Color :</label>
        <select  key="color" name="color" id="color" className="form-control" value={this.props.color || "Select Color"} onChange={this.props.handleInputChange}>
          <option value="">Select Any</option>
          {colorList ? colorList.map(this.optionsList) : <option value=""></option>}
        </select>
      </div>
		)
	}

}
