import angular from 'angular';
import uirouter from 'angular-ui-router';

import routing from './app.config';

import AppController from './app.controller';

import home from './app/home/';
import groups from './app/groups/';
import items from './app/items/';

angular.module('app', [uirouter, home, groups, items])
	.config(routing)
	.controller('AppController', AppController)
	.name;