import angular from 'angular';
import uirouter from 'angular-ui-router';

import routing from './home.routes';
import HomeController from './home.controller';
import homeService from './home.service';

export default angular.module('app.home', [uirouter, homeService])
	.config(routing)
	.controller('HomeController', HomeController)
	.name;