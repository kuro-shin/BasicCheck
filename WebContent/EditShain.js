

function executeAjax() {
	 'use strict';
	 var key = location.search.substring(3);
	 var requestQuery = {
				renew_department_id : key ,
				renew_department_name : $('#renameDepartmentName').val()
			};
	$.ajax({
		type : 'GET',
		dataType : 'json',
		url : '/BasicCheck/displayShainServlet',
		data : requestQuery,
		async: false,
		success : function(json) {
			// サーバーとの通信に成功した時の処理
			// 確認のために返却値を出力
			shain_count=json.length;
			console.log(json);
			for (var i = 0; i < json.length; i++) {
				var s = json[i];
				$('#shainInformation').append('<p> 社員ID：<input type="text" id="shain_id" value="'+s.shain_id+'"/></p>');
				$('#shainInformation').append('<p> 名前：<input type="text" id="shain_name" value="'+s.shain_name+'"/></p>');
				$('#shainInformation').append('<p> 年齢：<input type="text" id="age" value="'+s.age+'"/></p>');
				$('#shainInformation').append('<p> 性別：<input type="text" id="sex" value="'+s.sex+'"/></p>');
				$('#shainInformation').append('<p> 住所：<input type="text" id="home" value="'+s.home+'"/></p>');
				$('#shainInformation').append('<p> 所属：<input type="text" id="ShainDepartmentName" value="'+s.ShainDepartmentName+'"/></p>');
			}


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


$(document).ready(function() {
	// 初期表示用
	executeAjax();

});