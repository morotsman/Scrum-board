define([
	'angular',
        'ui_sortable',
        'ui_keypress',
	'services',
	'directives',
	'todoControllers',
	'menuControllers',
	'angularRoute',
        'ngResource',
        'ui_bootstrap'
	], function () {
		'use strict';

		return angular.module('myApp', [
			'ngRoute',
			'myApp.todoControllers',
			'myApp.menuControllers',
			'myApp.services',
			'myApp.directives',
                        'ui.sortable',
                        'ui.keypress',
                        'ngResource',
                        'ui.bootstrap'
                       
		]);
});
