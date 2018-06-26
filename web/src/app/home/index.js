import angular from 'angular';
import uirouter from 'angular-ui-router';

import routing from './home.routes';
import HomeController from './home.controller';
import homeService from './home.service';

import greeting from '../../directives/greeting.directive';

export default angular.module('app.home', [uirouter, homeService, greeting])
	.config(routing)
	.controller('HomeController', HomeController)
	.name;