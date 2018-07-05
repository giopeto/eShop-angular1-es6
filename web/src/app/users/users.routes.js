import usersService from './users.service';

routes.$inject = ['$stateProvider'];

export default function routes($stateProvider) {

	const signUpState = {
		name: 'signup',
		url: '/signup',
		template: require('./signup.html'),
		controller: 'UsersController',
		controllerAs: 'vm'
	};

	const signInState = {
		name: 'signin',
		url: '/signin',
		template: require('./signin.html'),
		controller: 'UsersController',
		controllerAs: 'vm'
	};

	$stateProvider.state(signUpState);
	$stateProvider.state(signInState);
}