<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container well text-center">
	<h1>Error</h1>
	<c:if test="${not empty generic}">
		<h4>${generic}</h4>
	</c:if>
	<h5>${message}</h5>
</div>