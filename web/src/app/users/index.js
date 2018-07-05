import angular from 'angular';
import uirouter from 'angular-ui-router';

import routing from './users.routes';
import UsersController from './users.controller';

export default angular.module('app.users', [uirouter])
	.config(routing)
	.controller('UsersController', UsersController)
	.name;