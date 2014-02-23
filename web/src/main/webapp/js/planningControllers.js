define(['angular','_'], function() {
    'use strict';

    var planningControllers =  angular.module('myApp.planningControllers', ['myApp.daos']);
            // Sample controller where service is being used




    planningControllers.controller('PlanningController', ['$scope','todoService','$modal', 'sprintDao', 'storyDao', function($scope, todoService, $modal, sprintDao, storyDao) {

        $scope.planningData = {};

        $scope.planningData.team = todoService.getTeamToShowBoard();


        var teamChanged = function(){
            $scope.planningData.team = todoService.getTeamToShowBoard();
            alert($scope.planningData.team.teamName);
        };

        $scope.$on('PlanningSelected', teamChanged);

        var failure = function(){
            alert("Failure");
        }

        var sprintsLoaded = function(data){
            $scope.planningData.sprints = data.sprints;
            $scope.planningData.sprints.push({name:"Backlog", stories:[]});
            _.each($scope.planningData.sprints, function(sprint){
                loadStories(sprint);
            });
        }

        var loadSprints = function(){
            sprintDao.getSprints($scope.planningData.team, sprintsLoaded, failure);
        };

        $scope.addSprint = function(){
            var modalInstance = $modal.open({
                templateUrl: 'partials/addSprint.html',
                controller: 'CreateSprintDialogController'
            });

            modalInstance.result.then(function(presentation) {
                loadSprints();
            }, function() {
                //cancel
            });
        };

          $scope.planningData.sortableOptions = {
            placeholder: "stories-placeholder",
            connectWith: ".stories-container",
            items: "> .sortable",
            receive: function(e, ui)  {
                  // ui.item -> item that will be received in the new list; scope undefined
                  // ui.sender -> list that is the source of this item; scope != undefined; scope access to the modified source list
                  console.log('receive item',ui.item.scope());
                  console.log('receive sender',ui.sender.scope());
                }
          };



        var loadStories = function(sprint){
            if(sprint.name === "Backlog"){
                storyDao.getStories($scope.planningData.team, function(data){
                    sprint.stories = data.stories;
                }, failure);
            }

        };

        $scope.addStory = function(index){
            alert("add story for: " + $scope.planningData.sprints[index].name);
            var modalInstance = $modal.open({
                templateUrl: 'partials/createStory.html',
                controller: 'CreateStoryDialogController'
            });

            modalInstance.result.then(function(presentation) {
                loadStories($scope.planningData.sprints[index]);
            }, function() {
                //cancel
            });
        };

        loadSprints();


    }]);

    planningControllers.controller('CreateSprintDialogController', ['$scope', '$modalInstance','sprintDao','todoService', function($scope, $modalInstance, sprintDao, todoService) {

            $scope.createSprintData = {};

            $scope.ok = function() {
                var result;
                sprintDao.createSprint(todoService.getTeamToShowBoard(), $scope.createSprintData.sprintName, function(data){
                    $modalInstance.close(data);
                }, function(data){
                    if(data.status === 409){
                        $scope.createSprintData.error = "A sprint called " + $scope.createSprintData.sprintName + " already exists.";
                    }else{
                        $scope.createSprintData.error = "Failed to create sprint due to " + data.status;
                    }
                });

            };

            $scope.cancel = function() {
                $modalInstance.dismiss('cancel');
            };

    }]);

    planningControllers.controller('CreateStoryDialogController', ['$scope', '$modalInstance','storyDao','todoService', function($scope, $modalInstance, storyDao, todoService) {

            $scope.createStoryData = {};

            $scope.ok = function() {
                 var result;
                 storyDao.createStory(todoService.getTeamToShowBoard(), $scope.createStoryData.story, function(data){
                     $modalInstance.close(data);
                 }, function(data){
                     if(data.status === 409){
                         $scope.createStoryData.error = "A story called " + $scope.createStoryData.story.name + " already exists.";
                     }else{
                         $scope.createStoryData.error = "Failed to create story due to " + data.status;
                     }
                 });

            };

            $scope.cancel = function() {
                $modalInstance.dismiss('cancel');
            };

    }]);



    return planningControllers;





});