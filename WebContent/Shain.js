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
			if(json=="NOT LOGIN"){
				$('#shainTableBody').append('<p>ログインしてください</p>');
			}else{
			for (var i = 0; i < json.length; i++) {
				var s = json[i];
				$('#shainTableBody').append('<tr id="shain_list'+(i+1)+'"><td id="id'+(i+1)+'">' + s.shain_id + '</td><td>' + s.shain_name + '</td><td>');
				var m=s.manager;
				var l=s.loginuser;
				if(m==true||l==true){
				$('#shain_list'+(i+1)).append('<a id="edit'+(i+1)+'" href="http://localhost:8081/BasicCheck/EditShain.html?q='+s.shain_id+'">編集</a>');
				}
				$('#shain_list'+(i+1)).append('</td><td>');

				if(m==true){
				$('#shain_list'+(i+1)).append('<input type="button" value="削除" id="delete'+(i+1)+'" onclick="deleteShain(\''+s.shain_id+'\')" >');
				}
				$('#shain_list'+(i+1)).append('</td></tr>');

				if(m==true&&l==true){
					$('#createB').append('<button id="createbutton" onclick="location.href=\'http://localhost:8081/BasicCheck/createShain.html\'">社員新規作成</button>');
				}

				if(l==true){
				$('#LoginName').append('ログインユーザー：'+s.shain_name);
				}

			}
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
	 $('#LoginName').remove();
	 $('#shainTableBody').empty();
	 $('#createB').empty();
	var requestQuery = {
	q : id
};
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : '/BasicCheck/deleteShainServlet',
		data : requestQuery,
		async: false,
		success : function(json) {
			// サーバーとの通信に成功した時の処理
			// 確認のために返却値を出力

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