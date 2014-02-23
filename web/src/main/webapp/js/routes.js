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

		$routeProvider.when('/PlanningView', {
            templateUrl: 'partials/Planning.html',
            controller: 'PlanningController'
        });

		$routeProvider.otherwise({redirectTo: '/PersonalView'});
	}]).run(function($rootScope, $location, todoService) {

            $rootScope.$on( "$routeChangeStart", function(event, next, current) {
                if(next.templateUrl === "partials/TeamOverview.html" && todoService.getTeamToOverview() === undefined){
                   $location.path( "/PersonalView" );
                }
                if(next.templateUrl === "partials/Planning.html" && todoService.getTeamToShowBoard() === undefined){
                    $location.path( "/PersonalView" );
                }
            });


    });

});