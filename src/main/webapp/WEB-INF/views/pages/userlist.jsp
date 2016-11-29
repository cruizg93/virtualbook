<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
 
 	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    <link href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet"></link>
    <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js" type="text/javascript"></script>
    <div class="generic-container">
        <div class="panel panel-default">
              <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of Users </span></div>
            <table class="table table-hover" id="usersTable">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th>Username</th>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                            <th width="100"></th>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN')">
                            <th width="100"></th>
                        </sec:authorize>
                         
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.name}</td>
                        <td>${user.address}</td>
                        <td>${user.phoneNumber}</td>
                        <td>${user.email}</td>
                        <td>${user.username}</td>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                            <td><button type="button" class="btn btn-success formEditButtonIcon" onclick="window.location.href ='<c:url value="/edit-user-${user.username}" />'">Edit</button></td>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN')">
                        	<td><button type="button" class="btn btn-danger formDeleteButtonIcon" 
                        	onclick="window.location.href ='<c:url value="/delete-user-${user.username}" />'">Delete</button></td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <sec:authorize access="hasRole('ADMIN')">
            <div class="well">
				<button type="button" class="btn btn-primary formAddButtonIcon" onclick="window.location.href ='<c:url value="/newuser" />'">Add New User</button>
            </div>
        </sec:authorize>
    </div>
    <script type="text/javascript">
    	$("#usersTable").DataTable();
    </script>