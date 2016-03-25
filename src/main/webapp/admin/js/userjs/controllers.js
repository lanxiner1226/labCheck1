var labCheckControllers = angular.module("labCheckControllers",
		["ui.bootstrap","dataServices"]);

labCheckControllers.controller("loginController",
		["$scope","dataService","$window","$controller",
        function($scope,dataService,$window,$controller){
			angular.extend(this,$controller("BaseController",{$scope:$scope}));
			
			$scope.signin = function(){
				var username = document.getElementById("username").value;
				
				var pwd = document.getElementById("pwd").value;
			
				
				dataService.login(username,pwd).success(function(data){
					if(data.resultCode == 1&&data.role == 3){
						$window.location.href="../admin/admin.html#/manage/show";
					}else{
						$scope.status = "登录失败，请确认您的用户名或密码是否正确";
						$scope.addAlert("danger",$scope.status);
					}
					
				}).error(function(error,status){
					$scope.status = "登录失败";
					$scope.addAlert("danger",$scope.status);
				})
					
				
				
			}
			
		}])
		
labCheckControllers.controller("WrapperController",
	    ["$scope","$location","$window","dataService",
	     function($scope,$location,$window,dataService){
	    	dataService.getUserInfo().success(function(data){
	    		if(isSessionTimeout(data)){
	    			relogin();
	    		}
	    		$scope.userInfo = data;
	    		
	    	}).error(function(error,status){
	    		errorHandler(error, status);
	    	});
	    	
	    	$scope.$location = $location;
	    	
	    	$scope.navItems = [
	    	  {path:"/manage/show",title:"管理"},
	    	  {path:"/search/show",title:"查询签到记录"}
	    	                  ]
	    	  //判断条目是否需要激活
	        $scope.isActive = function(item) {
	          if (item.path === $location.path()) {
	              return true;
	          }  
	          
	          return false;  
	        };
	        
	        //隐去或展现左边栏
			$scope.toggleSidebar = function() {
				$scope.boolChangeClass = !$scope.boolChangeClass;
			};
			
			  //注销退出
	        $scope.logout = function() {
	            if (!$window.confirm("您确定要退出么？")) {
	                return;
	            }
	            
	            /*dataService.logout()
	                    .success(function(data){
	                        $window.location.href="/admin/login.html";
	                    })
	                    .error(function(error,status){
	                        $window.location.href="/admin/login.html";
	                    });
	                    */
	            $window.location.href="../admin/login.html";
	        };
	    	
	    }])

labCheckControllers.controller("ManageController",["$scope","dataService",
                    function($scope,dataService){
	      $scope.teachers = [];
	      dataService.getTeachers().success(function(data){
	    	  $scope.teachers = data;
	      }).error(function(error,status){
	    	  errorHandler(error, status);
	      })
}])

labCheckControllers.controller("AddTeacherController",["$scope","dataService","$controller",
                    function($scope,dataService,$controller){
	        angular.extend(this,$controller("BaseController",{$scope:$scope}));
	        
	        $scope.isTeaSave = false;
	        
	        $scope.cleanTeacherInput = function(){
	        	$scope.teacher_username = "";
	        	$scope.teacher_name = "";
	        	$scope.teacher_pwd = "";
	        }
	        
	        $scope.submitAddTeacher = function(username,name,pwd){
	        	
	        	if(isEmpty(username) || isEmpty(name) || isEmpty(pwd)){
	        		$scope.addAlert("danger","请完整设置该教师信息!");
	        		return ;
	        	}
	        	
	        	$scope.isTeaSave = true;
	        	
	        	dataService.addTeacher(username.trim(),name.trim(),pwd)
	        	           .success(function(data){
	        	        	   if(isSessionTimeout(data)){
	        	        		   relogin();
	        	        	   }
	        	        	   if(data.resultCode == 1){
	        	        		   $scope.status = "新增教师成功！";
	        	        		   $scope.addAlert("success",$scope.status);
	        	        		   $scope.cleanTeacherInput();
	        	        	   }else {
	        	        		   $scope.status = "该教师工号已存在，不允许重复添加!";
	        	        		   $scope.addAlert("danger",$scope.status);
	        	        	   }
	        	        	   
	        	        	   $scope.isTeaSave = false;
	        		
	        	}).error(function(error,status){
	        		errorHandler(error, status);
	        	})
	        }
}])

