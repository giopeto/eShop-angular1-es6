import itemsService from './items.service';
import groupsService from '../groups/groups.service';
import imagesController from '../images/images.controller';
import imagesService from '../images/images.service';

routes.$inject = ['$stateProvider'];

export default function routes($stateProvider) {

	const itemsState = {
		name: 'items',
		url: '/items',
		template: require('./items.html'),
		controller: 'ItemsController',
		controllerAs: 'vm',
		resolve: {
			items: function() {
				return itemsService.get();
			},
		}
	};

	const itemsAddEditView = {
		template: require('./items_add_edit.html'),
		controller: 'ItemsAddEditController',
		controllerAs: 'vm',
		
	};
	
	const filesAddEditView = {
		template: require('../images/images.html'),
		controller: imagesController,
		controllerAs: 'vm'
	};

	const itemsAddEditStateView = {
		name: 'items_add_edit',
		url: '/items_add_edit/:id',
		views: {
			'': { template: require('./items_add_edit_view.html') },
			'items@items_add_edit': itemsAddEditView,
			'files@items_add_edit': filesAddEditView,
		},
		resolve: {
			item: function($stateParams) {
				return $stateParams.id ?
					itemsService.getById($stateParams.id) :
					{}
			},
			groups: function() {
				return groupsService.get();
			},
			domainId: function($stateParams) {
				return $stateParams.id;
			},
			filesToDomainMapper: function($stateParams) {
				return $stateParams.id ?
					imagesService.getByDomainId($stateParams.id) : 
					[]
			}
		}
	};

	$stateProvider.state(itemsState);
	$stateProvider.state(itemsAddEditStateView);
}