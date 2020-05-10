

function executeAjax() {
	 'use strict';
	 var key = location.search.substring(3);
	 var requestQuery = {
				shain_id : key ,
			};
	$.ajax({
		type : 'GET',
		dataType : 'json',
		url : '/BasicCheck/editDisplayShainServlet',
		data : requestQuery,
		async: false,
		success : function(json) {
			// サーバーとの通信に成功した時の処理

//				console.log(json);
				$('#shainInformation').append('<p> 社員ID：<input type="text" id="shain_id" value="'+json.shain_id+'"/></p>');
				$('#shainInformation').append('<p> 名前：<input type="text" id="shain_name" value="'+json.shain_name+'"/></p>');
				$('#shainInformation').append('<p> 年齢：<input type="text" id="age" value="'+json.age+'"/></p>');
				$('#shainInformation').append('<p> 性別：<input type="text" id="sex" value="'+json.sex+'"/></p>');
				$('#shainInformation').append('<p> 住所：<input type="text" id="home" value="'+json.home+'"/></p>');
				$('#shainInformation').append('<p> 所属：<input type="text" id="ShainDepartmentName" value="'+json.shainDepartmentName+'"/></p>');

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// サーバーとの通信に失敗した時の処理
			alert('displayするときにデータの通信に失敗しました');

			console.log("XMLHttpRequest : " + XMLHttpRequest.status);
			console.log("textStatus     : " + textStatus);
			console.log("errorThrown    : " + errorThrown.message);
			console.log(errorThrown)
		}
	});
}

function remakeShain(){


	var key = location.search.substring(3);
	var requestQuery = {
	shain_id : key ,
	renew_shain_id : $('#shain_id').val(),
	renew_shain_name : $('#shain_name').val(),
	renew_age : $('#age').val(),
	renew_sex : $('#sex').val(),
	renew_home : $('#home').val(),
	renew_Shain_DepartmentName : $('#ShainDepartmentName').val(),
};
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : '/BasicCheck/editShainServlet',
		data : requestQuery,
		async: false,
		success : function(json) {
			// サーバーとの通信に成功した時の処理
			// 確認のために返却値を出力
			console.log(json);
			location.href='./Shain.html';
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// サーバーとの通信に失敗した時の処理
			console.log("XMLHttpRequest : " + XMLHttpRequest.status);
			console.log("textStatus     : " + textStatus);
			console.log("errorThrown    : " + errorThrown.message);
			console.log(errorThrown)
		}
	});
}


$(document).ready(function() {
	// 初期表示用
	executeAjax();
	$('#remakeShainButton').click(remakeShain);

});