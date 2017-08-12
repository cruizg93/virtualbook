<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
    
    <title><tiles:getAsString name="title" /></title>
    <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/font-awesome-4.7.0/css/font-awesome.min.css' />" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Kurale" rel="stylesheet">
	<script type="text/javascript" src="<c:url value='/static/js/jquery-3.2.1.js' />"></script>
	<sec:authorize access="isAnonymous()">
		<!-- NO LOGGED IN -->
	</sec:authorize>
	<script type="text/javascript">
		var contextPath='<%=request.getContextPath()%>';
		var context = '${pageContext.request.contextPath}';
	</script>
</head>
<body>
	<header id="header" class="navbar navbar-default">
	    <tiles:insertAttribute name="header" />
	</header>
	<section id="site-content">
		<tiles:insertAttribute name="menu" />
	    <tiles:insertAttribute name="body" />
	</section>
	<footer id="footer">
	    <tiles:insertAttribute name="footer" />
	</footer>
	<script type="text/javascript" src="<c:url value='/static/js/bootstrap.js' />"></script>
	<script type="text/javascript" src="<c:url value='/static/js/app.js' />"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.3/jquery.mask.min.js"></script>
	<script type="text/javascript" src="<c:url value='/static/js/appInputMask.js' />"></script>
	<script type="text/javascript" src="<c:url value='/static/js/bootbox.min.js' />"></script>
</body>
</html>