var app = angular.module('campApp', []);
app.controller('campCtrl', function($scope){
    $scope.buildings = [];

    $scope.getBuildings = function() {
        $.ajax({
            url: 'http://localhost:8080/api/buildings'
        }).then(function(data) {
            $scope.buildings = data;
            $scope.$apply()
        })
    }

    $scope.init = function () {
        $scope.getBuildings();
    }

    $scope.init();
});