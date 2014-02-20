define(['angular','_'], function() {
    'use strict';

    var adminControllers =  angular.module('myApp.adminControllers', ['myApp.daos']);
            // Sample controller where service is being used


    adminControllers.controller('PersonalController', ['$scope', function($scope) {


    }]);

    adminControllers.controller('AdminTeamController', ['$rootScope','$scope','teamDao','$http','membershipDao', 'userDao', function($rootScope,$scope, teamDao, $http, membershipDao, userDao) {

        $scope.teamAdminData = {};

        var teamsLoaded = function(data){
            $scope.teamAdminData.teams = data.teams;
        }

        var loadTeams = function(){
            teamDao.getTeams(teamsLoaded, failure);
        }

        var deselectAllTeams = function(){
            $scope.teamAdminData.selectedTeam = undefined;
            $scope.teamAdminData.teams = _.map($scope.teamAdminData.teams,function(team){ team.selected = false; return team;});
        };

        $scope.selectTeam = function(index){
           deselectAllTeams();
           $scope.teamAdminData.selectedTeam =  $scope.teamAdminData.teams[index];
           $scope.teamAdminData.teams[index].selected = true;
           $scope.teamAdminData.createTeam = undefined;
           loadMembers();
        };

        $scope.newTeam = function(){
            deselectAllTeams();
            $scope.teamAdminData.selectedTeam = undefined;
            $scope.teamAdminData.createTeam = {};
        }

        var success = function(){
            loadTeams();
        };

         var teamCreated = function(){
            $rootScope.$broadcast('TeamCreated', $scope.teamAdminData.selectedTeam);
            loadTeams();
         };



        var failure = function(failure){
            alert("Failure");
        };

        $scope.createTeam = function(){
            teamDao.saveTeam($scope.teamAdminData.createTeam, teamCreated, failure);
            $scope.teamAdminData.createTeam = undefined;
        };

        $scope.saveTeam = function(){
            teamDao.saveTeam($scope.teamAdminData.selectedTeam, success, failure);
            deselectAllTeams();
        };

        $scope.deleteTeam = function(){
            teamDao.deleteTeam($scope.teamAdminData.selectedTeam, success, failure);
            deselectAllTeams();
        };



        var userLoaded = function(data){
            $scope.teamAdminData.members.push(_.pick(data,'userName', 'firstName', 'lastName', 'email', 'phoneNumber'));
        };

        var loadMembers = function(){
            membershipDao.getMemberships($scope.teamAdminData.selectedTeam, membersLoaded, failure);
        };


        var membersLoaded = function(data){
            var users = _.map(data.memberships, function(membership){
                            return membership.userName;
                        });

            $scope.teamAdminData.members = [];
            _.each(users, function(user){
                userDao.getUser(user, userLoaded, failure)
            });
        };

         $scope.getUsers = function(value){
                    return $http.get('services/v1/user?partOfName=' + value, {})
                        .then(function(response){
                            return  _.reduce(response.data.users, function(memo, user){
                                memo.push(user.userName);
                                return memo;
                            }, []);
                    });
         };

         var userAdded = function(data){
            loadMembers();
         };

         $scope.addUserToTeam = function(){
            membershipDao.createMembership($scope.teamAdminData.selectedTeam, $scope.teamAdminData.userToAdd, userAdded, failure)
         };


         $scope.removeUserFromTeam = function(){
            _.each($scope.gridOptions.selectedItems, function(membership){
                membershipDao.deleteMembership($scope.teamAdminData.selectedTeam, membership.userName, function(){
                   $scope.teamAdminData.members = _.filter($scope.teamAdminData.members, function(member){ return member.userName !== membership.userName;})
                }
                , failure)
            });
            $scope.gridOptions.selectedItems.length = 0;

         };

         $scope.teamAdminData.members = [];
         $scope.gridOptions = {
                                data: 'teamAdminData.members',
                                showFilter : true,
                                selectedItems: [],
                                keepLastSelected: false
                              };

         loadTeams();


    }]);


    adminControllers.controller('AdminUserController', ['$scope','userDao', function($scope, userDao) {
        $scope.userAdminData = {};



        var usersLoaded = function(data){
            $scope.userAdminData.users = data.users;
        }

        var loadUsers = function(){
            userDao.getUsers(usersLoaded, failure);
        }

        var deselectAllUsers = function(){
            $scope.userAdminData.selectedUser = undefined;
            $scope.userAdminData.users = _.map($scope.userAdminData.users,function(user){ user.selected = false; return user;});
        };

        $scope.selectUser = function(index){
           deselectAllUsers();
           $scope.userAdminData.selectedUser =  $scope.userAdminData.users[index];
           $scope.userAdminData.users[index].selected = true;
           $scope.userAdminData.createUser = undefined;
        };

        $scope.newUser = function(){
            deselectAllUsers();
            $scope.userAdminData.selectedUser = undefined;
            $scope.userAdminData.createUser = {};
        }

        var success = function(){
            loadUsers();
        };

        var failure = function(failure){
            alert("Failure");
        };

        $scope.createUser = function(){
            userDao.saveUser($scope.userAdminData.createUser, success, failure);
            $scope.userAdminData.createUser = undefined;
        }

        $scope.saveUser = function(){
            userDao.saveUser($scope.userAdminData.selectedUser, success, failure);
            deselectAllUsers();
        }

        $scope.deleteUser = function(){
            userDao.deleteUser($scope.userAdminData.selectedUser, success, failure);
            deselectAllUsers();
        }

        loadUsers();

    }]);

    adminControllers.controller('TeamOverviewController', ['$scope','todoService', function($scope, todoService) {
        alert(todoService.getTeamToOverview().teamName);

    }]);


    adminControllers.controller('BoardController', ['$scope', function($scope) {


    }]);

    return adminControllers;





});