labCheckControllers.controller("AddStudentController",["$scope","dataService","$controller",
                    function($scope,dataService,$controller){
	            
	           angular.extend(this,$controller("BaseController",{$scope:$scope}));
	           
	           $scope.isStuSave = false;
	           $scope.cleanStudentInput = function(){
	        	   $scope.student_username = "";
	        	   $scope.student_name = "";
	        	   $scope.student_pwd = "";
	           }
	           
	           $scope.submitAddStudent = function(username,name,pwd){
	        	   if(isEmpty(username) || isEmpty(name) || isEmpty(pwd)){
	        		   $scope.status = "请完整设置该学生信息!";
	        		   $scope.addAlert("danger",$scope.status);
	        		   return ;
	        	   }
	        	   $scope.isStuSave = true;
	        	   
	        	   dataService.addStudent(username.trim(),name.trim(),pwd)
	        	               .success(function(data){
	        	            	   if(isSessionTimeout(data)){
	        	            		   relogin();
	        	            	   }
	        	            	   
	        	            	   if(data.resultCode == 1){
	        	            		   $scope.status = "新增学生成功！";
	        	            		   $scope.addAlert("success",$scope.status);
	        	            		   $scope.cleanStudentInput();
	        	            	   }else{
	        	            		   $scope.status = "该学生学号已存在，不能重复添加！";
	        	            		   $scope.addAlert("danger",$scope.status);
	        	            	   }
	        	            	   
	        	            	   $scope.isStuSave = false;
	        	               })
	        	               .error(function(error,status){
	        	            	   errorHandler(error, status);
	        	            	   
	        	               })
	        	   
	           }
}])


labCheckControllers.controller("SearchAllStudent",["$scope","dataService",
                    function($scope,dataService){
	$scope.students = [];
	
	dataService.getStudents().success(function(data){
		 $scope.students = data;
	}).error(function(error,status){
		errorHandler(error, status);
	})
}])


var DetailModalController = function ($scope, $modalInstance, item, audio) {
        $scope.item = item;
        $scope.isIe = isIE();
        
        $scope.ok = function (selected) {
          $modalInstance.close(selected);
        };

        $scope.cancel = function () {
          $modalInstance.dismiss('cancel');
        };
        
        $scope.playme = function(songPath) {
          audio.play(getStaticFileURLPrefix() + songPath);    
        };
};

labCheckControllers.controller("AllTeaStudentController",["$scope","dataService","$routeParams","$window","$modal",
                   function($scope,dataService,$routeParams,$window,$modal){
	$scope.teacherId = $routeParams.teacherId;
	$scope.teacherName = $routeParams.teacherName;
	
	$scope.teaStudents = [];
	
	dataService.getRelationStudents($scope.teacherId)
	           .success(function(data){
	        	   if(data.resultCode == 1){
	        		   $scope.teaStudents = data.students;
	        	   }
	           }).error(function(error,status){
	        	   errorHandler(error, status);
	           })
	           
	 $scope.deleteRelation = function(index,teacherId,studentId){
	
//		var modalInstance = $modal.open({
//			templateUrl:"partials/queryDelete.html",
//			controller:DetailModalController,
//			
//			
//		});
//		modalInstance.result.then();
//		
//		modalInstance.result.then(
//			function(){
//				dataService.delRelation(teacherId,studentId)
//		           .success(function(data){
//		        	   if(data.resultCode == 1){
//		        		   $scope.teaStudents.splice(index,1);
//		        	   }else{
//		        		   alert("解除关联失败！");
//		        	   }
//		           })
//			},function(){}
//				);
		
//		if(!$window.confirm("您确定要解除此教师 --学生关联么？")){
//			return ;
//		}
		smoke.confirm("信息提示<br/><br/>您确定要解除此教师 --学生关联么？",function(e){
			if(e){
				dataService.delRelation(teacherId,studentId)
		        .success(function(data){
		     	   if(data.resultCode == 1){
		     		   $scope.teaStudents.splice(index,1);
		     	   }else{
		     		   alert("解除关联失败！");
		     	   }
		        })
			}
		
		},{
			reverseButtons:true,
			ok:"确认",
			cancel:"取消"
		})
		
//		dataService.delRelation(teacherId,studentId)
//        .success(function(data){
//     	   if(data.resultCode == 1){
//     		   $scope.teaStudents.splice(index,1);
//     	   }else{
//     		   alert("解除关联失败！");
//     	   }
//        })
		
	}
}])
                                                          
  


