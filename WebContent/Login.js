function LoginCheck() {
	'use strict';
	var requestQuery = {
		LoginId : $('#LoginId').val(),
		LoginPassword : $('#LoginPassword').val(),
	};
	$.ajax({
		type : 'GET',
		dataType : 'json',
		url : '/BasicCheck/LoginServlet',
		data : requestQuery,
		async : false,
		success : function(json) {
			// サーバーとの通信に成功した時の処理

			if (Object.keys(json).length == '0') {
				$('#LoginError').empty();
				$('#LoginError').append('<p>ログインできません。</p>');
			}else{
			location.href='./Shain.html';
			}

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// サーバーとの通信に失敗した時の処理
			console.log('displayするときにデータの通信に失敗しました');
			console.log("XMLHttpRequest : " + XMLHttpRequest.status);
			console.log("textStatus     : " + textStatus);
			console.log("errorThrown    : " + errorThrown.message);
			console.log(errorThrown)
		}
	});
}

$(document).ready(function() {
	$('#LoginBtn').click(LoginCheck);
});