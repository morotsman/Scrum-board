define(['angular','_'], function() {
    'use strict';

    var todoControllers =  angular.module('myApp.todoControllers', ['myApp.services']);
            // Sample controller where service is being used

    todoControllers.controller('TodoController', ['$scope', function($scope) {

    }]);



    return todoControllers;





});