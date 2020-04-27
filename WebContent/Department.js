var department_count=0;

function executeAjax() {
	 'use strict';
	 var requestQuery = 1;
	$.ajax({
		type : 'GET',
		dataType : 'json',
		url : '/BasicCheck/displayDepartmentServlet',
		data : requestQuery,
		async: false,
		success : function(json) {
			// サーバーとの通信に成功した時の処理
			// 確認のために返却値を出力
			department_count=json.length;
			console.log(json);
			for (var i = 0; i < json.length; i++) {
				var d = json[i];
				$('#DepartmentTable').append('<tr class="department_list"><td id="id'+(i+1)+'">' + d.department_id + '</td><td>' + d.department_name + '</td><td>'
				+'<a id="edit'+(i+1)+'"href="http://localhost:8080/BasicCheck/EditDepartment.html?q='+d.department_id+'">編集</a>'+ '</td><td>'
				+'<input type="button" value="削除" id="delete'+(i+1)+'" onclick="deleteDepartment(\''+d.department_id+'\')" ></td></tr>');
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


function createDepartment(){
	$(".department_list").remove();// 今までのdepartmentlist消す
	var requestQuery = {
	new_department_id : $('#newDepartmentId').val(),
	new_department_name : $('#newDepartmentName').val()
};
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : '/BasicCheck/createDepartmentServlet',
		data : requestQuery,
		async: false,
		success : function(json) {
			// サーバーとの通信に成功した時の処理
			// 確認のために返却値を出力
			console.log(json);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// サーバーとの通信に失敗した時の処理
			console.log("XMLHttpRequest : " + XMLHttpRequest.status);
			console.log("textStatus     : " + textStatus);
			console.log("errorThrown    : " + errorThrown.message);
			console.log(errorThrown)
		}
	});
	 executeAjax();// 再表示
}

function deleteDepartment(id){
	 $(".department_list").remove();// 今までのdepartmentlist消す
	var requestQuery = {
	q : id
};
	console.log(id);
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : '/BasicCheck/deleteDepartmentServlet',
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
	//$('#DepartmentTable').ready('load', executeAjax);
	// 更新ボタンにイベント設定
//	for(var i=1;i<=department_count;i++){
//	$('#delete'+i).click(deleteDepartment($('#id'+i).text()));
	//$('#delete'+i).click(console.log("a"));
//}
	$('#createDepartment').click(createDepartment);
});