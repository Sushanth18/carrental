import * as React from "react";
import 'jquery/src/jquery.js';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';
import '../static/css/main.css';
import '../static/css/about_us.css';
import car_rental_logo_name from '../CarRental/etc-img/car_rental_logo_name.png';
export class Description extends React.Component {


	render () {
		return (
      <div id="car-rental-description-container" className="flow-container">
        <div id="car-rental-description">
          <div className="container">
            <img src={car_rental_logo_name} alt="logo" id="car-rental-logo-desc" className="mt-5"/>
            <p className="mt-5 ">
            On many occasions, working professionals and business persons want to commute to different parts of the city in the same day. Usually, they hire a chauffeur driven car and pay extra for the waiting charges. This option, while popular, is also more expensive. It is more economical to opt for a self-drive car rental in Mumbai. You can spend any amount of time at a meeting without having to worry about the waiting charges, and use the car to explore Mumbai after work at your leisure. Car Rental also provides Mumbai airport taxi service, using which you can pick up one of our cars right at the airport when you land. We have a variety of self drive cars in our fleet including luxury brands which makes it easier to book luxury self drive cars on rent in Mumbai.
            </p>
          </div>
        </div>
      </div>
		)
	}

}
