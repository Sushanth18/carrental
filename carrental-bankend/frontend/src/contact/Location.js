import * as React from "react";
import "jquery/src/jquery.js";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.js";
import "../static/css/main.css";
import "../static/css/contact.css";
import GoogleMapReact from "google-map-react";
import { Map } from "./Map.js";

export class Location extends React.Component {
  static defaultProps = {
    center: {
      lat: 52.23,
      lng: 21.01,
    },
    zoom: 11,
  };

  render() {
    return (
      <div style={{ height: "500px", width: "100%" }}>
        <figure className="img_inner fleft">
          <iframe
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3767.0711843329077!2d73.13226952364487!3d19.235729189459008!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0000000000000000%3A0xaccee08d3f9a03cc!2sKalyan+Junction+(West)+Ticket+Counter!5e0!3m2!1sen!2sin!4v1453901522162"
            width="100%"
            height="450"
            frameborder="0"
            // style="border:0"
            allowfullscreen
          ></iframe>
        </figure>
        {/* <GoogleMapReact
          // bootstrapURLKeys={{ key: 'AIzaSyCk7pbkmNhknGumy2vgDykdgVj6lSreTt0', libraries: ['places']  }}

          defaultCenter={this.props.center}
          defaultZoom={this.props.zoom}
        >
          <Map
            // lat={52.230774}
            // lng={21.006348}
            lat={59.955413}
            lng={30.337844}
          />
        </GoogleMapReact> */}
      </div>
    );
  }
}
