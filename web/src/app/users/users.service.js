import * as Api from '../../Api';

class UsersClass {

	constructor() {
		this.USERS_URL = `${Api.API_BASE_URL}/users`;
	}

	signUp(payload) {
		return Api.callPost(this.USERS_URL, payload);
	}
	
	update(payload) {
		return Api.callPut(`${this.USERS_URL}/${payload.id}`, payload);
	}

	/*

	get() {
		return Api.callGet(this.GROUPS_URL);
	}

	getById(id) {
		return Api.callGet(`${this.GROUPS_URL}/${id}`);
	}

	remove(id) {
		return Api.callDelete(`${this.GROUPS_URL}/${id}`);
	}*/
}

const UsersService = new UsersClass();
export default UsersService;