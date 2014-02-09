define([
	'angular',
        'ui_sortable',
        'ui_keypress',
	'services',
	'directives',
	'loginControllers',
	'todoControllers',
	'angularRoute',
        'ngResource',
        'ui_bootstrap'
	], function () {
		'use strict';

		return angular.module('myApp', [
			'ngRoute',
			'myApp.loginControllers',
			'myApp.todoControllers',
			'myApp.services',
			'myApp.directives',
                        'ui.sortable',
                        'ui.keypress',
                        'ngResource',
                        'ui.bootstrap'
                       
		]);
});
