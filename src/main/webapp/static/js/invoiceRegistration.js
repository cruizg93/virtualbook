$(document).ready(function(){
	
	$("#clientSelect").change(function(){
		getEvents($(this).val());
	});
	
	$('#dtpDueDate').datetimepicker({
		 minView: 2, pickTime: false,autoclose: 1,
	});
	var clientSelect = $("#clientSelect").selectize();
	
	if(edit==true){
		$("#dueDateControl").val($("#dueDate").val());
		getEvents();
	}
});

function submitForm(){
	var valid = true;
	var message = "";
	if($("#events").val().length<=0){
		valid = false;
		message = "You must add an event to invoices";
	}
	if($("#dueDate").val()==""){
		valid = false;
		message = "Please select due date";
	}
	if($("#clientSelect").val()==""){
		valid = false;
		message = "Please select a client";
	}
	if(valid){
		$("form").submit();
	}else{
		$("#errorMessage").css("display","block");
		$("#errorMessage").html(message);
	}
}

function getEvents(){
	var data = {}
	data = $("#clientSelect").val();

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : contextPath+'/nonPaidEventsByClient',
		data : data,
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			$("#allEvents").html(" ");
			for(var i=0;i<data.length;i++){
				var date = data[i].formatedDateAndHour;
				$("#allEvents").append($("<option></option>")
	                    .attr("value",data[i].id)
	                    .text(date+"-"+data[i].location.buildingName));
			}
			filterEvents();
		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE");
		},
		beforeSend: function(xhr) {
	        xhr.setRequestHeader("Accept", "application/json");
	        xhr.setRequestHeader("Content-Type", "application/json");
	        xhr.setRequestHeader(header, token);
	    }
	});
}

function rightArrow(){
	var value = $("#allEvents").val();
	var text = $("#allEvents option[value='"+value+"']").text()
	addOption("events",value,text,true);
	$("#allEvents option[value='"+value+"']").remove();
	
}

function leftArrow(){
	var values = $("#events").val();
	if(Array.isArray(values)){
		$(values).each(function(index,value){
			var text = $("#events option[value='"+value+"']").text();
			addOption("allEvents",value,text,false);
			$("#events option[value='"+value+"']").remove();
		});
	}else{
		var value = values;
		var text = $("#events option[value='"+value+"']").text();
		addOption("allEvents",value,text,false);
		$("#events option[value='"+value+"']").remove();
	}
}

/**
 * SelectId elementId in HTML
 * value id of object
 * text description of object
 * Selected boolean value
 */
function addOption(selectId,value,text,selected){
	$("#"+selectId).append($("<option></option>")
            .attr("value",value)
            .text(text));
	if(selected){
		$('#'+selectId+' option').attr('selected', 'selected');
	}
}

function formatDatesInForm(){
	dateAndHour = $("#dueDateControl").val()?$("#dueDateControl").val():$("#dueDate").val();
	$("#dueDateControl").val(moment(dateAndHour).format("YYYY-MM-DD"));
	$("#dueDate").val(moment(dateAndHour).format("YYYY-MM-DD"));
}

function collect(){
	var invoiceId = $("#id").val();
	
	bootbox.confirm({
	    title: "Collect Invoice?",
	    message: "If you have change this invoice make sure you saved the changes before collect.<br/>Are you sure you want to collect this invoice. <br/> Can not be reverse <br/> <label>COMMENT</label> <br/> <textarea cols='12' class='form-control' id='comment' name='comment'></textarea>",
	    buttons: {
	        cancel: {
	            label: '<i class="fa fa-times"></i> Cancel'
	        },
	        confirm: {
	            label: '<i class="fa fa-check"></i> Confirm'
	        }
	    },
	    callback: function (result) {
	        if(result){
	        	var comment = $("#comment").val();
	        	var id = $("#id").val();
	        	var data = {}
	        	data = id+"<"+comment+"";
	        	$.ajax({
	        		type : "POST",
	        		contentType : "application/json",
	        		url : contextPath+'/collectInvoice',
	        		data : data,
	        		timeout : 100000,
	        		dataType : 'json',
	        		success : function(data) {
	        			alert(data);
	        		},
	        		error : function(e) {
	        			alert("error");
	        			console.log("ERROR: ", e);
	        		},
	        		done : function(e) {
	        			console.log("DONE");
	        		},
	        		beforeSend: function(xhr) {
	        	        xhr.setRequestHeader("Accept", "application/json");
	        	        xhr.setRequestHeader("Content-Type", "application/json");
	        	        xhr.setRequestHeader(header, token);
	        	    }
	        	});
	        }
	    }
	})
}

function filterEvents(){
	$("#events option").each(function(){
		var eventValue = $(this).val();
		$("#allEvents option[value='"+eventValue+"']").remove();
	});
}