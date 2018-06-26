import * as Api from '../../Api';

class GroupClass {

	constructor() {
		this.GROUPS_URL = `${Api.API_BASE_URL}/groups`;
	}

	save(payload) {
		return Api.callPost(this.GROUPS_URL, payload);
	}

	update(payload) {
		return Api.callPut(`${this.GROUPS_URL}/${payload.id}`, payload);
	}

	get() {
		return Api.callGet(this.GROUPS_URL);
	}

	getById(id) {
		return Api.callGet(`${this.GROUPS_URL}/${id}`);
	}

	remove(id) {
		return Api.callDelete(`${this.GROUPS_URL}/${id}`);
	}
}

const GroupService = new GroupClass();
export default GroupService;