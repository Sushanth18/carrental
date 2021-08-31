import React from 'react';
import ReactDOM from 'react-dom';
import {MainNav} from './MainNav.js';
import {AsideNav} from './AsideNav.js';
import {Main} from './Main.js';
import {Footer} from './Footer.js';

export class MainWebsiteContent extends React.Component {
  constructor() {
    super();
  }
  
	render () {
		return (
      <div>
        <AsideNav props={this.props}/>
        <MainNav props={this.props}/>
        <Main props={this.props}/>
        <Footer props={this.props}/>
      </div>
		)
	}

}
