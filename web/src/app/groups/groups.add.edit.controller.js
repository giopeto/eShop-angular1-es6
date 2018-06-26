export default class GroupsAddEditController {

	constructor($state, groupService, group) {
		this.$state = $state;
		this.groupService = groupService;
		this.group = group;
	}

	save() {
		this.groupService.save(this.group).then(() => this.goBack());
	}

	update() {
		this.groupService.update(this.group).then(() => this.goBack());
	}

	goBack() {
		this.$state.go('groups');
	};
}