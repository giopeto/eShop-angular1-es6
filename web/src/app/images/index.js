import angular from 'angular';
import uirouter from 'angular-ui-router';

import routing from './images.routes';
import ImagesController from './images.controller';

export default angular.module('app.images', [uirouter])
	.config(routing)
	.controller('ImagesController', ImagesController)
	.name;