import imagesService from './images.service';

routes.$inject = ['$stateProvider'];

export default function routes($stateProvider) {

	const imagesAddEditState = {
		name: 'images',
		url: '/images/:domainId',
		template: require('./images.html'),
		controller: 'ImagesController',
		controllerAs: 'vm',
		resolve: {
			domainId: function($stateParams) {
				return $stateParams.domainId;
			},
			filesToDomainMapper: function($stateParams) {
				return $stateParams.domainId ?
					imagesService.getByDomainId($stateParams.domainId) : 
					[]
			}
		}
	};

	$stateProvider.state(imagesAddEditState);
}