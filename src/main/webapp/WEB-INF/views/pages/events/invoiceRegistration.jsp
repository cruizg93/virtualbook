<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<c:url value='/static/js/datetimepicker/css/bootstrap-datetimepicker.min.css' />"
	rel="stylesheet">
<link href="<c:url value='/static/css/selectize.bootstrap3.css' />"
	rel="stylesheet">
<div class="container well">
	<h3>Invioce Create Interface</h3>
	<c:set var="action" value="newInvoice"/>
	<c:if test="${edit}">
		<c:set var="action" value="edit-invoice-${invoice.id}"/>
	</c:if>
	<form:form method="POST" modelAttribute="invoice" id="invoiceForm"
		action="${pageContext.request.contextPath}/${action}">
		
		<form:input type="hidden" path="id" id="id" />
		<form:input type="hidden" path="invoiceNumber" id="invoiceNumber" />
		<div class="row">
			<div class="col-lg-6 col-md-6 col-ms-6 col-xs-12">
				<c:choose>
					<c:when test="${edit eq true}">
						<div class="col-lg-8 col-md-8 col-ms-8 col-xs-8">
					</c:when>
					<c:otherwise>
						<div class="col-lg-12 col-md-12 col-ms-12 col-xs-12">
					</c:otherwise>
				</c:choose>
				<label for="">CLIENT</label>
					<div class="form-group input-group">
						<span class="glyphicon glyphicon-user input-group-addon"
							id="addongClient"></span>
						<select name="clientSelect" id="clientSelect" class="form-control">
							<option value="">-- Select Client --</option>
							<c:forEach items="${clients}" var="client">
								<c:choose>
									<c:when test="${edit eq true}">
								  		<c:choose>
								  			<c:when test="${client.id eq invoice.events.iterator().next().client.id}">
								  				<option value="${client.id}" selected="selected">${client.name}</option>		
								  			</c:when>
								  			<c:otherwise>
								  				<option value="${client.id}">${client.name}</option>
								  			</c:otherwise>
								  		</c:choose>  		
								  	</c:when>
									<c:otherwise>
										<option value="${client.id}">${client.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>
				<c:if test="${edit eq true }">
					<div class="col-lg-4 col-md-4 col-ms-4 col-xs-4">
						<label>INVOICE NUMBER</label>
						<span>${invoice.invoiceNumber}</span>
					</div>
				</c:if>
			</div>
			<div class="col-lg-6 col-md-6 col-ms-6 col-xs-12">
				<label for="dtpDueDate">DUE DATE</label>
				<div class="form-group input-group col-lg-12 col-md-12 col-ms-12 col-xs-12">
					<div id="dtpDueDate" class="input-group date form_datetime col-md-12 "
						data-date-format="yyyy-mm-dd"
						data-link-field="dtpDueDate">
						<input id="dueDateControl" class="form-control input-lg"
							size="22" type="text" value="" placeholder="Due Date"
							onchange="formatDatesInForm()" onblur="formatDatesInForm()"
							readonly>
						<form:hidden path="dueDate" id="dueDate" />
						<span class="input-group-addon"><span
							class="glyphicon glyphicon-remove"></span></span> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-th"></span></span>
					</div>
				</div>
				<div class="has-error">
					<form:errors path="dueDate" class="help-inline text-danger" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6 col-md-6 col-ms-6 col-xs-12">
	            <div class="form-group input-group">
	            	<span class="input-group-addon" id="addonRolesL"><p></p><p>E</p><p>V</p><p>E</p><p>N</p><p>T</p><p>S</p></span>
	                  	<select name="allEvents" id="allEvents" 
	                  		class="form-control" size="8">
	                  		<option value="">-- Select Client --</option>
	                  		<option value="">-- Select Client --</option>
	                  		<option value="">-- Select Client --</option>
	                  		<option value="">-- Select Client --</option>
	                  		<option value="">-- Select Client --</option>
	                  		<option value="">-- Select Client --</option>
	                  		<option value="">-- Select Client --</option>
	                  	</select>
	            </div>
	        </div>
	        <div class="col-lg-6 col-md-6 col-ms-6 col-xs-12">
	            <div class="form-group input-group">
	            	<span class="input-group-addon" id="addonRolesL"><p>I</p><p>N</p><p>V</p><p>O</p><p>I</p><p>C</p><p>E</p></span>
	                  	<form:select path="events" size="6" items="${invoice.events}" multiple="true"
                  				itemValue="id" itemLabel="invoiceLable" class="form-control input-lg" />
	            </div>
	        </div>
        </div>
        <div class="row">
        	<div class="col-md-12 ">
        		<div id="errorMessage" class="alert alert-danger" style="display:none;">
        		
				</div>
        	</div>
        </div>
        <div class="row">
        	<c:choose>
        		<c:when test="${edit eq true }">
					<c:choose>
						<c:when test="${invoice.isCollected() eq true }">
							<button type="button" class="btn btn-primary btn-lg" onclick="submitForm();" disabled>
								<span class="glyphicon glyphicon-plus"></span> SAVE
							</button>
							<button type="button" class="btn btn-success btn-lg" onclick="collect();" disabled>
								<span class="glyphicon glyphicon-piggy-bank"></span> COLLECT
							</button>
						</c:when>
						<c:otherwise>
							<button type="button" class="btn btn-primary btn-lg" onclick="submitForm();">
								<span class="glyphicon glyphicon-plus"></span> SAVE
							</button>
							<button type="button" class="btn btn-success btn-lg" onclick="collect();">
								<span class="glyphicon glyphicon-piggy-bank"></span> COLLECT
							</button>
						</c:otherwise>
					</c:choose>	        		
        		</c:when>
        		<c:otherwise>
        			<button type="button" class="btn btn-primary btn-lg" onclick="submitForm();">
						<span class="glyphicon glyphicon-plus"></span> CREATE
					</button>			
        		</c:otherwise>
        	</c:choose>
			<button type="button"
					class="btn btn-danger btn-lg formDeleteButtonIcon"
					onclick="window.location.href ='<c:url value="/invoicelist" />'">
					<span class="glyphicon glyphicon-remove"></span><span class="hidden-ms hidden-xs"> CANCEL</span>
				</button>
			<span class="glyphicon glyphicon-chevron-left" onclick="leftArrow();" style="font-size:4rem; vertical-align: middle;"></span>
			<span class="glyphicon glyphicon-chevron-right" onclick="rightArrow();" style="font-size:4rem; vertical-align: middle;"></span>
		</div>
	</form:form>
</div>
<script type="text/javascript"
	src="<c:url value='/static/js/standalone/selectize.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/static/js/selectize.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/static/js/invoiceRegistration.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/static/js/datetimepicker/js/bootstrap-datetimepicker.js' />"></script>	
<script type="text/javascript"
	src="<c:url value='/static/js/moment.js' />"></script>
<script type="text/javascript">
	var context = '${pageContext.request.contextPath}';
	var token = "${_csrf.token}";
	var header = "${_csrf.headerName}";
	var edit = ${edit};
</script>