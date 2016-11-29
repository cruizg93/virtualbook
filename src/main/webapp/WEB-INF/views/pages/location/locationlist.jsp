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
            <div class="panel-heading"><span class="lead">List of Locations</span></div>
            <table class="table table-hover" id="locationTable">
                <thead>
                    <tr>
                        <th>Location</th>
                        <th>Building</th>
                        <th>Phone</th>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                        	<th></th>
                        </sec:authorize>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${locations}" var="location">
                    <tr>
                        <td>${location.location}</td>
                        <td>${location.buildingName}</td>
                        <td>${location.phoneNumber}</td>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                        <td>
                        	<button type="button" class="btn btn-success formEditButtonIcon"
	                            	onclick="window.location.href ='<c:url value="/edit-location-${location.location}" />'">Edit</button>
                            <button type="button" class="btn btn-danger formDeleteButtonIcon" 
                        		onclick="window.location.href ='<c:url value="/delete-location-${location.location}" />'">Delete</button>
                        </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <sec:authorize access="hasRole('ADMIN')">
            <div class="well">
				<button type="button" class="btn btn-primary formAddButtonIcon" onclick="window.location.href ='<c:url value="/newlocation" />'">Add New Location</button>
            </div>
        </sec:authorize>
    </div>
    <script type="text/javascript">
    	$("#locationTable").DataTable();
    </script>