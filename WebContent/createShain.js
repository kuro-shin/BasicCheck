/**
 *
 */
function createShain() {
	var str = "";
	var sex = document.getElementsByName("sex");
	for (let i = 0; i < sex.length; i++) {
		if (sex[i].checked) { // (color2[i].checked === true)と同じ
			str = sex[i].value;
			break;
		}
	}

	var requestQuery = {
		shainId : $('#shainId').val(),
		shainName : $('#shainName').val(),
		shainAge : $('#shainAge').val(),
		sex : str,
		home : $('#home').val(),
		shainDepartmentName : $('#ShainDepartmentName').val(),

	};
	console.log(requestQuery);
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : '/BasicCheck/createShainServlet',
		data : requestQuery,
		async : false,
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

	$('#createShainButton').click(createShain);
});