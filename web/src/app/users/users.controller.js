import usersService from './users.service';
import * as Api from '../../Api';

export default class UsersController {

	constructor($scope, $state) {
		this.$scope = $scope;
		this.$state = $state;
		this.user = {};
		this.USERS_URL = `${Api.API_BASE_URL}/users`;
	}

	signUp() {
		console.log(this.user);
		this.user.role = 'ROLE_ADMIN';
		usersService.signUp(this.user).then(() => this.authenticate());
	}

	authenticate() {
		
		alert('Call service');
			 Api.callGet(`${this.USERS_URL}`); 
	}

	/*addEdit(id) {
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
	}*/
}