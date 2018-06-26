import groupsService from './groups.service';

export default class GroupsAddEditController {

	constructor($state, group) {
		this.$state = $state;
		this.group = group;
		this.errorMsg = this.getErrorMsgObj();
	}

	save() {
		if (this.hasErrors()) {
			return;
		}

		groupsService.save(this.group).then(() => this.goBack());
	}

	update() {
		if (this.hasErrors()) {
			return;
		}
		
		groupsService.update(this.group).then(() => this.goBack());
	}

	goBack() {
		this.$state.go('groups');
	};

	getErrorMsgObj() {
		return {
			emptyName: false
		};
	};

	hasErrors() {
		let hasErrors = false;
		if (!this.group.name) {
			this.errorMsg.emptyName = true;
			hasErrors = true;
		}
		return hasErrors;
	}
}