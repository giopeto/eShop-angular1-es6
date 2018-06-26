import itemsService from './items.service';

export default class ItemsController {

	constructor($scope, $state, items) {
		this.$scope = $scope;
		this.$state = $state;
		this.items = items;
	}

	addEdit(id) {
		this.$state.go('items_add_edit', {id: id});
	};

	remove({id, index}) {
		if(!confirm('Are you sure?')) {
			return;
		}

		itemsService.remove(id).then((response) => {
			this.items.splice(index, 1);
			this.$scope.$apply();
		});
	}
}