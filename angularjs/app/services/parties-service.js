'use strict';

angular.module('dd-demo-app')
    .factory('PartiesService', function() {
        var service = {

            list: function() {
                return ['c', 'l', 'kd', 'm', 'mp', 's', 'sd', 'v'];
            }

        };

        return service;
    });