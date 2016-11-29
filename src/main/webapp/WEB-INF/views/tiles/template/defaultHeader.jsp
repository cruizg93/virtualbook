<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
	    <span class="floatRight" style="font-size: 3.6em;"><a href="<c:url value="/logout" />">Logout</a></span>
</sec:authorize>
<H1>VIRTUALBOOK</H1>
