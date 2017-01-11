app.registerCtrl('recruitmentPostCtrl', function($scope, $filter, $timeout, $interval, $sce, campService){
    $scope.recruiters = [];
    /*
     Interval function that continually updates the status of the recruiters, 
     including countdowns when applicable.
     */
    $interval(function() {
        angular.forEach($scope.recruiters, function(recruiter) {
            currentTime = new Date().getTime();
            recruiter.millisUntilRecruitmentOver = recruiter.onRecruitmentUntil - currentTime;
            recruiter.millisUntilAvailable = recruiter.unavailableUntil - currentTime;
            if (recruiter.calculateStatus() == 'RECRUITING') {
                recruiter.statusMsg = "Out on recruitment mission";
                recruiter.actionMsg = Math.ceil(recruiter.millisUntilRecruitmentOver / 1000);
            } else if (recruiter.calculateStatus() == 'RECRUITS_WAITING') {
                recruiter.statusMsg = "Back from recruitment mission";
            } else if (recruiter.calculateStatus() == 'UNAVAILABLE') {
                recruiter.statusMsg = "Unavailable for recruitment mission";
                recruiter.actionMsg = Math.ceil(recruiter.millisUntilAvailable / 1000);
            } else {
                recruiter.statusMsg = "Available for recruitment mission";
            }
        })
    }, 1000);

    /*
    REST interface to retrieve all recruiters. After retrieval each recruiter is 
    set up such that if a countdown would run down to zero, recruiters are retrieved 
    again. (In order to get new status from server.) Run on init.
     */
    $scope.getRecruiters = function() {
        $.ajax({
            url: 'http://localhost:8080/api/recruiters'
        }).then(function(data) {
            $scope.recruiters = data;
            $scope.$apply();

            currentTime = new Date().getTime();
            angular.forEach($scope.recruiters, function(recruiter) {
                recruiter.millisUntilRecruitmentOver = recruiter.onRecruitmentUntil - currentTime;
                if (recruiter.millisUntilRecruitmentOver > 0) {
                    $timeout($scope.getRecruiters, recruiter.millisUntilRecruitmentOver);
                }
                recruiter.millisUntilAvailable = recruiter.unavailableUntil - currentTime;
                if (recruiter.millisUntilAvailable > 0) {
                    $timeout($scope.getRecruiters, recruiter.millisUntilAvailable);
                }
                recruiter.calculateStatus = function() {
                    if (recruiter.millisUntilRecruitmentOver > 0) {
                        return 'RECRUITING';
                    } else if (recruiter.status == 'RECRUITS_WAITING') {
                        return 'RECRUITS_WAITING';
                    } 
                    return recruiter.millisUntilAvailable > 0 ? 'UNAVAILABLE' : 'AVAILABLE';
                }
            })
        })
    };
    
    /*
    REST interface to send a certain recruiter out on a recruitment mission. 
    Recruiter is updated with new values, and is set up to retrieve recruiters when
    'on recruitment'-timer runs out.
     */
    $scope.sendOnRecruitment = function(recruiterId) {
        $.ajax({
            url: 'http://localhost:8080/api/sendOnRecruitment/' + recruiterId
        }).then(function(data) {
            $filter('filter')($scope.recruiters, {id: recruiterId})[0].onRecruitmentUntil = data.onRecruitmentUntil;
            $filter('filter')($scope.recruiters, {id: recruiterId})[0].unavailableUntil = data.unavailableUntil;
            $scope.$apply();

            currentTime = new Date().getTime();
            angular.forEach($scope.recruiters, function(recruiter) {
                recruiter.millisUntilRecruitmentOver = recruiter.onRecruitmentUntil - currentTime;
                if (recruiter.millisUntilRecruitmentOver > 0) {
                    $timeout($scope.getRecruiters, recruiter.millisUntilRecruitmentOver);
                }
            })
        })
    };
    
    $scope.inspectRecruits = function(recruiterId) {
        campService.set(recruiterId);
        window.location.href = "#/inspect_recruits";
    };

    $scope.init = function () {
        $scope.getRecruiters();
    };

    $scope.init();
});

app.registerCtrl('inspectRecruitsCtrl', function($scope, campService){
    $scope.recruits = [];

    $scope.getRecruitedUnits = function() {
        $.ajax({
            url: 'http://localhost:8080/api/units/recruit/' + campService.get()
        }).then(function(data) {
            $scope.recruits = data;
            $scope.$apply()
        })
    };

    $scope.init = function () {
        $scope.getRecruitedUnits();
    };

    $scope.init();
});
