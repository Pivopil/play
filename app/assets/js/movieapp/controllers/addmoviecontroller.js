'use strict';

movieApp.controller('AddMovieController', ['$scope', '$modalInstance', 'favouriteLists',
    function ($scope, $modalInstance, favouriteLists) {


        $scope.favouriteLists = favouriteLists;
        $scope.value = favouriteLists.length > 0 ? favouriteLists[0] : {};


        $scope.ok = function () {
            $modalInstance.close($scope.value);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }]);