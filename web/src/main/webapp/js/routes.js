define(['angular', 'app'], function(angular, app) {
	'use strict';

	return app.config(['$routeProvider', function($routeProvider) {
		$routeProvider.when('/TodoView', {
			templateUrl: 'partials/Editor.html',
			controller: 'TodoController'
		});
        $routeProvider.when('/login', {
			templateUrl: 'partials/Login.html',
			controller: 'LoginController'
		});
		$routeProvider.when('/createUser', {
            templateUrl: 'partials/CreateUser.html',
        	controller: 'CreateUserController'
        });
        $routeProvider.when('/userCreated', {
            templateUrl: 'partials/UserCreated.html',
            controller: 'UserCreatedController'
         });



		$routeProvider.otherwise({redirectTo: '/login'});
	}]).run(function($rootScope, $location) {
            $rootScope.$on( "$routeChangeStart", function(event, next, current) {
                if ( $rootScope.loggedUser === undefined || $rootScope.loggedUser === null ) {
                    // no logged user, we should be going to #login
                    if ( next.templateUrl === "partials/login.html" ) {
                        // already going to #login, no redirect needed
                    } else {
                        // not going to #login, we should redirect now
                        $location.path( "/login" );
                    }
                }  
            });
        });

});