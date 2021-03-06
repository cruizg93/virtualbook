var attachmentItems = null;
var eventItems = [];//items have been add to event
var itemsCont = 0;// itemContTotal is initialize at the bottom of eventRegistration.jsp
var dataHasChange = false;//Only use when try to download a contract PDF
$(document).ready(function(){
	itemsCont = $(".itemRow").length;
	if($("#contactSameAsClient").val()=="1"){
		$("#sameAsContactControl").prop("checked", true);
		sameAsContact(true);
	}else{
		$("#sameAsContactControl").prop("checked", false);
		sameAsContact(false);
	}
	
	$("#sameAsContactControl").change(function() {
	    if(this.checked) {
	    	sameAsContact(true);
	    	$("#contactSameAsClient").val("1");
	    }else{
	    	sameAsContact(false);
	    	$("#contactSameAsClient").val("0");
	    }
	});
	$('#dtpDateAndHour').datetimepicker({
		use24hours: true,
		weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2
	});
	
	$('#dtpDropOff').datetimepicker({
		use24hours: true,
		weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2
	});
	
	$('#dtpPickUp').datetimepicker({
		use24hours: true,
		weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2
	});
	
	//editing is capture at the bottom of eventRegistration.jsp
	if(editing=="True" || editing=="true"){
		formatDatesInForm();
		calculateTotal();
	}else{
		$("#taxPercentage").val("0.0");
		$("#delivery").val("0.0");
		$("#advance").val("0.0");
	}
	
	$("input").change(function(){
		dataHasChange = true;
	});
	$("select").change(function(){
		dataHasChange = true;
	});
});

function addItemRow(){
	var row = $("#itemRow"+(itemsCont-1)).clone();
	$(row).prop("id","itemRow"+itemsCont);
	var itemSelectElement = $(row).find('select');
	var quantityElement = $(row).find('input')[0];
	var pricePerUnitElement = $(row).find('input')[1];
	var actionButton = $(row).find("button");
	
	$(itemSelectElement).prop("id", 	"items"+itemsCont+".item");
	$(itemSelectElement).prop("name", 	"items["+itemsCont+"].item");
	$(quantityElement).prop("id", 		"quantity"+itemsCont);
	$(quantityElement).prop("name", 	"items["+itemsCont+"].quantity");
	$(pricePerUnitElement).prop("id", 	"pricePerUnit"+itemsCont);
	$(pricePerUnitElement).prop("name", "items["+itemsCont+"].pricePerUnit");
	
	$(actionButton).html("-");
	$(actionButton).attr("onclick","deleteItemRow('itemRow"+(itemsCont)+"')");
	$("#itemRow"+(itemsCont-1)).after(row);
	itemsCont += 1;
	dataHasChange = true;
}

function deleteItemRow(rowId){
	$("#"+rowId).remove();
	itemsCont = $(".itemRow").length;
	var items = $(".itemRow");
	var selects = items.find("select");
	var quantity = items.find(".quantity");
	var pricePerUnit = items.find(".pricePerUnit");
	/**
	 * re-index the elements
	 */
	$(selects).each(function(index,val){
		$(val).prop("id","items"+index+".item");
		$(val).prop("name","items["+index+"].item");
	});
	$(quantity).each(function(index,val){
		$(val).prop("id","quantity"+index);
		$(val).prop("name","items["+index+"].quantity");
	});
	$(pricePerUnit).each(function(index,val){
		$(val).prop("id","pricePerUnit"+index);
		$(val).prop("name","items["+index+"].pricePerUnit");
	});
	calculateTotal();
	dataHasChange = true;
}

function requestAttachmentList(){
	 $.ajax({
         url : contextPath+'/existingAttachmentItems',
         dataType: "json",
         success : function(data) {
        	 attachmentItems = data;
        	 for ( var i = 0; i < attachmentItems.length; i++ ) {
    			$('#attachments')
    	        .append($("<option></option>")
    	                   .attr("value",attachmentItems[i].id)
    	                   .text(attachmentItems[i].description));
    		}
         }
     });
} 

