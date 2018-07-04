routes.$inject = ['$stateProvider'];

export default function routes($stateProvider) {
	$stateProvider
		.state('403', {
			url: '/403',
			template: require('./access-denied.html')
		});
}