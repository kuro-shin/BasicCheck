var shain_count = 0;

function searchShain() {
	'use strict';
	var requestQuery = {
		shainDepartmentName : $('#shainDepartmentName').val(),
		shainId : $('#shainId').val(),
		shainName : $('#shainName').val()
	};

	$.ajax({
		type : 'GET',
		dataType : 'json',
		url : '/BasicCheck/searchShainServlet',
		data : requestQuery,
		async : false,
		success : function(json) {
			// サーバーとの通信に成功した時の処理
			// 確認のために返却値を出力
			shain_count = json.length;
			$("#shainTable").empty();
			if (json.length == 0) {
				$('#shainTable').append('<p>登録された社員はいません</p>');
			}
			for (var i = 0; i < json.length; i++) {
				var s = json[i];
				$('#shainTable').append('<div class="shain_list"><p> 社員ID:' + s.shain_id + '  社員名：' + s.shain_name + '</p></div>');
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
	$('#searchShainButton').click(searchShain);

});