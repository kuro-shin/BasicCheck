function executeAjax() {
	 'use strict';
	 var key = location.search.substring(3);
	 console.log(key);
	$.ajax({
		type : 'GET',
		dataType : 'json',
		url : '/BasicCheck/getExpenseServlet',
		data : key,
		async: false,
		success : function(json) {
			// サーバーとの通信に成功した時の処理
			// 確認のために返却値を出力
		//	var registBtnCount=0;
			for (var i = 0; i < json.length; i++) {
				console.log(json);
				var d = json[i];
				var m=d.manager;
				var l=d.loginuser;
				var fixedcode = d.code.substr(0,5);
				var status=d.status;
	if(key==fixedcode){
					$('#ExpenseTableBody').append('<tr><td>'+fixedcode+'</td><td>'+d.today+'</td><td>'+d.updated_date
							+'</td><td>'+d.applicant_name+'</td><td>'+d.title+'</td><td>'+d.price+'</td><td>'+d.status+
							'</td><td>'+d.reason+'</td></tr>');
	if(m==true&&status!='approval'&&status!='rejected'){
		$('#managerJudge').append('<p>更新日:<input type="text" id="updated_date" /></p>');
		$('#managerJudge').append('<p>更新者:<input type="text" id="updater_name" /></p>');
		$('#managerJudge').append('<p><button value=approval id=approval class=judge>承認</button></p>');
		$('#managerJudge').append('<p><button value=rejected id=rejected class=judge>拒否</button>  拒否理由:<input type="text" id="reason" /></p>');
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



function judgeExpense(){
	'use strict';
	$('#ExpenseTableBody').empty();
	var key = location.search.substring(3);
	var judge = $(this).attr("id");
	var reason =  $('#reason').val();
	var ApprovalReason = '承認により特になし';
	var updated_date =$('#updated_date').val();
	var updater_name =$('#updater_name').val();
	var requestQuery ;

	if(judge=='rejected'){
	var	x = {
				reason : reason,
				updated_date : updated_date,
				updater_name : updater_name,
				status:judge,
				cd:key
	};
	requestQuery = x;
	}else if(judge=='approval'){
	var	x = {
				reason : ApprovalReason,
				updated_date : updated_date,
				updater_name : updater_name,
				status:judge,
				cd:key
	};
	requestQuery = x;
	}
//	console.log(requestQuery['reason']);
//	console.log(requestQuery['updated_date']);
//	console.log(requestQuery['status']);

	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : '/BasicCheck/ApprovalExpenseServlet',
		data : requestQuery,
		async: false,
		success : function(json) {

	console.log("承認or拒否完了");

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// サーバーとの通信に失敗した時の処理
			alert('経費承認するときにデータの通信に失敗しました');

			console.log("XMLHttpRequest : " + XMLHttpRequest.status);
			console.log("textStatus     : " + textStatus);
			console.log("errorThrown    : " + errorThrown.message);
			console.log(errorThrown)
		}
	});


	$('#managerJudge').empty();
	executeAjax();// 再表示
}



$(document).ready(function() {

	executeAjax();
	$('.judge').click(judgeExpense);

	});