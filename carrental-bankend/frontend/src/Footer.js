import * as React from "react";
import 'jquery/src/jquery.js';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';
import './static/css/main.css'
import 'font-awesome/css/font-awesome.min.css';
import '../node_modules/font-awesome/css/font-awesome.min.css'; 
import '../node_modules/font-awesome/css/font-awesome.css'
import {Link} from 'react-router-dom';


export class Footer extends React.Component {
	render () {
		var d = new Date();
    var n = d.getFullYear();

		return (
			  <footer>
				<div className="container">
			      <div className="row">
			        <div id="pages-list" className="col-lg-3 col-md-12">
			          <ul>
									<li><Link to='/CarRental/' className="linkstyle_black">Home</Link></li>
									<li><Link to='/CarRental/listcar' className="linkstyle_black">Car list</Link></li>
									<li><Link to='/CarRental/bestoffers' className="linkstyle_black">Best offers</Link></li>
									<li><Link to='/CarRental/aboutus' className="linkstyle_black">About us</Link></li>
									<li><Link to='/CarRental/contact' className="linkstyle_black">Contact</Link></li>
			          </ul>
			        </div>


			        <div id="news-list" className="col-lg-5 col-md-12 mt-3">
								<h5>Socials:</h5>
								<div className="row">
									<a href="https://www.facebook.com" target="_blank"><i className="fa fa-facebook-f" style={{fontSize:50,marginLeft:"10px"}}></i></a><br></br>
									<a href="https://www.twitter.com" target="_blank"><i className="fa fa-twitter" style={{fontSize:50,marginLeft:"10px"}}></i></a><br></br>
									<a href="https://www.instagram.com" target="_blank"><i className="fa fa-instagram" style={{fontSize:50,marginLeft:"10px"}}></i></a><br></br>
									<a href="https://www.youtube.com" target="_blank"><i className="fa fa-youtube" style={{fontSize:50,marginLeft:"10px"}}></i></a><br></br>
								</div>
			        </div>


			        <div className="footer-contact"  className="col-lg-4 col-md-12 mt-3">
			          <h5 className="linkstyle_black">Contact us:</h5>
			          <i className="fa fa-phone linkstyle_black" style={{fontSize:24}}></i>  <t className="linkstyle_black"> 423 2321 123 </t> <br></br>
			          <i className="fa fa-mobile linkstyle_black" style={{fontSize:24}}></i> <t className="linkstyle_black"> 948 1321 423 </t> <br></br>
			          <i className="fa fa-envelope linkstyle_black" style={{fontSize:24}}></i> <a href="mailto:car.rental@test.com">test.carental@.com</a> <br></br><br></br>
			        </div>
			      </div>


			    </div>

			    <div className="footer-copyright text-center">
			      © {n} Copyright:  <Link to='/CarRental/' className="linkstyle_white font-weight-bold">CarRental</Link>
			    </div>
			   </footer>
		)
	}

}
