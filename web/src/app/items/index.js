import angular from 'angular';
import uirouter from 'angular-ui-router';

import routing from './items.routes';
import ItemsController from './items.controller';
import ItemsAddEditController from './items.add.edit.controller';

import ImagesController from '../images/images.controller';

export default angular.module('app.items', [uirouter])
	.config(routing)
	.controller('ItemsController', ItemsController)
	.controller('ItemsAddEditController', ItemsAddEditController)
	.controller('ImagesController', ImagesController)
	.name;