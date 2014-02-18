require.config({
	paths: {                
                jquery: 'external/jquery/jquery.min',
                jquery_ui: 'external/jquery-ui/jquery-ui',
		angular: 'external/angular/angular.min',
                ui_sortable: 'external/ui-sortable/sortable',
                ui_keypress: 'external/ui_utils/keypress',
		angularRoute: 'external/angular-route/angular-route.min',
		angularMocks: 'external/angular-mocks/angular-mocks',
		text: 'external/requirejs-text/text',
                ngResource: "external/angular-resource/angular-resource.min",
                ui_bootstrap : "external/angular-bootstrap/ui-bootstrap-tpls.min",
                diff_match_patch : "external/diff-match-patch/diff_match_patch",
                _ : "external/underscore/underscore-min",
                ngGrid : "external/ng-grid/ng-grid-2.0.7.min"
                 
	},
	baseUrl: 'js',
	shim: {
                'jquery_ui' : {'exports' : '$', deps : ['jquery']},
                'angular' : {'exports' : 'angular', deps : ['jquery']},  
		'angularRoute': {deps: ['angular']},
		'angularMocks': {
			deps:['angular'],
			'exports':'angular.mock'
		},
                'ui_sortable' : {
                    deps:['angular']
                },
                'ui_keypress' : {
                    deps:['angular','jquery']
                },
                'ngResource':{
                    deps:['jquery', 'angular']
                 },
                 'ui_bootstrap' :{
                    deps:['angular'] 
                 },
                 'ngGrid':{
                    deps:['jquery', 'angular']
                 }
	},
	priority: [
                "angular"
                
	]
});

// hey Angular, we're bootstrapping manually!
window.name = "NG_DEFER_BOOTSTRAP!";

require( [
    'jquery',
    'jquery_ui',
	'angular',
	'app',
	'routes'
], function($,jquery_ui,angular,app) {
	'use strict';
	var $html = angular.element(document.getElementsByTagName('html')[0]);

	angular.element().ready(function() {
		$html.addClass('ng-app');
		angular.bootstrap($html, [app['name']]);
	});
});
