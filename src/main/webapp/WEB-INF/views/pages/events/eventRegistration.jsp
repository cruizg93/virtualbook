<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link
	href="<c:url value='/static/js/datetimepicker/css/bootstrap-datetimepicker.min.css' />"
	rel="stylesheet">
<link href="<c:url value='/static/css/selectize.bootstrap3.css' />"
	rel="stylesheet">
<div class="container well">
	<h3 class="text-center">Event Registration Form</h3>
	<form:form method="POST" modelAttribute="event" id="eventForm">
		<form:input type="hidden" path="id" id="id" />
		<div class="col-md-12 col-ms-12 col-xs-12 text-right">
			<label class="col-md-10 control-lable pull-right" for="contact_date"
				id="lblcontact_date"></label>
			<form:input type="hidden" path="contact_date" id="contact_date" />
			<div class="has-error">
					<form:errors path="contact_date" class="help-inline text-danger" />
				</div>
		</div>
		<div class="row">
			<div class="col-lg-4 col-md-4 col-ms-4 col-xs-12">
				<div class="form-group input-group">
					<span class="glyphicon glyphicon-gift input-group-addon"
						id="addongEventName"></span>
					<form:input type="text" path="eventName" id="eventName"
						class="form-control input-lg" placeholder="Event Name" />
				</div>
				<div class="has-error">
					<form:errors path="eventName" class="help-inline text-danger" />
				</div>
			</div>
			<div class="col-lg-4 col-md-4 col-ms-4 col-xs-12">
				<div class="form-group input-group">
					<span class="glyphicon glyphicon-user input-group-addon"
						id="addongClient"></span>
					<form:select path="client" id="client" items="${clients}"
						multiple="false" itemValue="id" itemLabel="selectDescription"
						class="form-control input-lg" />
				</div>
				<div class="has-error">
					<form:errors path="client" class="help-inline text-danger" />
				</div>
			</div>
			<div class="col-lg-4 col-md-4 col-ms-4 col-xs-12">
				<div class="form-group input-group">
					<span class="glyphicon glyphicon-map-marker input-group-addon"
						id="addongLocation"></span>
					<form:select path="location" id="location" items="${locations}"
						multiple="false" itemValue="id" itemLabel="selectDescription"
						class="form-control input-lg" />
				</div>
				<div class="has-error">
					<form:errors path="location" class="help-inline text-danger" />
				</div>
			</div>

		</div>
		<div class="row">
			<div class="col-lg-4 col-md-4 col-ms-4 col-xs-12">
				<div class="form-group input-group">
					<span class="glyphicon glyphicon-time input-group-addon"
						id="addongContactName"></span>
					<form:input type="text" path="contactPersonName"
						id="contactPersonName" class="form-control input-lg appText"
						placeholder="Contact Person Name" />
				</div>
				<div class="has-error">
					<form:errors path="contactPersonName"
						class="help-inline text-danger" />
				</div>
			</div>
			<div class="col-lg-4 col-md-4 col-ms-4 col-xs-12">
				<div class="form-group input-group">
					<span class="input-group-addon" id="addongContactEmail">@</span>
					<form:input type="text" path="contactPersonEmail"
						placeholder="Contact Person E-mail" id="contactPersonEmail"
						class="form-control input-lg appEmail" />
				</div>
				<div class="has-error">
					<form:errors path="contactPersonEmail"
						class="help-inline text-danger" />
				</div>
			</div>
			<div class="col-lg-4 col-md-4 col-ms-4 col-xs-12">
				<div class="form-group input-group">
					<span class="glyphicon glyphicon-earphone input-group-addon"
						id="addongContactPhone"></span>
					<form:input type="text" path="contactPersonPhoneNumber"
						placeholder="Contact Person Phone" id="contactPersonPhoneNumber"
						class="form-control input-lg appUSPhoneNumber" />
				</div>
				<div class="has-error">
					<form:errors path="contactPersonPhoneNumber"
						class="help-inline text-danger" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-4 col-md-4 col-ms-4 col-xs-12">
				<div
					class="form-group input-group col-lg-12 col-md-12 col-ms-12 col-xs-12">
					<div id="dtpDateAndHour"
						class="input-group date form_datetime col-md-12 "
						data-date-format="yyyy-mm-dd hh:mm:ss"
						data-link-field="dtpDateAndHour">
						<input id="dateAndHourControl" class="form-control input-lg"
							size="22" type="text" value="" placeholder="Event Date/Hour"
							onchange="formatDatesInForm()" onblur="formatDatesInForm()"
							readonly>
						<form:hidden path="dateAndHour" id="dateAndHour" />
						<span class="input-group-addon"><span
							class="glyphicon glyphicon-remove"></span></span> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-th"></span></span>
					</div>
				</div>
				<div class="has-error">
					<form:errors path="dateAndHour" class="help-inline text-danger" />
				</div>
			</div>
			<div class="col-lg-4 col-md-4 col-ms-4 col-xs-12">
				<div
					class="form-group input-group col-lg-12 col-md-12 col-ms-12 col-xs-12">
					<div id="dtpDropOff"
						class="input-group date form_datetime col-md-12 "
						data-date-format="yyyy-mm-dd hh:mm:ss"
						data-link-field="dtpDropOff">
						<input id="dropOffTimeControl" class="form-control input-lg"
							size="22" type="text" value="" placeholder="DropOff Date/Hour"
							onchange="formatDatesInForm()" onblur="formatDatesInForm()"
							readonly>
						<form:hidden path="dropOffTime" id="dropOffTime" />
						<span class="input-group-addon"><span
							class="glyphicon glyphicon-remove"></span></span> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-th"></span></span>
					</div>
				</div>
				<div class="has-error">
					<form:errors path="dropOffTime" class="help-inline text-danger" />
				</div>
			</div>
			<div class="col-lg-4 col-md-4 col-ms-4 col-xs-12">
				<div
					class="form-group input-group col-lg-12 col-md-12 col-ms-12 col-xs-12">
					<div id="dtpPickUp" class="input-group date form_datetime "
						data-date-format="yyyy-mm-dd hh:mm:ss" data-link-field="dtpPickUp">
						<input id="pickUpTimeControl" class="form-control input-lg "
							type="text" value="" placeholder="PickUp Date/Hour"
							onchange="formatDatesInForm()" onblur="formatDatesInForm()"
							readonly>
						<form:hidden path="pickUpTime" id="pickUpTime" />
						<span class="input-group-addon"><span
							class="glyphicon glyphicon-remove"></span></span> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-th"></span></span>
					</div>
				</div>
				<div class="has-error">
					<form:errors path="pickUpTime" class="help-inline text-danger" />
				</div>
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-ms-4 col-xs-12">
			<div class="form-group input-group">
				<span class="glyphicon glyphicon-tower input-group-addon"></span>
				<form:select path="items[0].item" items="${existingMainItems}"
					multiple="false" itemValue="id" itemLabel="description"
					class="form-control input-lg" />
			</div>
			<div class="has-error">
				<form:errors path="items[0].item" class="help-inline text-danger" />
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-ms-4 col-xs-12">
			<div class="form-group input-group">
				<span class="input-group-addon">#</span>
				<form:input type="text" path="items[0].quantity"
					placeholder="Quantity" id="quantity0"
					class="form-control input-lg appNumber" onblur="calculateTotal()" />
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-ms-4 col-xs-12">
			<div class="form-group input-group">
				<span class="glyphicon glyphicon-usd input-group-addon"></span>
				<form:input type="text" path="items[0].pricePerUnit"
					placeholder="Price Per Unit" id="pricePerUnit0"
					class="form-control input-lg appNumberDot"
					onblur="calculateTotal()" />
			</div>
		</div>




		<div class="col-lg-4 col-md-4 col-ms-4 col-xs-12 pull-right">
			<div class="form-group input-group">
				<span class="glyphicon glyphicon-usd input-group-addon">
					SUBTOTAL</span>
				<form:input type="text" path="" id="subtotal"
					class="form-control input-lg appNumber" disabled="true" value="0.0" />
			</div>
			<div class="has-error">
				<form:errors path="delivery" class="help-inline" />
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-ms-4 col-xs-12 pull-right">
			<div class="form-group input-group">
				<span class="glyphicon glyphicon-road input-group-addon">
					DELIVERY</span>
				<form:input type="text" path="delivery" id="delivery"
					class="form-control input-lg appNumber" onblur="calculateTotal()" />
			</div>
			<div class="has-error">
				<form:errors path="delivery" class="help-inline" />
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-ms-4 col-xs-12 pull-left">
			<form:textarea path="comments" class="form-control input-lg" rows="6"
				placeholder="comments" />
		</div>
		<div class="col-lg-4 col-md-4 col-ms-4 col-xs-12 pull-right">
			<div class="form-group input-group">
				<span class="glyphicon glyphicon-usd input-group-addon">
					TAXES</span>
				<form:input type="text" path="" id="tax"
					class="form-control input-lg appNumber col-md-9"
					placeholder="tax value" value="0.0" disabled="true" />
			</div>
			<div class="has-error">
				<form:errors path="delivery" class="help-inline" />
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-ms-4 col-xs-12 pull-right">
			<div class="form-group input-group">
				<span class="input-group-addon">% TAX</span>
				<form:input type="text" path="taxPercentage" id="taxPercentage"
					class="form-control input-lg appNumberDot col-md-3" value=""
					placeholder="%" onblur="calculateTotal()" />
			</div>
			<div class="has-error">
				<form:errors path="delivery" class="help-inline" />
			</div>
		</div>

		<div class="col-lg-4 col-md-4 col-ms-4 col-xs-12 pull-right">
			<div class="form-group input-group">
				<span class="glyphicon glyphicon-usd input-group-addon">
					BALANCE</span>
				<form:input type="text" path="" id="total"
					class="form-control input-lg appNumber" disabled="true" value="0.0" />
			</div>
			<div class="has-error">
				<form:errors path="delivery" class="help-inline" />
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-ms-4 col-xs-12 pull-right">
			<div class="form-group input-group">
				<span class="glyphicon glyphicon-transfer input-group-addon">
					PAID</span>
				<form:input type="text" path="advance" id="advance"
					class="form-control input-lg appNumberDot"
					onblur="calculateTotal()" value="" />
			</div>
			<div class="has-error">
				<form:errors path="delivery" class="help-inline" />
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-ms-12 col-xs-12">
			<div class="form-actions text-center">
				<c:choose>
					<c:when test="${edit}">
						<button type="submit" value="Update"
							class="btn  btn-primary btn-lg formSaveButtonIcon">
							<span class="glyphicon glyphicon-floppy-disk"></span><span class="hidden-ms hidden-xs"> SAVE</span>
						</button>

					</c:when>
					<c:otherwise>
						<button type="submit" value="Save"
							class="btn btn-primary btn-lg formSaveButtonIcon" id="btnSave">
							<span class="glyphicon glyphicon-ok"></span><span class="hidden-ms hidden-xs"> CREATE</span>
						</button>
					</c:otherwise>
				</c:choose>
				<button type="button"
					class="btn btn-danger btn-lg formDeleteButtonIcon"
					onclick="window.location.href ='<c:url value="/eventlist" />'">
					<span class="glyphicon glyphicon-remove"></span><span class="hidden-ms hidden-xs"> CANCEL</span>
				</button>
				 <c:if test = "${edit}">
					<button type="button"
						class="btn btn-danger btn-lg formDeleteButtonIcon"
						onclick="window.location.href ='<c:url value="/delete-event-${id}" />'">
						<span class="glyphicon glyphicon-trash"></span><span class="hidden-ms hidden-xs"> DELETE</span>	
					</button> 	
				 </c:if>
				
			</div>
		</div>
	</form:form>
</div>
<script type="text/javascript"
	src="<c:url value='/static/js/eventRegistration.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/static/js/datetimepicker/js/bootstrap-datetimepicker.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/static/js/moment.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/static/js/standalone/selectize.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/static/js/selectize.js' />"></script>
<style>
.row {
	margin: 0px !important
}
</style>
<script>
	var editing = "${edit}";
	if(editing!=='true'){
		$("#location").prepend("<option value='' selected='selected'>LOCATION</option>");
		$("#client").prepend("<option value='' selected='selected'>CLIENT</option>");
	}
	$("#location").selectize();
	$("#client").selectize();
</script>