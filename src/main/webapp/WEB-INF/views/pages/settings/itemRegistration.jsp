<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="generic-container" style="text-align:right;">

    <div class="well lead" style="text-align: center !important;">Item Registration Form</div>
    <form:form method="POST" modelAttribute="item" class="form-horizontal" >
        <form:input type="hidden" path="id" id="id"/>
         
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="description">Description</label>
                <div class="col-md-7">
                    <form:input type="text" path="description" id="description" class="form-control input-sm appText"/>
                    <div class="has-error">
                        <form:errors path="description" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="quantity">Quantity</label>
                <div class="col-md-7">
                    <form:input type="text" path="quantity" id="quantity" class="form-control input-sm appNumber"/>
                    <div class="has-error">
                        <form:errors path="quantity" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="itemTypes">Types</label>
                <div class="col-md-7">
                    <form:select path="itemTypes" items="${types}" multiple="false" itemValue="id" itemLabel="description" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="itemTypes" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
 
        <div class="row">
            <div class="form-actions" style="text-align:center;">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update" class="btn btn-primary formSaveButtonIcon"/> <button type="button" class="btn btn-danger formDeleteButtonIcon" onclick="window.location.href ='<c:url value="/itemList" />'">Cancel</button>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Register" class="btn btn-primary formSaveButtonIcon"/> <button type="button" class="btn btn-danger formDeleteButtonIcon" onclick="window.location.href ='<c:url value="/itemList" />'">Cancel</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</div>
