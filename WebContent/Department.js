
function executeAjax() {
	 'use strict';
	var requestQuery = {
		q : $('#q').val()
	};
	// console.dir(requestQuery);
	$.ajax({
		type : 'GET',
		dataType : 'json',
		url : '/BasicCheck/displayDepartmentServlet',
		data : requestQuery,
		success : function(json) {
			// サーバーとの通信に成功した時の処理
			// 確認のために返却値を出力
			console.log(json);

			for (var i = 0; i < json.length; i++) {
				var d = json[i];
				$('#DepartmentTable').append('<tr><td>' + d.Department_id + '</td><td>' + d.Department_name + '</td><td>'
								+ '<input id>' + '</td><td></td></tr>');
			}

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// サーバーとの通信に失敗した時の処理
			alert('データの通信に失敗しました');
			alert('error!!!');
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
	$('#DepartmentTable').ready('load', executeAjax);
	// 更新ボタンにイベント設定
	// $('#').ready('click', );

});