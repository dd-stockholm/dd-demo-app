'use strict';

angular.module('dd-demo-app')
    .controller('TabsCtrl', ['$scope', function($scope) {
      $scope.tabs = [{
        title: 'Partier',
        url: '#/parties'
        }, {
          title:'Frågor',
          url: '#/questions'
        }]
    }]);