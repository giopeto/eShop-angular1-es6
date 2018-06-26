import itemsService from './items.service';
import groupsService from '../groups/groups.service';

console.log(groupsService);

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

	const itemsAddEditState = {
		name: 'items_add_edit',
		url: '/items_add_edit/:id',
		template: require('./items_add_edit.html'),
		controller: 'ItemsAddEditController',
		controllerAs: 'vm',
		resolve: {
			item: function($stateParams) {
				return $stateParams.id ?
					itemsService.getById($stateParams.id) : 
					{}
			},
			groups: function() {
				return groupsService.get();
			}
		}
	};

	$stateProvider.state(itemsState);
	$stateProvider.state(itemsAddEditState);
}