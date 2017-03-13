<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<script type="text/javascript" src="js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript">
	function submitForm(){
		var uname = $("#uname").val();
		var rname = $("#rname").val();
		var upwd = $("#upwd").val();
		var rpwd = $("#rpwd").val();
		if(""==uname || ""==rname || ""==upwd || ""==rpwd){
			alert("请填入完整信息！");
			return;
		}
		if(upwd != rpwd){
			alert("两次密码输入不匹配！");
			return;
		}
		$.ajax({
			type : "POST",
			url : "register",
			data : {
				username : uname,
				realname : rname,
				pwd : upwd
			},
			success : function(data){
				alert(data.msg);
			},
			dataType : "json"
		});
	}
	
	function queryForm(){
		$.ajax({
			type : "POST",
			url : "query",
			success : function(data){
				if(undefined!=data || null != data){
					var str = '<table border="1" cellspacing="0" rowspacing="0">';
					str += '<tr id="tbRow"><th>用户ID</th><th>用户名</th><th>姓名</th></tr>';
					for(i=0;i<data.length;i++){
						str +="<tr><td>"+data[i].id+"</td><td>"+data[i].username+"</td><td>"+data[i].realname+"</td></tr>";
					}
					$("#tbRow").empty();
					$("#tbRow").append(str).append("</table>");
					$("#userlist").show();
				}
			},
			dataType : "json"
		});
	}
</script>
</head>
<body>
<h2>用户注册</h2>
<div>
<div>&nbsp;用户名：<input id="uname" name="uname" type="text" placeholder="请输入用户名" maxlength="20" /></div>
<div>姓&nbsp;&nbsp;名：<input id="rname" name="rname" type="text" placeholder="请输入用户名" maxlength="20" /></div>
<div>密&nbsp;&nbsp;码：<input id="upwd" name="upwd" type="password" placeholder="请输入密码" maxlength="20" /></div>
<div>确认密码：<input id="rpwd" name="rpwd" type="password" placeholder="请再次确认密码" maxlength="20" /></div>
<div><input name="uname" type="button" value="注册" onclick="submitForm()" maxlength="20" />
<input name="uname" type="button" value="查看用户列表" onclick="queryForm()" maxlength="20" /></div>
</div>
<div id="userlist" style="display:none">
<h2>用户列表</h2>
<div id="tbRow">
</div>
</div>
</body>
</html>
