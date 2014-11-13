hrApp.controller('EmployeeDeleteController', ['$scope', '$http', '$routeParams', '$location', function($scope, $http, $routeParams, $location) {
    $http({url: 'http://localhost:8080/mvc/department/all', method: 'GET'}).
        success(function (data) {
            $scope.departments = data;
        });
    $http({url: 'http://localhost:8080/mvc/employee/all', method: 'GET'}).
        success(function (data) {
            $scope.managers = data;
        });

    $http({url: 'http://localhost:8080/mvc/job/all', method: 'GET'}).
        success(function (data) {
            $scope.jobs = data;
        });

    $http({url: 'http://localhost:8080/mvc/employee/one/'+$routeParams.employeeid, method: 'GET'}).
        success(function (data) {
            $scope.employee = data;
        });

    $scope.deleteEmployee = function() {
        $http({url: 'http://localhost:8080/mvc/employee/delete?idEmployee='+$routeParams.employeeid, method: 'DELETE'}).
            success(function (data) {
                $location.url('/employeeslist');
            });
    }
}]);