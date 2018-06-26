import * as Api from '../../Api';

class ItemsClass {

	constructor() {
		this.ITEMS_URL = `${Api.API_BASE_URL}/items`;
	}

	save(payload) {
		return Api.callPost(this.ITEMS_URL, payload);
	}

	update(payload) {
		return Api.callPut(`${this.ITEMS_URL}/${payload.id}`, payload);
	}

	get() {
		return Api.callGet(this.ITEMS_URL);
	}

	getById(id) {
		return Api.callGet(`${this.ITEMS_URL}/${id}`);
	}

	remove(id) {
		return Api.callDelete(`${this.ITEMS_URL}/${id}`);
	}
}

const ItemsService = new ItemsClass();
export default ItemsService;