function fillAttachmentOptions(id){
	if(attachmentItems!=null || attachmentItems!=undefined){
		for ( var i = 0; i < attachmentItems.length; i++ ) {
			$('#'+id)
	        .append($("<option></option>")
	                   .attr("value",attachmentItems[i].id)
	                   .text(attachmentItems[i].description));
		}
	}
}

function calculateTotal(){
    var delivery = $("#delivery").val();
    var subtotal;
    var itemsPrice = 0;
    var items = $(".itemRow");
    
    for(var i=0;i<items.length;i++){
    	unit_price = $("#pricePerUnit"+i).val();
        quantity = $("#quantity"+i).val();
        if(!isNaN(unit_price) && !isNaN(quantity)){
        	itemsPrice += unit_price * quantity;
        }
    }
    
    if(isNaN(delivery)){
        delivery = 0;
    }
    subtotal = parseFloat(itemsPrice)+parseFloat(delivery);
    $("#subtotal").val(subtotal);
    /* END OF SUBTOTAL CALULATION*/

    var tax_percentage = $("#taxPercentage").val();
    var forward_payment =$("#advance").val();
    var total = parseFloat(subtotal);
    if(!isNaN(tax_percentage)){
        total = parseFloat(subtotal)+((parseFloat(subtotal)*parseInt(tax_percentage))/100);
        $("#tax").val((parseFloat(subtotal)*parseInt(tax_percentage))/100);
    }
    if(!isNaN(forward_payment)){
        total = parseFloat(total) - parseFloat(forward_payment);
    }
    $("#total").val(total);
}


function formatDatesInForm(){
	if($("#contact_date").val()){
		contact = moment($("#contact_date").val()).format("YYYY-MM-DD");
		$("#lblcontact_date").html("CONTACT DATE: "+contact);
		$("#contact_date").val(contact);
	}
	
	dateAndHour = $("#dateAndHourControl").val()?$("#dateAndHourControl").val():$("#dateAndHour").val();
	dropOff = $("#dropOffTimeControl").val()?$("#dropOffTimeControl").val():$("#dropOffTime").val();
	pickUp = $("#pickUpTimeControl").val()?$("#pickUpTimeControl").val():$("#pickUpTime").val();
	
	if(dateAndHour){
		$("#dateAndHourControl").val(moment(dateAndHour).format("YYYY-MM-DD HH:mm"));
		$("#dateAndHour").val(moment(dateAndHour).format("YYYY-MM-DD HH:mm"));
	}
	
	if(dropOff){
		$("#dropOffTimeControl").val(moment(dropOff).format("YYYY-MM-DD HH:mm"));
		$("#dropOffTime").val(moment(dropOff).format("YYYY-MM-DD HH:mm"));
	}
	
	if(pickUp){
		$("#pickUpTimeControl").val(moment(pickUp).format("YYYY-MM-DD HH:mm"));
		$("#pickUpTime").val(moment(pickUp).format("YYYY-MM-DD HH:mm"));
	}
}

function sameAsContact(booleanValue){
	if(booleanValue){
		$("#contactPersonName").prop("disabled", true);
    	$("#contactPersonName").val("");
    	$("#contactPersonEmail").prop("disabled", true);
    	$("#contactPersonEmail").val("");
    	$("#contactPersonPhoneNumber").prop("disabled", true);
    	$("#contactPersonPhoneNumber").val("");
	}else{
		$("#contactPersonName").prop("disabled", false);
    	$("#contactPersonEmail").prop("disabled", false);
    	$("#contactPersonPhoneNumber").prop("disabled", false);
	}
}

function contract(id){
	if(dataHasChange===true){
		alert("Please make sure to save your changes before donwload the new Contract");
	}else{
		$('<form>', {
	         "id": "imprimir",
	         "html": '',
	         "target":'_blank',
	         'method':'GET',
	         "action": context+'/contract-'+id
	     }).appendTo(document.body).submit();
	}
	
}