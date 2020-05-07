function registExpense(){
	$("#ExpenseTableBody").remove();// 今までのdepartmentlist消す
	var requestQuery = {
			id : $('#id').val(),
			today : $('#today').val(),
			applicant_name : $('#applicant_name').val(),
			title : $('#title').val(),
			price : $('#price').val(),
			payee : $('#payee').val(),

};
	console.log($('applicant_name').val());
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : '/BasicCheck/RegistExpenseServlet',
		data : requestQuery,
		async: false,
		success : function(json) {
			// サーバーとの通信に成功した時の処理
			// 確認のために返却値を出力
			console.log(json);
			location.href='http://localhost:8081/BasicCheck/Expense.html';

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
	$('#registExpenseBtn').click(registExpense);
});