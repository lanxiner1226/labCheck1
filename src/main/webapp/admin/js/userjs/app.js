var labCheck = angular.module("labCheck",
		["ngRoute","labCheckControllers",]);

labCheck.config(["$routeProvider",function($routeProvider){
	$routeProvider.
	          when("/manage/show",{
	        	  templateUrl:"partials/manage.html",
	        	  controller:"ManageController"
	          }).
	          when("/search/show",{
	        	  templateUrl:"partials/search.html",
	        	  controller:"SearchController"
	          }).
	          when("/manage/addTeacher",{
	        	  templateUrl:"partials/addTeacher.html",
	        	  controller:"AddTeacherController"
	          }).
	          when("/manage/addStudent",{
	        	  templateUrl:"partials/addStudent.html",
	        	  controller:"AddStudentController"
	          }).
	          when("/manage/searchStudent",{
	        	  templateUrl:"partials/searchStudent.html",
	        	  controller:"SearchAllStudent"
	          }).
	          when("/manage/allTeaStudent",{
	        	  templateUrl:"partials/allTeaStudent.html",
	        	  controller:"AllTeaStudentController"
	          }).
	          when("/manage/addTeaStudent",{
	        	  templateUrl:"partials/addTeaStudent.html",
	        	  controller:"AddTeaStudentController"
	          })
	
}])