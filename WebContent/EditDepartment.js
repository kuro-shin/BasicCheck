
function renameDepartment(){


	var key = location.search.substring(3);
	var requestQuery = {
	renew_department_id : key ,
	renew_department_name : $('#renameDepartmentName').val()
};
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : '/BasicCheck/editDepartmentServlet',
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
}



$(document).ready(function() {

	$('#remakeDepartmentButton').click(renameDepartment);
});