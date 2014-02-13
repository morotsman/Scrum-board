define(['angular', 'app'], function(angular, app) {
	'use strict';

	return app.config(['$routeProvider', function($routeProvider) {
		$routeProvider.when('/PersonalView', {
			templateUrl: 'partials/Personal.html',
			controller: 'PersonalController'
		});

		$routeProvider.when('/AdminTeamView', {
            templateUrl: 'partials/AdminTeam.html',
            controller: 'AdminTeamController'
        });

		$routeProvider.when('/AdminUserView', {
            templateUrl: 'partials/AdminUser.html',
            controller: 'AdminUserController'
        });

		$routeProvider.when('/TeamOverviewView', {
            templateUrl: 'partials/TeamOverview.html',
            controller: 'TeamOverviewController'
        });

		$routeProvider.when('/BoardView', {
            templateUrl: 'partials/Board.html',
            controller: 'BoardController'
        });

		$routeProvider.otherwise({redirectTo: '/PersonalView'});
	}]).run(function($rootScope, $location) {

        });

});