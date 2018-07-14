function getRecordList(sqlMstId,param){
	var returnResp = "";
	$.ajax({
		url: "getRecordLst?sqlMstId="+sqlMstId+"&param="+param,
		type: 'post',
		dataType: 'json',
		async: false,
		success: function (resp) {
			returnResp = resp;
			
		},
	});
	return returnResp;
}

function getRecordListUserWise(sqlMstId,param){
	var returnResp = "";
	$.ajax({
		url: "user/getRecordLst?sqlMstId="+sqlMstId+"&param="+param,
		type: 'post',
		dataType: 'json',
		async: false,
		success: function (resp) {
			returnResp = resp;
			
		},
	});
	return returnResp;
}

