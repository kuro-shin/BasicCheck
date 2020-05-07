function executeAjax() {
	 'use strict';
	 var key = location.search.substring(3);
	$.ajax({
		type : 'GET',
		dataType : 'json',
		url : '/BasicCheck/getExpenseServlet',
		data : requestQuery,
		async: false,
		success : function(json) {
			// サーバーとの通信に成功した時の処理
			// 確認のために返却値を出力
			var registBtnCount=0;
			for (var i = 0; i < json.length; i++) {
				var d = json[i];
				var m=d.manager;
				var l=d.loginuser;

	if(l==true||m==true){
					$('#ExpenseTableBody').append('<tr><td>'+d.code+'</td><td>'+d.today+'</td><td>'+d.updated_date
							+'</td><td>'+d.applicant_name+'</td><td>'+d.title+'</td><td>'+d.price+'</td><td>'+d.status+
							'</td><td><button onclick="location.href=\'http://localhost:8081/BasicCheck/DetailExpense.html?q='+d.code+'\'">詳細</td></tr>');

			}

	if(m!=true&&registBtnCount!=1){
		$('#registExpense').append('<button onclick="location.href=\'http://localhost:8081/BasicCheck/registExpense.html\'" >経費申請</button>');
		registBtnCount++;
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



function judgeExpense(){
	 $("#ExpenseTableBody").remove();// 今までのdepartmentlist消す
	var requestQuery = {
	q : id
};

	executeAjax();// 再表示
}



$(document).ready(function() {

	executeAjax();
	$('#registExpenseBtn').click(registExpense);
});b