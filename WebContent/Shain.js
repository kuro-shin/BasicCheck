var shain_count=0;

function executeAjax() {
	 'use strict';
	 var requestQuery = 1;
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

function deleteShain(id){
	 $(".shain_list").remove();// 今までのdepartmentlist消す
	var requestQuery = {
	q : id
};
	console.log(id);
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : '/BasicCheck/deleteShainServlet',
		data : requestQuery,
		async: false,
		success : function(json) {
			// サーバーとの通信に成功した時の処理
			// 確認のために返却値を出力
			console.log(json);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// サーバーとの通信に失敗した時の処理
			//alert('deleteするときにデータの通信に失敗しました');

			console.log("XMLHttpRequest : " + XMLHttpRequest.status);
			console.log("textStatus     : " + textStatus);
			console.log("errorThrown    : " + errorThrown.message);
			console.log(errorThrown)
		}
	});
	executeAjax();// 再表示
}

$(document).ready(function() {
	// 初期表示用
	executeAjax();

});