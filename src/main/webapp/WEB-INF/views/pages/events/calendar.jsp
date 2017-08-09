<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<link href="<c:url value='/static/css/calendar.css' />" rel="stylesheet"></link>
<script>
var month = null;
var year = '${year}';
</script>
<div id="divCurrentMonth" class="container well text-center" style="padding-top:0px;">
	<div class="panel panel-default" style="margin-bottom: 0px;">
		<div class="row" id="divMonthTitle">
		 	<div class="col-md-12 col-sm-12">
		 		<h2>
		 			<span class="arrow clickable glyphicon glyphicon-menu-left" 
						onclick="window.location.href ='<c:url value="/previousYear?y=${year}"></c:url>'"></span>
					<span id="hYeartitle"></span>
					<span class="arrow clickable glyphicon glyphicon-menu-right"
					onclick="window.location.href ='<c:url value="/nextYear?y=${year}"></c:url>'"></span>
		 		</h2>
		 		<h4 id="hMonthtitle">Events:<span id="spanEventTitleCount">${count}</span></h4>
			</div>
			<ul class="nav nav-pills views">
				<li class="active" ><a href="#tab_calendar" data-toggle="pill">CALENDAR</a></li>
				<li ><a href="#tab_list" data-toggle="pill">LIST</a></li>
			</ul>
		</div>
	</div>
	<div class="divTopButton">
		<button type="button" class="btn btn-success btn-lg " 
			onclick="window.location.href ='<c:url value="/newEvent" />'">
		<span class="glyphicon glyphicon-plus"></span>New Event</button>
	</div>
	<div class="tab-content">
		<div class="tab-pane active" id="tab_calendar">
			<div class="row">
				<div class="col-md-12 col-sm-12" id="divCalendar" style="padding:0px !important;">
					<c:set var="row" value="even" />
					<c:set var="paid" value="notPaid" />
					<div class="row">
						<div class="calendarMonth noMinHeight" onclick="window.location.href ='<c:url value="/month?m=${1}&y=${year}"></c:url>'"><p class="monthLabel">JANUARY - ${fn:length(events[0])}</p></div>
						<div class="calendarMonth noMinHeight" onclick="window.location.href ='<c:url value="/month?m=${2}&y=${year}"></c:url>'"><p class="monthLabel">FEBRUARY - ${fn:length(events[1])}</p></div>
						<div class="calendarMonth noMinHeight" onclick="window.location.href ='<c:url value="/month?m=${3}&y=${year}"></c:url>'"><p class="monthLabel">MARCH - ${fn:length(events[2])}</p></div>
						<div class="calendarMonth noMinHeight" onclick="window.location.href ='<c:url value="/month?m=${4}&y=${year}"></c:url>'"><p class="monthLabel">APRIL - ${fn:length(events[3])}</p></div>
					</div>
					<div class="row">
						<c:set var="row" value="even" />
						<c:set var="paid" value="notPaid" />
						<c:forEach begin="0" end="3" varStatus="loop">  
							<c:choose>
								<c:when test="${fn:length(events[loop.index])>0}">
									<div class="calendarMonth">
								</c:when>
								<c:otherwise>
									<div class="calendarMonth hidden-xs">
								</c:otherwise>
							</c:choose>
								<c:forEach var="event" items="${events[loop.index]}">
									<c:if test="${event.isPaid()}">
										<c:set var="paid" value="paid" />
									</c:if>
									<c:if test="${event.isPaid() eq false}">
										<c:set var="paid" value="notPaid" />
									</c:if>
									<p class="eventLabel ${paid}"><a href='<c:url value="/edit-event-${event.id}"/>'><span>${event.calendarLabel}</span></a></p>
								</c:forEach>	
							</div>
						</c:forEach>
					</div>
					<div class="row">
						<div class="calendarMonth noMinHeight" onclick="window.location.href ='<c:url value="/month?m=${5}&y=${year}"></c:url>'"><p class="monthLabel">MAY - ${fn:length(events[4])}</p></div>
						<div class="calendarMonth noMinHeight" onclick="window.location.href ='<c:url value="/month?m=${6}&y=${year}"></c:url>'"><p class="monthLabel">JUNE - ${fn:length(events[5])}</p></div>
						<div class="calendarMonth noMinHeight" onclick="window.location.href ='<c:url value="/month?m=${7}&y=${year}"></c:url>'"><p class="monthLabel">JULY - ${fn:length(events[6])}</p></div>
						<div class="calendarMonth noMinHeight" onclick="window.location.href ='<c:url value="/month?m=${8}&y=${year}"></c:url>'"><p class="monthLabel">AUGUST - ${fn:length(events[7])}</p></div>
					</div>
					<div class="row">
						<c:set var="row" value="even" />
						<c:set var="paid" value="notPaid" />
						<c:forEach begin="4" end="7" varStatus="loop">  
							<c:choose>
								<c:when test="${fn:length(events[loop.index])>0}">
									<div class="calendarMonth">
								</c:when>
								<c:otherwise>
									<div class="calendarMonth hidden-xs">
								</c:otherwise>
							</c:choose>
								<c:forEach var="event" items="${events[loop.index]}">
										<c:if test="${event.isPaid()}">
											<c:set var="paid" value="paid" />
										</c:if>
										<c:if test="${event.isPaid() eq false}">
											<c:set var="paid" value="notPaid" />
										</c:if>
											<p class="eventLabel ${paid}"><a href='<c:url value="/edit-event-${event.id}"/>'><span>${event.calendarLabel}</span></a></p>									
								</c:forEach>
							</div>
						</c:forEach>
					</div>
					<div class="row">
						<div class="calendarMonth noMinHeight" onclick="window.location.href ='<c:url value="/month?m=${9}&y=${year}"></c:url>'"><p class="monthLabel">SEPTEMBER - ${fn:length(events[8])}</p></div>
						<div class="calendarMonth noMinHeight" onclick="window.location.href ='<c:url value="/month?m=${10}&y=${year}"></c:url>'"><p class="monthLabel">OCTUBER - ${fn:length(events[9])}</p></div>
						<div class="calendarMonth noMinHeight" onclick="window.location.href ='<c:url value="/month?m=${11}&y=${year}"></c:url>'"><p class="monthLabel">NOVEMBER - ${fn:length(events[10])}</p></div>
						<div class="calendarMonth noMinHeight" onclick="window.location.href ='<c:url value="/month?m=${12}&y=${year}"></c:url>'"><p class="monthLabel">DECEMBER - ${fn:length(events[11])}</p></div>
					</div>
					<div class="row">
						<c:set var="row" value="even" />
						<c:set var="paid" value="notPaid" />
						<c:forEach begin="8" end="11" varStatus="loop">  
							<c:choose>
								<c:when test="${fn:length(events[loop.index])>0}">
									<div class="calendarMonth">
								</c:when>
								<c:otherwise>
									<div class="calendarMonth hidden-xs">
								</c:otherwise>
							</c:choose>
								<c:forEach var="event" items="${events[loop.index]}">
									<c:if test="${event.isPaid()}">
										<c:set var="paid" value="paid" />
									</c:if>
									<c:if test="${event.isPaid() eq false}">
										<c:set var="paid" value="notPaid" />
									</c:if>
									<p class="eventLabel ${paid}"><a href='<c:url value="/edit-event-${event.id}"/>'><span>${event.calendarLabel}</span></a></p>
								</c:forEach>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<div class="tab-pane" id="tab_list">
			<div class="table-responsive">
				<table class="table table-bordered" width="100%" id="eventsTable"> 
					<thead>
						<tr>
							<th>Event Date</th>
							<th>Event Name</th>
							<th>Client</th>
							<th>Location</th>
							<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
								<th width="100"></th>
							</sec:authorize>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="event" items="${eventsList}" varStatus="index">
							<c:set var="paid" value="notPaid" />
							<c:if test="${event.isPaid()}">
								<c:set var="paid" value="paid" />
							</c:if>
							<c:if test="${event.isPaid() eq false}">
								<c:set var="paid" value="notPaid" />
							</c:if>
							<tr>
								<td>${event.formatedDateAndHour}</td>
								<td>
									<span class="${paid}">${paid}</span> ${event.eventName} 
								</td>
								<td>${event.client.name}</td>
								<td>${event.location.buildingName}</td>
								<td>
									<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
										<button type="button" class="btn btn-success formEditButtonIcon col-lg-6 col-md-6 col-ms-6 col-xs-6"
												onclick="window.location.href ='<c:url value="/edit-event-${event.id}" />'">
												<span class="glyphicon glyphicon-pencil"></span><span
													class="hidden-ms hidden-xs"> Edit</span>
										</button>
									</sec:authorize>
									<sec:authorize access="hasRole('ADMIN')">
										<button type="button" class="btn btn-success formDeleteButtonIcon col-lg-6 col-md-6 col-ms-6 col-xs-6"
												onclick="contract(${event.id})">
												<span class="glyphicon glyphicon-download-alt"></span><span
													class="hidden-ms hidden-xs"> Contract</span>
											</button>
									</sec:authorize>
								</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>		
	
		</div>
	</div>
