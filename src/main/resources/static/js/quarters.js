app.controller('quartersCtrl', function($scope, campService){
    $scope.building = {};
    $scope.units = [];

    $scope.getUnits = function() {
        $.ajax({
            url: 'http://localhost:8080/api/units/byBuilding/' + $scope.building.id
        }).then(function(data) {
            $scope.units = data;
            $scope.$apply()
        })
    };

    $scope.init = function () {
        $scope.building = campService.get();
        $scope.getUnits();
    };

    $scope.init();
});