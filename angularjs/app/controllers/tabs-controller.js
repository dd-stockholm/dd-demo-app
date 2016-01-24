'use strict';

angular.module('dd-demo-app')
    .controller('TabsCtrl', ['$scope', function($scope) {
      $scope.tabs = [{
          title: 'Delegering',
          url: '#/delegation',
          glyphicon: 'glyphicon glyphicon-user'
        }, {
          title:'Omröstning',
          url: '#/vote',
          glyphicon: 'glyphicon glyphicon-envelope'
        }, {
          title:'Om oss',
          url: '#/about',
          glyphicon: 'glyphicon glyphicon-info-sign'
        }]
    }]);