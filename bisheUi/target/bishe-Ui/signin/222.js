/**
 * 
 * @authors dlice (158156325@qq.com)
 * @date    2020-03-09 19:12:08
 * @version $Id$
 */
//登录逻辑验证
function login11() {

	var username = $("#username").val();
	var password = $("#password").val();

	var domain = document.domain;

    $.ajax({
    	type: 'post',
    	url: 'http://' + domain + ':10001/user/login',
    	headers: {
    		"content-Type": "application/json;charset=utf-8",
    		"Access-Control-Allow-Origin":'*'
    	},
    	data: JSON.stringify({
    		"username": username,
    		"password": password
    	}),
    	dataType: 'json',
    	success: function (data) {
    		console.log(data);
    		alert(data);

    		// var result = JSON.parse(data);
    		if(data.success == true){
    			document.location.href = '../manage/user.html';
    		} else {
    			var message = data.message;
    			alert(message);
    		}
    	},
		fail:function (r) {
			alert(111);
		}
    })
}




