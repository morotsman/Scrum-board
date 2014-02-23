define([
	'angular',
        'ui_sortable',
        'ui_keypress',
	'daos',
	'services',
	'directives',
	'adminControllers',
	'menuControllers',
	'teamOverviewControllers',
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
			'myApp.services',
			'myApp.directives',
                        'ui.sortable',
                        'ui.keypress',
                        'ngResource',
                        'ui.bootstrap',
                        'ngGrid',
                        'myApp.teamOverviewControllers'
                       
		]);
});
