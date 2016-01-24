'use strict';

angular.module('dd-demo-app')
    .controller('TabsCtrl', ['$scope', function($scope) {
      $scope.tabs = [{
        title: 'Delegering',
        url: '#/delegation'
        }, {
          title:'Omröstning',
          url: '#/vote'
        }, {
          title:'Om oss',
          url: '#/about'
        }]
    }]);