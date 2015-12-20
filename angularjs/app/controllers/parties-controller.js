'use strict';

angular.module('dd-demo-app')
    .controller('PartiesController', ['$scope', 'PartiesService', function($scope, PartiesService) {
        PartiesService.list().then(function(parties) {
            $scope.parties = parties;
        });
    }]);