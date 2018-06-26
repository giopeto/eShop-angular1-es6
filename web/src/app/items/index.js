import angular from 'angular';
import uirouter from 'angular-ui-router';

import routing from './items.routes';
import ItemsController from './items.controller';

export default angular.module('app.items', [uirouter])
	.config(routing)
	.controller('ItemsController', ItemsController)
	.name;