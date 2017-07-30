<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container well lead" >
   	<h3 class="text-center">Location Registration Form</h3>
    <c:set var="action" value="newlocation" />
	<c:if test="${edit}">
		<c:set var="action" value="edit-location-${location.location}" />
	</c:if>
    <form:form method="POST" modelAttribute="location" class="form-horizontal" 
    	action="${pageContext.request.contextPath}/${action}">
        <form:input type="hidden" path="id" id="id"/>
         
        <div class="col-lg-6 col-md-6 col-ms-6 col-lg-offset-3 col-md-offset-3 col-ms-offset-3 col-xs-12">
            <div class="form-group input-group">
				<span class="glyphicon glyphicon-map-marker input-group-addon" id="addongLocation"></span>
                   <form:input type="text" path="location" id="location" class="form-control input-sm" placeholder="Address, city, state, zipcode"/>
            </div>
            <div class="has-error text-danger">
                   <form:errors path="location" class="help-inline"/>
               </div>
        </div>
 
        <div class="col-lg-6 col-md-6 col-ms-6 col-lg-offset-3 col-md-offset-3 col-ms-offset-3 col-xs-12">
            <div class="form-group input-group">
            	<span class="glyphicon glyphicon-home input-group-addon" id="addongBuildingName"></span>
               	<form:input type="text" path="buildingName" id="buildingName" class="form-control input-sm" placeholder="Building Name" />
            </div>
             <div class="has-error text-danger">
                   <form:errors path="buildingName" class="help-inline"/>
                </div>
         </div>
		<div class="col-lg-6 col-md-6 col-ms-6 col-lg-offset-3 col-md-offset-3 col-ms-offset-3 col-xs-12">
             <div class="form-group input-group">
				<span class="glyphicon glyphicon-earphone input-group-addon" id="addongPhone"></span>
				<form:input type="text" path="phoneNumber" id="phoneNumber" class="form-control input-sm appUSPhoneNumber" placeholder="Phone Number"/>
            </div>
               <div class="has-error text-danger">
                   <form:errors path="phoneNumber" class="help-inline"/>
               </div>
        </div>
             
        <div class="col-lg-12 col-md-12 col-ms-12 col-xs-12">
            <div class="form-actions text-center">
                <c:choose>
                    <c:when test="${edit}">
                        <button type="submit" value="Update" class="btn btn-primary formSaveButtonIcon"><span class="glyphicon glyphicon-floppy-disk"></span> SAVE</button> 
                    </c:when>
                    <c:otherwise>
                        <button type="submit" value="Register" class="btn btn-primary formSaveButtonIcon"><span class="glyphicon glyphicon-ok"></span> CREATE</button>
                    </c:otherwise>
                </c:choose>
                <button type="button" class="btn btn-danger formDeleteButtonIcon" onclick="window.location.href ='<c:url value="locationlist" />'">
                	<span class="glyphicon glyphicon-remove"></span> Cancel
               	</button>
            </div>
        </div>
    </form:form>
</div>
