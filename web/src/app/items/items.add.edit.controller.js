import itemsService from './items.service';
import imagesService from '../images/images.service';

export default class ItemsAddEditController {

	constructor($state, item, groups) {
		this.$state = $state;
		this.item = item;
		this.groups = groups;
		this.errorMsg = this.getErrorMsgObj();
	}

	save() {
		if (this.hasErrors()) {
			return;
		}

		itemsService.save(this.item)
			.then(response => imagesService.save(response.id))
			.then(() => this.goBack());
	}

	update() {
		if (this.hasErrors()) {
			return;
		}
		
		itemsService.update(this.item).then(response => {
			imagesService.save(response.id); 
			this.goBack()
		});
		
	}

	goBack() {
		this.$state.go('items');
	};

	getErrorMsgObj() {
		return {
			emptyGroupId: false,
			emptyName: false
		};
	};

	hasErrors() {
		let hasErrors = false;
		this.errorMsg = this.getErrorMsgObj();

		if (!this.item.name) {
			this.errorMsg.emptyName = true;
			hasErrors = true;
		}
		
		if (!this.item.groupId) {
			this.errorMsg.emptyGroupId = true;
			hasErrors = true;
		}
		return hasErrors;
	}
}