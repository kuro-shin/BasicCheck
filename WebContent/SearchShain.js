var shain_count=0;

function displayShain() {
	 'use strict';
	 var requestQuery = {
			 shainDepartmentName:DEPARTMENT_NAME,
			 shainId:ID,
			 shainName:NAME

	 };

	$.ajax({
		type : 'GET',
		dataType : 'json',
		url : '/BasicCheck/searchShainServlet',
		data : requestQuery,
		async: false,
		success : function(json) {
			// サーバーとの通信に成功した時の処理
			// 確認のために返却値を出力
			shain_count=json.length;

			for (var i = 0; i < json.length; i++) {
				var s = json[i];
				$('#shainTable').append('');
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
	$('#').click(searchShain);

});