export default class GroupsController {

	constructor($scope, $state, groupService, groups) {
		this.$scope = $scope;
		this.$state = $state;
		this.groupService = groupService;
		this.groups = groups;
	}

	get() {
		this.groupService.get();
	}

	addEdit(id) {
		this.$state.go('groups_add_edit', {id: id});
	};

	remove({id, index}) {
		if(!confirm('Are you sure?')) {
			return
		}

		this.groupService.remove(id).then(() => {
			this.groups.splice(index, 1);
			this.$scope.$apply();
		});	
	}
}