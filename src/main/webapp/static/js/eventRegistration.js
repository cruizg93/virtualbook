var attachmentItems = null;
var eventItems = [];//items have been add to event
var itemsCont = 1;

$(document).ready(function(){
	
	$("#sameAsContact").change(function() {
	    if(this.checked) {
	    	$("#contactPersonName").prop("disabled", true);
	    	$("#contactPersonName").val(" ");
	    	$("#contactPersonEmail").prop("disabled", true);
	    	$("#contactPersonEmail").val(" ");
	    	$("#contactPersonPhoneNumber").prop("disabled", true);
	    	$("#contactPersonPhoneNumber").val(" ");
	    }else{
	    	$("#contactPersonName").prop("disabled", false);
	    	$("#contactPersonEmail").prop("disabled", false);
	    	$("#contactPersonPhoneNumber").prop("disabled", false);
	    }
	});
	$('#dtpDateAndHour').datetimepicker({
		use24hours: true,
		weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0
	});
	
	$('#dtpDropOff').datetimepicker({
		use24hours: true,
		weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0
	});
	
	$('#dtpPickUp').datetimepicker({
		use24hours: true,
		weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0
	});
	
	//editing is capture at the bottom of eventRegistration.jsp
	if(editing=="True" || editing=="true"){ //Production
	//if(editing){//for dev porpouse
		formatDatesInForm();
		calculateTotal();
	}else{
		$("#taxPercentage").val("0.0");
		$("#delivery").val("0.0");
		$("#advance").val("0.0");
	}
});

function deleteRow(itemId){
	// Find and remove item from an array
	var i = eventItems.indexOf(""+itemId);
	if(i != -1) {
		eventItems.splice(i, 1);
	}
	$("#selectExistingItems option[value='"+itemId+"']").css("display","block");
	
	$("#row"+itemId).remove();
	calculateTotal()
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
    var unit_price = $("#pricePerUnit0").val();
    var quantity = $("#quantity0").val();
    var delivery = $("#delivery").val();
    var subtotal;
    var itemPrice = 0;
    if(!isNaN(unit_price) && !isNaN(quantity)){
        itemPrice = unit_price * quantity;
    }
    if(isNaN(delivery)){
        delivery = 0;
    }
    subtotal = parseFloat(itemPrice)+parseFloat(delivery);
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
	if(dateAndHour){
		$("#dateAndHourControl").val(moment(dateAndHour).format("YYYY-MM-DD HH:mm:ss"));
		$("#dateAndHour").val(moment(dateAndHour).format("YYYY-MM-DD HH:mm:ss"));
	}
	dropOff = $("#dropOffTimeControl").val()?$("#dropOffTimeControl").val():$("#dropOffTime").val();
	if(dropOff){
		$("#dropOffTimeControl").val(moment(dropOff).format("YYYY-MM-DD HH:mm:ss"));
		$("#dropOffTime").val(moment(dropOff).format("YYYY-MM-DD HH:mm:ss"));
	}
	pickUp = $("#pickUpTimeControl").val()?$("#pickUpTimeControl").val():$("#pickUpTime").val();
	if(pickUp){
		$("#pickUpTimeControl").val(moment(pickUp).format("YYYY-MM-DD HH:mm:ss"));
		$("#pickUpTime").val(moment(pickUp).format("YYYY-MM-DD HH:mm:ss"));
	}
}