labCheckControllers.controller("SearchController",["$scope","dataService",
                    function($scope,dataService){
	var div = document.getElementById("table");
	$scope.teachersRecord = function(){
		dataService.getTeachersRecord().success(function(data){
			var allRecords = data.dayRecords;
			var table = "<table class='table table-hover'>";
			table += "<caption><h3>所有老师签到记录</h3></caption>";
				table += "<thead>";
				table += "<tr>";
				table += "<th></th>";
			    table += "<th>教师姓名</th>";
			    table += "<th>打卡时间</th>";
			    table += "</tr>";
			    table += "</thead>"
			    table += "<tbody>"
			    	var count =  1;
			     for(var i in allRecords){
			    	  var username = allRecords[i].name;
			    	  var teacherRecords = allRecords[i].records;
//			    table += "<tr ng-repeat='record in teacherRecord'>";
//			    table +="<td>" + '{{$index+1}}'+ "</td>";
//			    table += "<td>" + username + "</td>";
//			    table += "<td>" + record.recordTime + "</td>";
//			    table += "</tr>";
			    
			    	 for(var j in teacherRecords){
			    		 table += "<tr>"
			    		 table += "<td>" + (count++) + "</td>";
				    	 table +="<td>" + username + "</td>";
			    		 table += "<td>" + teacherRecords[j].recordTime + "</td>";
				    	 table += "</tr>";
			    	 }
			    		 
			     }
			   table += "</tbody>";
			   table += "</table>";
			   div.innerHTML = table;
		})
	}
	
	
	$scope.studentsRecord = function(){
		dataService.getStudentsRecord().success(function(data){
			var allRecords = data.dayRecords;
			var table = "<table class='table table-hover'>";
			    table += "<caption><h3>所有学生签到记录</h3></caption>";
				table += "<thead>";
				table += "<tr>";
				table += "<th></th>";
			    table += "<th>学生姓名</th>";
			    table += "<th>打卡时间</th>";
			    table += "</tr>";
			    table += "</thead>"
			    table += "<tbody>"
			    	var count =  1;
			     for(var i in allRecords){
			    	  var username = allRecords[i].name;
			    	  var teacherRecords = allRecords[i].records;
//			    table += "<tr ng-repeat='record in teacherRecord'>";
//			    table +="<td>" + '{{$index+1}}'+ "</td>";
//			    table += "<td>" + username + "</td>";
//			    table += "<td>" + record.recordTime + "</td>";
//			    table += "</tr>";
			    
			    	 for(var j in teacherRecords){
			    		 table += "<tr>"
			    		 table += "<td>" + (count++) + "</td>";
				    	 table +="<td>" + username + "</td>";
			    		 table += "<td>" + teacherRecords[j].recordTime + "</td>";
				    	 table += "</tr>";
			    	 }
			    		 
			     }
			   table += "</tbody>";
			   table += "</table>";
			   div.innerHTML = table;
		})
	}
}])


labCheckControllers.controller("AddTeaStudentController",["$scope","dataService","$controller","$routeParams","$window",
                   function($scope,dataService,$controller,$routeParams,$window){
angular.extend(this,$controller("BaseController",{$scope:$scope}));
	
	$scope.teacherId = $routeParams.teacherId;
	$scope.teacherName = $routeParams.teacherName;
	
	$scope.allStudents = [];
    $scope.alreadyStudents = [];
    $scope.students = [];
    
    dataService.getStudents().success(function(data){
		 $scope.students = data;
		
	})
   
    
    dataService.getRelationStudents($scope.teacherId).success(function(data){
    	if(data.resultCode == 1){
    	   $scope.alreadyStudents = data.students;
    	 
         for(var i=0;i<$scope.students.length;i++){
             var flag = true;
             for(var j=0;j<$scope.alreadyStudents.length;j++){
        		 if($scope.students[i].id == $scope.alreadyStudents[j].id){
        		    	flag = false;
        		    	}
        		    }
             if(flag){
      		    $scope.allStudents.push($scope.students[i]);
      		    	 }
        		
        		    	 
        		    }
        }
     if(data.resultCode == 0){
    		$scope.allStudents = $scope.students;
    	}
        	
        
  
})
	

	$scope.change = function(studentId,username){
		if(username != null || username != ""){
//			if(!$window.confirm("您确定要为" + $scope.teacherName + "老师新增学号为" + username + "的学生吗？")){
//				return ;
//			}
			smoke.confirm("操作提示<br/><br/>您确定要为" + $scope.teacherName + "老师新增学号为" + username + "的学生吗？",function(e){
				if(e){
					dataService.addRelation($scope.teacherId,studentId).success(function(data){
						if(data.resultCode == 1){
							
							refresh();
							$scope.status = "关联学生成功！";
							$scope.addAlert("success",$scope.status);
							//goBack();
						}else{
							$scope.status = "该老师---学生关联关系已存在，请不要重复添加！";
							$scope.addAlert("danger",$scope.status);
						}
					}).error(function(error,status){
						errorHandler(error, status);
					})
				}else{
					refresh();
				}
				
			},{
				reverseButtons:true,
				ok:"确认",
				cancel:"取消"
			})
//			dataService.addRelation($scope.teacherId,studentId).success(function(data){
//				if(data.resultCode == 1){
//					$scope.status = "关联学生成功！";
//					$scope.addAlert("success",$scope.status);
//					refresh();
//					//goBack();
//				}else{
//					$scope.status = "该老师---学生关联关系已存在，请不要重复添加！";
//					$scope.addAlert("danger",$scope.status);
//				}
//			}).error(function(error,status){
//				errorHandler(error, status);
//			})
			
		}
	}
  
    
    
}])



labCheckControllers.controller("BaseController",["$scope",function($scope){
	$scope.alerts = [];
	$scope.addAlert = function(statusType,status){
		if($scope.alerts.length > 0){
			$scope.closeAlert(0,1);
		}
		$scope.alerts.push({"statusType":statusType,"status":status});
	}
	
	$scope.closeAlert = function(index){
		$scope.alerts.splice(index,1);
	}
}])