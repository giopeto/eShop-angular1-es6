import groupsService from './groups.service';

routes.$inject = ['$stateProvider'];

export default function routes($stateProvider) {

	const groupsState = {
		name: 'groups',
		url: '/groups',
		template: require('./groups.html'),
		controller: 'GroupsController',
		controllerAs: 'vm',
		resolve: {
			groups: function() {
				return groupsService.get();
			},
		}
	};

	const groupsAddEditState = {
		name: 'groups_add_edit',
		url: '/groups_add_edit/:id',
		template: require('./groups_add_edit.html'),
		controller: 'GroupsAddEditController',
		controllerAs: 'vm',
		resolve: {
			group: function($stateParams) {
				return $stateParams.id ?
					groupsService.getById($stateParams.id) : 
					{}
			}
		}
	};

	$stateProvider.state(groupsState);
	$stateProvider.state(groupsAddEditState);
}