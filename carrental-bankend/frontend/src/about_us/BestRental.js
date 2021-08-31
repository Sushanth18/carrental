import * as React from "react";
import 'jquery/src/jquery.js';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';
import '../static/css/main.css';
import '../static/css/about_us.css';

export class BestRental extends React.Component {

	render () {

		return (
      <div id="best-rental" className="flow-container mt-5">
        <div className="container">
          <h1>The best car rental !</h1>
          <p>The financial capital of India, Mumbai is a city which never sleeps. Buzzing with activity, it also holds the distinction of being the entertainment hub of the country. With a beach in its backyard and the picturesque Western Ghats a stone’s throw away, the city attracts visitors from all over the word. From the Gateway of India which opens the doors to India’s mysteries, to the ancient caves of Elephanta Island, and from the iconic Stock Exchange to the many studios where Bollywood movies are made, the city offers a multitude of sights and experiences.
The downside of life in Mumbai is the difficulty commuting poses. Overloaded public transport and increasing distances add to the stress of living in Mumbai. Whether you are a visitor to this melting-pot of cultures, or a resident of the city, it is important to have a self-drive car at your disposal to make the most of your time. If you do not plan to buy a car of your own, Car Rental offers the perfect solution by making the process of self-drive car booking in Mumbai convenient and affordable. The Car Rental app allows you the freedom of booking a self drive car at your fingertips.</p>
        </div>
      </div>
		)
	}

}
