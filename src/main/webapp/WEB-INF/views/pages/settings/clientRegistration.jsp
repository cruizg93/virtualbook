<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="generic-container" style="text-align:right;">

    <div class="well lead" style="text-align: center !important;">Client Registration Form</div>
    <form:form method="POST" modelAttribute="client" class="form-horizontal" >
        <form:input type="hidden" path="id" id="id"/>
         
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="name">Name</label>
                <div class="col-md-7">
                    <form:input type="text" path="name" id="name" class="form-control input-sm appText"/>
                    <div class="has-error">
                        <form:errors path="name" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
 
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="phoneNumber">Phone</label>
                <div class="col-md-7">
                    <form:input type="text" path="phoneNumber" id="phoneNumber" class="form-control input-sm appUSPhoneNumber" />
                    <div class="has-error">
                        <form:errors path="phoneNumber" class="help-inline"/>
                     </div>
                 </div>
             </div>
         </div>
		<div class="row">
             <div class="form-group col-md-12">
                 <label class="col-md-3 control-lable" for="email">Email</label>
                 <div class="col-md-7">
                     <form:input type="text" path="email" id="email" class="form-control input-sm appEmail" />
                    <div class="has-error">
                        <form:errors path="email" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
             
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="companyName">Company Name</label>
                <div class="col-md-7">
                    <form:input type="text" path="companyName" id="companyName" class="form-control input-sm appTextNumber"/>
                    <div class="has-error">
                        <form:errors path="companyName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-actions" style="text-align:center;">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update" class="btn btn-primary formSaveButtonIcon"/> <button type="button" class="btn btn-danger formDeleteButtonIcon" onclick="window.location.href ='<c:url value="/clientlist" />'">Cancel</button>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Register" class="btn btn-primary formSaveButtonIcon"/> <button type="button" class="btn btn-danger formDeleteButtonIcon" onclick="window.location.href ='<c:url value="/clientlist" />'">Cancel</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</div>
