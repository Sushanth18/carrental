import * as React from "react";
import 'jquery/src/jquery.js';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';
import '../static/css/main.css';
import '../static/css/home.css';
import { Link } from 'react-router-dom'
import sport_car_round2 from '../CarRental/etc-img/sport_car_round2.jpeg';
import sedan_car_round from '../CarRental/etc-img/sedan_car_round.jpeg';
import suv_car_round4 from '../CarRental/etc-img/suv_car_round2.jpeg';
// import suv_car_round4 from '../settings/static/img/suv_car_round4.jpeg';
import cabrio_car_round2 from '../CarRental/etc-img/suv_car_round2.jpeg';
export class RoundedImg extends React.Component {

	render () {

		return (
      <div id="car-types-round-images">
        <div className="container mt-5 mb-4">
          <div className="row">
            <div className="car-item col-sm-6 col-md-3">
							<Link to={"./CarRental/listcar"} className="linkstyle_black">
              	<img src={sport_car_round2} className="img-responsive" />
              	<div className="text-under-round-image">
                	<h2>Sportowe</h2>
              	</div>
							</Link>
            </div>

            <div className="car-item col-sm-6 col-md-3">
							<Link to={"/CarRental/listcar"} className="linkstyle_black">
              <img src={sedan_car_round} className="img-responsive"/>
              	<div className="text-under-round-image">
                	<h2>Sedan</h2>
              	</div>
							</Link>
            </div>

            <div className="car-item col-sm-6 col-md-3">
							<Link to={"/CarRental/listcar"} className="linkstyle_black">
              	<img src={suv_car_round4} className="img-responsive"/>
              	<div className="text-under-round-image">
                	<h2>SUV</h2>
              	</div>
							</Link>
            </div>

            <div className="car-item col-sm-6 col-md-3">
							<Link to={"/CarRental/listcar"} className="linkstyle_black">
              	<img src={cabrio_car_round2} className="img-responsive"/>
              	<div className="text-under-round-image">
                	<h2>Kabriolet</h2>
              	</div>
							</Link>
            </div>
          </div>
        </div>
      </div>
		)
	}

}
