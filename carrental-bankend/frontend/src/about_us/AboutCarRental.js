import * as React from "react";
import 'jquery/src/jquery.js';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';
import '../static/css/main.css';
import '../static/css/about_us.css';
import shelby_gt500 from '../CarRental/etc-img/suv_car_round2.jpeg';
export class AboutCarRental extends React.Component {

	render () {
		return (
      <div id="about-car-rental" className="container">
        <h1 className="mt-4">ABOUT CAR RENTAL</h1>
        <div className="row mb-5">
          <img src={shelby_gt500} className="mt-4  col-md-6"/>
          <p className="mt-4 col-md-6">
          No more worries about petrol mileage, insurance, and car breakdowns! Car Rental has enabled driving convenience for travellers around the country and is fast expanding its reach to cities including Ahmedabad, Bangalore, Chandigarh, Chennai, Coimbatore, Delhi-NCR, Hyderabad, Jaipur, Kochi, Kolkata, Ludhiana, Mangalore, Mumbai, Mysore, Pune, Siliguri, Vizag, Nagpur, Udaipur, Vijayawada, Surat, Lucknow and Guwahati.
Self drive cars from Car Rental have given customers more control, privacy, and freedom. Book a self drive car in any city you visit with the Car Rental app on your phone and feel at home wherever you go.
          </p>
        </div>
      </div>
		)
	}

}
