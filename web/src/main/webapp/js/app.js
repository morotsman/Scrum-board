define([
	'angular',
        'ui_sortable',
        'ui_keypress',
	'daos',
	'directives',
	'adminControllers',
	'menuControllers',
	'angularRoute',
        'ngResource',
        'ui_bootstrap',
        'ngGrid'
	], function () {
		'use strict';

		return angular.module('myApp', [
			'ngRoute',
			'myApp.adminControllers',
			'myApp.menuControllers',
			'myApp.daos',
			'myApp.directives',
                        'ui.sortable',
                        'ui.keypress',
                        'ngResource',
                        'ui.bootstrap',
                        'ngGrid'
                       
		]);
});
