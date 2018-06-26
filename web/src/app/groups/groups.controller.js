import groupsService from './groups.service';

export default class GroupsController {

	constructor($scope, $state, groups) {
		this.$scope = $scope;
		this.$state = $state;
		this.groups = groups;
	}

	addEdit(id) {
		this.$state.go('groups_add_edit', {id: id});
	};

	remove({id, index}) {
		if(!confirm('Are you sure?')) {
			return;
		}

		groupsService.remove(id).then(() => {
			this.groups.splice(index, 1);
			this.$scope.$apply();
		});	
	}
}