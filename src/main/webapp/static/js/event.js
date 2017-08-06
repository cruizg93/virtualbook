$(document).ready(function(){
	if(month!=null){
		getMonthLabel();
		var currentMonth= new Date();
		currentMonth.setMonth(month);
	}else{
		$("#hYeartitle").html(year);
	}
});


function getMonthLabel(){
	var label = "";
	switch (month) {
	case 1:
		label="JANUARY";
		break;
	case 2:
		label="FEBRUARY";
		break;
	case 3:
		label="MARCH";
		break;
	case 4:
		label="APRIL";
		break;
	case 5:
		label="MAY";
		break;
	case 6:
		label="JUNE";
		break;
	case 7:
		label="JULY";
		break;
	case 8:
		label="AUGUST";
		break;
	case 9:
		label="SEPTEMBER";
		break;
	case 10:
		label="OCTOBER";
		break;
	case 11:
		label="NOVEMBER";
		break;
	case 12:
		label="DECEMBER";
		break;
	default:
		label="ERROR";
		break;
	}
	$("#hMonthtitle").html(label+" "+year);
}


function contract(id){
	$('<form>', {
         "id": "imprimir",
         "html": '',
         "target":'_blank',
         'method':'GET',
         "action": context+'/contract-'+id
     }).appendTo(document.body).submit();
}