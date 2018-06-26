import angular from 'angular';

class HomeService {
	constructor() {
	}

}

export default angular.module('services.homeService', [])
	.service('homeService', HomeService)
	.name;