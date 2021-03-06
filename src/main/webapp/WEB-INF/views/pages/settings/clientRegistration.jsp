<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container well lead">
	<h3 class="text-center">Client Registration Form</h3>
	<c:set var="action" value="newclient" />
	<c:if test="${edit}">
		<c:set var="action" value="edit-client-${client.id}" />
	</c:if>
	<form:form method="POST" modelAttribute="client"
		class="form-horizontal"
		action="${pageContext.request.contextPath}/${action}">
		<form:input type="hidden" path="id" id="id" />
		<div class="col-lg-6 col-md-6 col-ms-6 col-lg-offset-3 col-md-offset-3 col-ms-offset-3 col-xs-12">
			<div class="form-group input-group">
				<span class="glyphicon glyphicon-user input-group-addon" id="addongName"></span>
				<form:input type="text" path="name" id="name"
					class="form-control input-sm appText" placeholder="Name"/>
			</div>
			<div class="has-error">
				<form:errors path="name" class="help-inline" />
			</div>
		</div>
		<div class="col-lg-6 col-md-6 col-ms-6 col-lg-offset-3 col-md-offset-3 col-ms-offset-3 col-xs-12">
			<div class="form-group input-group">
				<span class="glyphicon glyphicon-earphone input-group-addon" id="addongPhone"></span>
				<form:input type="text" path="phoneNumber" id="phoneNumber"
						class="form-control input-sm appUSPhoneNumber" placeholder="Phone Number"/>
			</div>
			<div class="has-error">
				<form:errors path="phoneNumber" class="help-inline" />
			</div>
		</div>
		<div class="col-lg-6 col-md-6 col-ms-6 col-lg-offset-3 col-md-offset-3 col-ms-offset-3 col-xs-12">
			<div class="form-group input-group">
				<span class="input-group-addon" id="addongEmail">@</span>
				<form:input type="text" path="email" id="email"
					class="form-control input-sm appEmail" placeholder="E-mail" />
			</div>
			<div class="has-error">
					<form:errors path="email" class="help-inline" />
				</div>
		</div>

		<div class="col-lg-6 col-md-6 col-ms-6 col-lg-offset-3 col-md-offset-3 col-ms-offset-3 col-xs-12">
			<div class="form-group input-group">
				<span class="glyphicon glyphicon-briefcase input-group-addon" id="addongCompany"></span>
				<form:input type="text" path="companyName" id="companyName"
					class="form-control input-sm appTextNumber" placeholder="Company Name"/>
			</div>
			<div class="has-error">
				<form:errors path="companyName" class="help-inline" />
			</div>
		</div>
		<div class="col-lg-6 col-md-6 col-ms-6 col-lg-offset-3 col-md-offset-3 col-ms-offset-3 col-xs-12">
			<div class="form-group input-group">
				<span class="glyphicon glyphicon-info-sign input-group-addon" id="addongInvoice"></span>
				<form:input type="text" path="invoiceNumber" id="invoiceNumber"
					class="form-control input-sm appTextNumber" placeholder="Invoice Number"/>
			</div>
			<div class="has-error">
				<form:errors path="invoiceNumber" class="help-inline" />
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-ms-12 col-xs-12">
			<div class="form-actions text-center">
				<c:choose>
					<c:when test="${edit}">
						<button type="submit" value="Update" class="btn btn-primary btn-lg formSaveButtonIcon" ><span class="glyphicon glyphicon-floppy-disk"></span> SAVE</button>
					</c:when>
					<c:otherwise>
						<button type="submit" value="Register" class="btn btn-primary btn-lg formSaveButtonIcon" ><span class="glyphicon glyphicon-ok"></span> CREATE</button>
					</c:otherwise>
				</c:choose>
				<button type="button" class="btn btn-danger btn-lg formDeleteButtonIcon"
							onclick="window.location.href ='<c:url value="/clientlist" />'">
					<span class="glyphicon glyphicon-remove"></span> CANCEL
				</button>
			</div>
		</div>
	</form:form>
</div>
