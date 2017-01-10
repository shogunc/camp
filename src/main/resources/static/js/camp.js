app.registerCtrl('campCtrl', function($scope, campService){
    $scope.buildings = [];

    $scope.getBuildings = function() {
        $.ajax({
            url: 'http://localhost:8080/api/buildings'
        }).then(function(data) {
            $scope.buildings = data;
            $scope.$apply()
        })
    };

    $scope.goToBuilding = function(building) {
        campService.set(building);
        window.location.href = '#/' + building.buildingType.toLowerCase();
    };

    $scope.init = function () {
        $scope.getBuildings();
    };

    $scope.init();
});