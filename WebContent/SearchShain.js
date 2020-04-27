var shain_count=0;

function searchShain() {
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
			console.log(json);
			for (var i = 0; i < json.length; i++) {
				var s = json[i];
				$('#shainTable').append('<tr class="shain_list"><td id="id'+(i+1)+'">' + s.shain_id + '</td><td>' + s.shain_name + '</td><td>'
				+'<a id="edit'+(i+1)+'"href="http://localhost:8080/BasicCheck/Editshain.html?q='+s.shain_id+'">編集</a>'+ '</td><td>'
				+'<input type="button" value="削除" id="delete'+(i+1)+'" onclick="deleteShain(\''+s.shain_id+'\')" ></td></tr>');
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
	searchShain();

});