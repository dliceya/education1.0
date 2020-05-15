/**
 * 
 * @authors dlice (158156325@qq.com)
 * @date    2020-03-09 19:12:08
 * @version $Id$
 */
//登录逻辑验证
var app = new Vue({
    el: "#app",
    data: {
        isShow: false,
        success: false,
        username: '',
        password: '',
        message: ''
    },
    computed: {
        messClass: function() {
            return {
            	'alert': this.isShow,
            	'alert-success': this.isShow && this.success,
            	'alert-danger': this.isShow && !this.success,
            }
        }
    },
    methods: {
        login12: function() {
        	//使用用户输入的用户名和密码来构造请求体
            var postData = {
            	"username": this.username,
                "password": this.password
            };
            var domain = document.domain;//document.domain;' + domain + 
            axios.post('http://localhost:40400/auth/userlogin', postData)
                .then(res => {
                	var response = res.data;
                    if(response.success){
                    	this.success = true;
                    }
                    else {
                    	this.success = false;
                    }
                    this.message = response.message;
                    this.isShow = true;
                    document.cookie="uid"+ res.jti;  
                    //window.location.href = 'http://www.baidu.com';
                })
                .catch(err => {
                    this.message = err;
                })
        }
    }
})




//         }
//     }
// })
// function login11() {
//
// 	var username = $("#username").val();
// 	var password = $("#password").val();
//
// 	var domain = document.domain;
//
//     $.ajax({
//     	type: 'post',
//     	url: 'http://' + domain + ':10001/ucenter/login',
//     	headers: {
//     		"content-Type": "application/json;charset=utf-8",
//     		"Access-Control-Allow-Origin":'*'
//     	},
//     	data: JSON.stringify({
//     		"username": username,
//     		"password": password
//     	}),
//     	dataType: 'json',
//     	success: function (data) {
//     		console.log(data);
//     		alert(data);
//
//     		// var result = JSON.parse(data);
//     		if(data.success == true){
//     			document.location.href = '../manage/user.html';
//     		} else {
//     			var message = data.message;
//     			alert(message);
//     		}
//     	},
// 		fail:function (r) {
// 			alert(111);
// 		}
//     })
// }
//