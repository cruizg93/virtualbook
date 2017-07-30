<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container well lead" >
   	<h3 class="text-center">User Registration Form</h3>
   	<c:set var="action" value="newuser" />
	<c:if test="${edit}">
		<c:set var="action" value="edit-user-${user.username}" />
	</c:if>
    <form:form method="POST" modelAttribute="user" action="${pageContext.request.contextPath}/${action}">
        <form:input type="hidden" path="id" id="id"/>
        <div class="col-lg-6 col-md-6 col-ms-6 col-xs-12">
            <div class="form-group input-group">
			  <span class="glyphicon glyphicon-user input-group-addon" id="addongName"></span>
			  <form:input type="text" path="name" id="name" class="form-control input-lg appText" placeholder="Name" />
			</div>
			<div class="has-error">
                    <form:errors path="name" class="help-inline text-danger"/>
                </div>
        </div> 
        
        <div class="col-lg-6 col-md-6 col-ms-6 col-xs-12">
            <div class="form-group input-group">
            	<span class="glyphicon glyphicon-earphone input-group-addon" id="addonPhone"></span>
                   <form:input type="text" path="phoneNumber" id="phoneNumber" class="form-control input-lg appUSPhoneNumber" placeholder="Phone Number"/>
             </div>
             <div class="has-error">
                   <form:errors path="phoneNumber" class="help-inline text-danger"/>
                </div>
         </div>
           <div class="col-lg-6 col-md-6 col-ms-6 col-xs-12">
               <div class="form-group input-group">
                   <span class="input-group-addon" id="addongEmail">@</span>
               	<form:input type="text" path="email" id="email" class="form-control input-lg appEmail" placeholder="E-mail"/>
               </div>
               <div class="has-error">
               	<form:errors path="email" class="help-inline text-danger"/>
               </div>
           </div>
             
        <div class="col-lg-6 col-md-6 col-ms-6 col-xs-12">
            <div class="form-group input-group">
           		<span class="glyphicon glyphicon-map-marker input-group-addon" id="addongAddress"></span>
           		<form:input type="text" path="address" id="address" class="form-control input-lg appAddress" placeholder="#Street (Address), City, state, ZipCode"/>
            </div>
            <div class="has-error">
                   <form:errors path="address" class="help-inline text-danger"/>
               </div>
        </div>
        <div class="col-lg-6 col-md-6 col-ms-6 col-xs-12">
            <div class="form-group input-group">
				<span class="glyphicon glyphicon-user input-group-addon" id="addongUsername"></span>
                    <c:choose>
                        <c:when test="${edit}">
                            <form:input type="text" path="username" id="username" class="form-control input-lg" readonly="true"/>
                        </c:when>
                        <c:otherwise>
                            <form:input type="text" path="username" id="username" class="form-control input-lg" placeholder="Username" />
                        </c:otherwise>
                    </c:choose>
            </div>
            <div class="has-error">
                   <form:errors path="username" class="help-inline text-danger"/>
               </div>
        </div>
        <div class="col-lg-6 col-md-6 col-ms-6 col-xs-12">
            <div class="form-group input-group">
				<span class="glyphicon glyphicon-lock input-group-addon" id="addongPassword"></span>
            	<form:input type="password" path="password" id="password" class="form-control input-lg" placeholder="Password"/>
            </div>
            <div class="has-error">
                   <form:errors path="password" class="help-inline text-danger"/>
               </div>
        </div>
        <div class="col-lg-12 col-md-12 col-ms-12 col-xs-12">
            <div class="form-group input-group">
            	<span class="input-group-addon" id="addonRolesL"><p>R</p><p>O</p><p>L</p><p>E</p><p>S</p></span>
                  	<form:select path="userProfiles" items="${roles}" multiple="true" 
                  				itemValue="id" itemLabel="type" class="form-control input-lg" />
            </div>
            <div class="has-error">
                   <form:errors path="userProfiles" class="help-inline text-danger"/>
               </div>
        </div>
 
        <div class="col-lg-12 col-md-12 col-ms-12 col-xs-12">
            <div class="form-actions text-center">
                <c:choose>
                    <c:when test="${edit}">
                        <button type="submit" value="Update" class="btn btn-primary btn-lg formSaveButtonIcon"><span class="glyphicon glyphicon-floppy-disk"></span> SAVE</button> 
                    </c:when>
                    <c:otherwise>
                        <button type="submit" value="Create" class="btn btn-primary btn-lg formSaveButtonIcon"><span class="glyphicon glyphicon-ok"></span> CREATE</button> 
                    </c:otherwise>
                </c:choose>
                <button type="button" class="btn btn-danger btn-lg formDeleteButtonIcon" onclick="window.location.href ='<c:url value="/list" />'">
                	<span class="glyphicon glyphicon-remove"></span> CANCEL
                </button>
            </div>
        </div>
        
    </form:form>
</div>
