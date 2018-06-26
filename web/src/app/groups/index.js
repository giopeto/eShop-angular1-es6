import angular from 'angular';
import uirouter from 'angular-ui-router';

import routing from './groups.routes';
import GroupsController from './groups.controller';
import GroupsAddEditController from './groups.add.edit.controller';

export default angular.module('app.groups', [uirouter])
	.config(routing)
	.controller('GroupsController', GroupsController)
	.controller('GroupsAddEditController', GroupsAddEditController)
	.name;