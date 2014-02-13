define([
	'angular',
        'ui_sortable',
        'ui_keypress',
	'services',
	'directives',
	'todoControllers',
	'angularRoute',
        'ngResource',
        'ui_bootstrap'
	], function () {
		'use strict';

		return angular.module('myApp', [
			'ngRoute',
			'myApp.todoControllers',
			'myApp.services',
			'myApp.directives',
                        'ui.sortable',
                        'ui.keypress',
                        'ngResource',
                        'ui.bootstrap'
                       
		]);
});
