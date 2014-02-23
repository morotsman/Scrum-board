define(['angular','_'], function() {
    'use strict';

    var teamOverviewControllers =  angular.module('myApp.teamOverviewControllers', ['myApp.daos']);
            // Sample controller where service is being used




    teamOverviewControllers.controller('TeamOverviewController', ['$scope','todoService','$modal', function($scope, todoService, $modal) {



    }]);




    return teamOverviewControllers;





});