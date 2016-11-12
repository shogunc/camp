var app = angular.module('quartersApp', []);
app.controller('quartersCtrl', function($scope){
    $scope.units = [];

    $scope.getUnitsByEnteredBuilding = function() {
        $.ajax({
            url: 'http://localhost:8080/api/units/byEnteredBuilding'
        }).then(function(data) {
            $scope.units = data;
            $scope.$apply()
        })
    }

    $scope.init = function () {
        $scope.getUnitsByEnteredBuilding();
    }

    $scope.init();
});