var department_count = 0 ;

function executeAjax() {
	 'use strict';
	 var requestQuery = 0;
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
				$('#DepartmentTable').append('<tr><td>' + d.department_id + '</td><td>' + d.department_name + '</td><td>'
				+'<button value="'+(i+1)+'" id="edit'+(i+1)+'">編集</button>'+ '</td><td>'
				+'<button value="'+(i+1)+'" id="delete'+(i+1)+'">削除</button>'+'</td></tr>');
				department_count++;
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

function deleteDepartment(){
	var requestQuery = {
	q : $('#delete').val()
};
	console.log("a");
	$.ajax({
		type : 'GET',
		dataType : 'json',
		url : '/BasicCheck/deleteDepartmentServlet',
		data : requestQuery,
		success : function(json) {
			// サーバーとの通信に成功した時の処理
			// 確認のために返却値を出力
			console.log(json);
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
	executeAjax();//再表示
}


$(document).ready(function() {

	// 初期表示用
	executeAjax();
	$('#DepartmentTable').ready('load', executeAjax);
	// 更新ボタンにイベント設定
	for(var i=1;i<=department_count;i++){
	$('#delete'+i).click(deleteDepartment);
	}

});