'use strict';

movieApp.controller('ListsController', ['$scope', 'favouriteLists', function ($scope, favouriteLists) {

    $scope.name = '';
    $scope.favouriteLists = favouriteLists;

    $scope.removeList = function (favouriteList) {
        var index = $scope.favouriteLists.indexOf(favouriteList);
        favouriteList.remove().then(function () {
            $scope.favouriteLists.splice(index, 1);
        });
    };

    $scope.createList = function (name) {
        $scope.favouriteLists.post({"name": name}).then(function (result) {
            $scope.favouriteLists.push(result);
            $scope.name = '';
        });
    };

}]);