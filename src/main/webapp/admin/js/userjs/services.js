var dataServices = angular.module("dataServices",[]);

dataServices.config(function($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
 
    // Override $http service's default transformRequest
    $httpProvider.defaults.transformRequest = [function(data) {
        /**
         * The workhorse; converts an object to x-www-form-urlencoded serialization.
         * @param {Object} obj
         * @return {String}
         * @author http://my.oschina.net/buwei/blog/191640
         */
        var param = function(obj) {
            var query = '';
            var name, value, fullSubName, subName, subValue, innerObj, i;
 
            for (name in obj) {
                value = obj[name];
 
                if (value instanceof Array) {
                    for (i = 0; i < value.length; ++i) {
                        subValue = value[i];
                        fullSubName = name + '[' + i + ']';
                        innerObj = {};
                        innerObj[fullSubName] = subValue;
                        query += param(innerObj) + '&';
                    }
                } else if (value instanceof Object) {
                    for (subName in value) {
                        subValue = value[subName];
                        fullSubName = name + '[' + subName + ']';
                        innerObj = {};
                        innerObj[fullSubName] = subValue;
                        query += param(innerObj) + '&';
                    }
                } else if (value !== undefined && value !== null) {
                    query += encodeURIComponent(name) + '='
                            + encodeURIComponent(value) + '&';
                }
            }
 
            return query.length ? query.substr(0, query.length - 1) : query;
        };
 
        return angular.isObject(data) && String(data) !== '[object File]'
                ? param(data)
                : data;
    }];
});

dataServices.factory("dataService",["$http",function($http){
	var userUrlBase = "../user";
	
	var recordUrlBase = "../record";
	
	var services = {};
	
	services.login = function(username,pwd){
		 return $http.post(userUrlBase + "/login",
				 {"username":username,"pwd":pwd}
		 );
	}
	
	services.getUserInfo = function(){
		return $http.get(userUrlBase + "/me");
	}
	
	services.getTeachers = function(){
		return $http.post(userUrlBase + "/getTeachers");
	}
	
	services.addTeacher = function(username,name,pwd){
		return $http.post(userUrlBase + "/newTeacher",
				{"username":username,"name":name,"pwd":pwd});
	}
	
	services.addStudent = function(username,name,pwd){
		return $http.post(userUrlBase + "/newStudent",
				{"username":username,"name":name,"pwd":pwd})
	}
	
	services.getStudents = function(){
		return $http.post(userUrlBase + "/getStudents");
	}
	
	services.getRelationStudents = function(teacherId){
	  return $http.post(userUrlBase + "/getRelationStudent",
			  {"teacher_id":teacherId});	
	}
	
	services.delRelation = function(teacherId,studentId){
		return $http.post(userUrlBase + "/delRelation",
				{"teacher_id":teacherId,"student_id":studentId})
	}
	
	services.addRelation = function(teacherId,studentId){
		return $http.post(userUrlBase + "/newRelation",
				{"teacher_id":teacherId,"student_id":studentId});
	}
	
	services.getStudentsRecord = function(){
		return $http.post(recordUrlBase + "/getAllStudentDayRecord");
	}
	
	services.getTeachersRecord = function(){
		return $http.post(recordUrlBase + "/getAllTeacherDayRecord");
	}
	
	return services;
}])