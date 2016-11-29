<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="generic-container" style="text-align:right;">

    <div class="well lead" style="text-align: center !important;">User Registration Form</div>
    <form:form method="POST" modelAttribute="user" class="form-horizontal" >
        <form:input type="hidden" path="id" id="id"/>
         
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="name">Name</label>
                <div class="col-md-7">
                    <form:input type="text" path="name" id="name" class="form-control input-sm"/>
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
                    <form:input type="text" path="phoneNumber" id="phoneNumber" class="form-control input-sm" />
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
                     <form:input type="text" path="email" id="email" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="email" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
             
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="address">Address</label>
                <div class="col-md-7">
                    <form:input type="text" path="address" id="address" class="form-control input-sm" placeholder="#Street, City, state, ZipCode"/>
                    <div class="has-error">
                        <form:errors path="address" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
 
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="username">Username</label>
                <div class="col-md-7">
                    <c:choose>
                        <c:when test="${edit}">
                            <form:input type="text" path="username" id="username" class="form-control input-sm" readonly="true"/>
                        </c:when>
                        <c:otherwise>
                            <form:input type="text" path="username" id="username" class="form-control input-sm" />
                            <div class="has-error">
                                <form:errors path="username" class="help-inline"/>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="password">Password</label>
                <div class="col-md-7">
                    <form:input type="password" path="password" id="password" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="password" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="userProfiles">Roles</label>
                <div class="col-md-7">
                    <form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="userProfiles" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
 
        <div class="row">
            <div class="form-actions" style="text-align:center;">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update" class="btn btn-primary formSaveButtonIcon"/> <button type="button" class="btn btn-danger formDeleteButtonIcon" onclick="window.location.href ='<c:url value="/list" />'">Cancel</button>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Register" class="btn btn-primary formSaveButtonIcon"/> <button type="button" class="btn btn-danger formDeleteButtonIcon" onclick="window.location.href ='<c:url value="/list" />'">Cancel</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</div>
