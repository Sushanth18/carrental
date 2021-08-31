import React from 'react';
import ReactDOM from 'react-dom';
import { Switch, Route,Redirect } from 'react-router-dom'
import { MainWebsiteContent } from './MainWebsiteContent.js';
import { ReservationData } from './reservation/reservation_data/ReservationData.js';
import { ReservationCarSelection } from './reservation/reservation_select_car/ReservationCarSelection.js';
import { ReservationConfirm } from './reservation/reservation_confirm/ReservationConfirm.js';
import { ReservationSucceed } from './reservation/reservation_succeed/ReservationSucceed.js';
import { SearchResults } from './carlist/search_results/SearchResults.js';
import { Login } from './login/Login.js';
import  Register  from './register/Register';
import { NavContainer } from './nav/NavContainer.js';
import { MainContent } from './MainContent.js';
import { Users } from './users/Users.js';
import { EditUser } from './users/EditUser.js';
import { CarList } from './cars/CarList.js'
import { EditCar } from './cars/EditCar.js';
import { AddCar } from './cars/AddCar.js';
import { ShowCarFeatures } from './cars/ShowCarFeatures.js';
import { FeaturesList } from './cars/FeaturesList.js';
import { UsersRoles } from './users_roles/UsersRoles.js';
import { AddRole } from './users_roles/AddRole.js';
import { SendEmail } from './send_email/SendEmail.js';
import { UserEmailContainer } from './send_email/UserEmailContainer.js';
import { Settings } from './settings/Settings.js';
import { AllBookings } from './booking/AllBookings.js';
import { AllRentedVehicles } from './booking/AllRentedVehicles.js';
import { AllReservedVehicles } from './booking/AllReservedVehicles.js';
import { MyAllBookings } from './booking/MyAllBookings.js';
import { MyAllReservedVehicles } from './booking/MyAllReservedVehicles.js';
import { MyAllRentedVehicles } from './booking/MyAllRentedVehicles.js';
import { BookingChanges } from './booking/BookingChanges.js';
import { Locations } from './locations/Locations.js';
import { Chat } from './chat/ChatMessageBox.js';

export class Content extends React.Component {
	render() {
		return (
			<main>
				<Switch>
					<Route exact path="/">
						<Redirect to="/CarRental" />
					</Route>
					<Route exact path="/CarRental/" component={MainWebsiteContent} />
					<Route path="/CarRental/login" component={Login} />
					<Route path="/CarRental/listcar" component={MainWebsiteContent} />
					<Route path="/CarRental/listcar?page=:page" component={MainWebsiteContent} />
					<Route path="/CarRental/bestoffers" component={MainWebsiteContent} />
					<Route path="/CarRental/bestoffers?page=:page" component={MainWebsiteContent} />
					<Route path="/CarRental/aboutus" component={MainWebsiteContent} />
					<Route path="/CarRental/contact" component={MainWebsiteContent} />
					<Route path="/CarRental/cardetails/:car_id" component={MainWebsiteContent} />
					<Route path="/CarRental/searchresult" component={MainWebsiteContent} />
					<Route path="/CarRental/searchresult?page=:page" component={MainWebsiteContent} />

					<Route path="/CarRental/reservation/data" component={ReservationData} />
					<Route path="/CarRental/reservation/selectcar" component={ReservationCarSelection} />
					<Route path="/CarRental/reservation/confirm" component={ReservationConfirm} />
					<Route path="/CarRental/reservation/succeed" component={ReservationSucceed} />
					<Route path="/CarRental/registration" component={Register} />
					<Route exact path="/CarRental/profile" component={MainContent} />
					<Route path="/CarRental/profile/allbookings" component={AllBookings} />
					<Route path="/CarRental/profile/allreservedvehicles" component={AllReservedVehicles} />
					<Route path="/CarRental/profile/allrentedvehicles" component={AllRentedVehicles} />
					<Route path="/CarRental/profile/allmybookings" component={MyAllBookings} />
					<Route path="/CarRental/profile/bookingchanges" component={BookingChanges} />
					<Route path="/CarRental/profile/myreservedbookings" component={MyAllReservedVehicles} />
					<Route path="/CarRental/profile/myrentedbookings" component={MyAllRentedVehicles} />
					<Route path="/CarRental/profile/userlist" component={Users} />
					<Route path="/CarRental/profile/edituser/:user_id" component={EditUser} />
				
					<Route path="/CarRental/profile/carslist" component={CarList} />
					<Route path="/CarRental/profile/editcar/:car_id" component={EditCar} />
					<Route path="/CarRental/profile/addcar" component={AddCar} />
					<Route path="/CarRental/profile/carequipments" component={ShowCarFeatures} />
				
					<Route path="/CarRental/profile/equipmentslist" component={FeaturesList} />
					<Route path="/CarRental/profile/userroles" component={UsersRoles} />
					<Route path="/CarRental/profile/addrole/:user_id" component={AddRole} />
					<Route path="/CarRental/profile/sendemail" component={SendEmail} />
					<Route path="/CarRental/profile/senduseremail/:user_id" component={UserEmailContainer} />
					<Route path="/CarRental/profile/settings" component={Settings} />
					<Route path="/CarRental/profile/locations" component={Locations} />
					<Route path="/CarRental/profile/chat" component={Chat} />
					<Route path="/CarRental/nav" component={NavContainer} />
					<Route path="/CarRental/carlist/search_results" component={SearchResults} />
				</Switch>
			</main>
		)
	}
}
