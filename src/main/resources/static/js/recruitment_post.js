var app = angular.module('recruitmentPostApp', []);
app.controller('recruitmentPostCtrl', function($scope, $filter){
    $scope.recruiters = [];

    $scope.getRecruiters = function() {
        $.ajax({
            url: 'http://localhost:8080/api/recruiters'
        }).then(function(data) {
            $scope.recruiters = data;
            $scope.$apply()
        })
    }
    
    $scope.sendOnRecruitment = function() {
        recruiterId = 1;
        $.ajax({
            url: 'http://localhost:8080/api/sendOnRecruitment/' + recruiterId
        }).then(function(data) {
            $filter('filter')($scope.recruiters, {id: recruiterId})[0].onRecruitmentUntil = data.onRecruitmentUntil;
            $filter('filter')($scope.recruiters, {id: recruiterId})[0].unavailableUntil = data.unavailableUntil;
            $scope.$apply()
        })
    }

    $scope.init = function () {
        $scope.getRecruiters();
    }

    $scope.init();
});