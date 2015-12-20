'use strict';

angular.module('dd-demo-app')
    .factory('PartiesService', ['$q', function($q) {
        var service = {
            list: function() {
                return $q(function(resolve, reject) {
                    resolve(['c', 'l', 'kd', 'm', 'mp', 's', 'sd', 'v']);
                });
            }
        };

        return service;
    }]);