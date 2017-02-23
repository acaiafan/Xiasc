<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
</head>
<body>
<input id="basePath" value="${pageContext.request.contextPath}" type="hidden">
	<form action="${pageContext.request.contextPath}/getForm.do" id="form1">
		<input name="user[0].name" value="" type="text"/> 
		<input name="user[0].age" value="" type="text"/>
		
	</form>
	<input type="button" value="增加" id="addBtn"/> 
		<input type="submit" value="确定" id="submitThis"/> 
	<script type="text/javascript">
	var num = 0;
		$("#addBtn").click(function(){
			num++;
			$("#form1").find("input[type=text]").hide();
			var newName = "<input name='user["+num+"].name' value='' type='text'/>";
			var newAge = "<input name='user["+num+"].age' value='' type='text'/>";
			$("#form1").append(newName);
			$("#form1").append(newAge);
			
		});
		
		
		
		$("#submitThis").click(function(){
			var data = $("#form1").serializeArray();
			alert(data);
			$.ajax({
				type:'get',
				url: $("#basePath").val()+"/userForm.do?userList="+data,
				success:function(result){
					alert(result);
				}
			});
			
		});
	</script>
	
</body>
</html>