routes.$inject = ['$stateProvider'];

export default function routes($stateProvider) {

	const itemsState = {
		name: 'items',
		url: '/items',
		template: require('./items.html'),
		controller: 'ItemsController',
		controllerAs: 'vm'
	};


	$stateProvider.state(itemsState);

}