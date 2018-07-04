routing.$inject = ['$urlRouterProvider', '$locationProvider', '$stateProvider'];

export default function routing($urlRouterProvider, $locationProvider, $stateProvider) {
	$locationProvider.html5Mode({
		enabled: false,
		requireBase: false
	});
	
	$urlRouterProvider.otherwise('/');
}