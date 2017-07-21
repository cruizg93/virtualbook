<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container well lead">
		<h3 class="text-center">Attachment Registration Form</h3>
		<c:set var="action" value="newattachment" />
		<c:if test="${edit}">
			<c:set var="action" value="edit-attachment-${attachment.id}" />
		</c:if>
		<form:form method="POST" modelAttribute="attachment" class="form-horizontal"
			action="${pageContext.request.contextPath}/${action}">
			<form:input type="hidden" path="id" id="id" />

			<div
				class="col-lg-6 col-md-6 col-ms-6 col-lg-offset-3 col-md-offset-3 col-ms-offset-3 col-xs-12">
				<div class="form-group input-group">
					<span class="glyphicon glyphicon-file input-group-addon"
						id="addongDescription"></span>
					<form:input type="text" path="description" id="description"
						class="form-control input-sm appTextNumber"
						placeholder="Description" />
				</div>
				<div class="has-error">
					<form:errors path="description" class="help-inline" />
				</div>
			</div>
			
			<div class="col-lg-12 col-md-12 col-ms-12 col-xs-12">
				<div class="form-actions" style="text-align: center;">
					<c:choose>
						<c:when test="${edit}">
							<button type="submit" value="Update"
								class="btn btn-primary btn-lg formSaveButtonIcon">
								<span class="glyphicon glyphicon-floppy-disk"></span> SAVE
							</button>
						</c:when>
						<c:otherwise>
							<button type="submit" value="Register"
								class="btn btn-primary btn-lg formSaveButtonIcon">
								<span class="glyphicon glyphicon-ok"></span> CREATE
							</button>
						</c:otherwise>
					</c:choose>
					<button type="button"
						class="btn btn-danger btn-lg formDeleteButtonIcon"
						onclick="window.location.href ='<c:url value="/itemList" />'">
						<span class="glyphicon glyphicon-remove"></span> CANCEL
					</button>
				</div>
			</div>
		</form:form>
	</div>