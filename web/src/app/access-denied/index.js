import angular from 'angular';
import uirouter from 'angular-ui-router';

import routing from './access.denied.routes';

export default angular.module('app.access.denied', [uirouter])
	.config(routing)
	.name;