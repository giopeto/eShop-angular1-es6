routing.$inject = ['$urlRouterProvider', '$locationProvider'];

export default function routing($urlRouterProvider, $locationProvider) {
	$locationProvider.html5Mode({
		enabled: false,
		requireBase: false
	});
	$urlRouterProvider.otherwise('/');
}