import angular from 'angular';
import uirouter from 'angular-ui-router';

import routing from './groups.routes';
import GroupsController from './groups.controller';
import GroupsAddEditController from './groups.add.edit.controller';
import GroupService from './groups.service';

export default angular.module('app.groups', [uirouter, GroupService])
	.config(routing)
	.controller('GroupsController', GroupsController)
	.controller('GroupsAddEditController', GroupsAddEditController)
	.name;