</div>
<div class="container divBottomButton">
	<button type="button" class="btn btn-success btn-lg " 
		onclick="window.location.href ='<c:url value="/newEvent" />'">
	<span class="glyphicon glyphicon-plus"></span>New Event</button>
</div>
<c:if test="${not empty incompleteEvents}">
<div class="container well incompleteContainer">
	<c:forEach items="${incompleteEvents}" var="event" varStatus="loopindex">
		<div class="incompleteCards" onclick="location.href = '<c:url value="/edit-event-${event.id}"/>'">
			<p class="client">${event.client.name}</p>
			<p class="event_name">${event.eventName}</p>
			<span class="contactDateLabel">Contact Date: </span><span class="contactDate">${event.contact_date}</span>
			<c:if test="${not empty event.items}">
				<p class="item"><span>${event.items[0].item.description}</span><span> ${event.items[0].quantity}</span></p>
			</c:if>
		</div>
	</c:forEach>
</div>
</c:if>
<script type="text/javascript" src="<c:url value='/static/js/event.js' />"></script>

<script src="<c:url value='/static/js/jquery.dataTables.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/static/js/dataTables.bootstrap.min.js' />" type="text/javascript"></script>
<script type="text/javascript">
	var context = '${pageContext.request.contextPath}';
	$("#eventsTable").DataTable();
	
</script>