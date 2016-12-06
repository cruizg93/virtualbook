<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><tiles:getAsString name="title" /></title>
    <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/tilesStyle.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/simple-sidebar.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/font-awesome-4.7.0/css/font-awesome.min.css' />" rel="stylesheet">
	
	<script type="text/javascript" src="<c:url value='/static/js/jquery-3.1.1.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/static/js/bootstrap.js' />"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.3/jquery.mask.min.js"></script>
	<script type="text/javascript" src="<c:url value='/static/js/appInputMask.js' />"></script>
	<sec:authorize access="isAnonymous()">
		<style>
			#site-content, #footer{
				width:100%;
				margin-left:0px;
			}
		</style>
	</sec:authorize>
           
</head>
<body>
        <header id="header">
            <tiles:insertAttribute name="header" />
        </header>
		<sec:authorize access="isAuthenticated()">
			<section id="sidemenu">
	            <tiles:insertAttribute name="menu" />
	        </section>
		</sec:authorize>
        <section id="site-content">
            <tiles:insertAttribute name="body" />
        </section>
         
        <footer id="footer">
            <tiles:insertAttribute name="footer" />
        </footer>
</body>
</html>