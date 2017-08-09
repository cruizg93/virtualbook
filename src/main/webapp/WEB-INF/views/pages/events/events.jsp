<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<link href="<c:url value='/static/css/calendar.css' />" rel="stylesheet"></link>
<script>
var month = ${month};
var year = '${year}';
</script>
<div id="divCurrentMonth" class="container well text-center" style="padding-top:0px;">
	<div class="panel panel-default" style="margin-bottom: 0px;">
		<div class="row" id="divMonthTitle">
		 	<div class="col-md-12 col-sm-12">
		 		<h2>
		 			<span class="arrow clickable glyphicon glyphicon-menu-left" 
						onclick="window.location.href ='<c:url value="/previousMonth?m=${month}&y=${year}"></c:url>'"></span>
					<span id="hMonthtitle"></span>
					<span class="arrow clickable glyphicon glyphicon-menu-right"
					onclick="window.location.href ='<c:url value="/nextMonth?m=${month}&y=${year}"></c:url>'"></span>
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
					<div class="row">
						<div class="calendarDay noMinHeight hidden-xs"><p class="dayLabel">MONDAY</p></div>
						<div class="calendarDay noMinHeight hidden-xs"><p class="dayLabel">TUESDAY</p></div>
						<div class="calendarDay noMinHeight hidden-xs"><p class="dayLabel">WEDNESDAY</p></div>
						<div class="calendarDay noMinHeight hidden-xs"><p class="dayLabel">THURSDAY</p></div>
						<div class="calendarDay noMinHeight hidden-xs"><p class="dayLabel">FRIDAY</p></div>
						<div class="calendarDay noMinHeight hidden-xs"><p class="dayLabel">SATURDAY</p></div>
						<div class="calendarDay noMinHeight hidden-xs"><p class="dayLabel">SUNDAY</p></div>
					</div>
					<c:set var="row" value="even" />
					<c:set var="paid" value="notPaid" />
					
					<c:forEach var="dayEvents" items="${events}" varStatus="loopindex">
						<c:set var="hiddenXS" value=" " />
						<c:if test="${fn:length(dayEvents) eq 0}">
							<c:set var="hiddenXS" value="hidden-xs" />
						</c:if>
						<c:if test="${loopindex.index mod 7 == 0}">
							<c:choose>
								<c:when test="${row eq 'odd'}"><c:set var="row" value="even" /></c:when>
								<c:otherwise><c:set var="row" value="odd" /></c:otherwise>
							</c:choose>				
						</c:if>
						<c:if test="${(loopindex.index == 0)|| (loopindex.index == 7) || (loopindex.index == 14) || (loopindex.index == 21) || (loopindex.index == 28)|| (loopindex.index == 35) || (loopindex.index == 42) || (loopindex.index == 49)}">
							<c:out value="<div class='row ${row}'>" escapeXml="false"/>
						</c:if>
						<div class="calendarDay ${row} ${hiddenXS}">
							<c:choose>
								<c:when
									test="${(loopindex.count > emptySpotsAtBegin) && (loopindex.count < fn:length(events)-emptySpotsAtEnd)}">
									<c:set var="dayDate" value="${year}-${month}-${loopindex.count - emptySpotsAtBegin}"/>
									<p class="dayNumber" onclick="window.location.href ='<c:url value="/newEvent-${dayDate}"/>'">${loopindex.count - emptySpotsAtBegin}</p>
									<c:forEach var="event" items="${dayEvents}">
										<c:if test="${event.isPaid()}">
											<c:set var="paid" value="paid" />
										</c:if>
										<c:if test="${event.isPaid() eq false}">
											<c:set var="paid" value="notPaid" />
										</c:if>
										<c:if test="${not empty event.client}">
											<p class="eventLabel ${paid}"><a href='<c:url value="/edit-event-${event.id}"/>'><span>${fn:substring(event.client.companyName, 0, 15)}</span></a></p>
										</c:if>
										<c:if test="${empty event.client}">
											<p class="eventLabel ${paid}"><a href='<c:url value="/edit-event-${event.id}"/>'><span>${fn:substring(event.eventName, 0, 15)}</span></a></p>
										</c:if>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<p class="dayNumber hidden-xs">-</p>
								</c:otherwise>
							</c:choose>
						</div>
						<c:if test="${(loopindex.index == 6) || (loopindex.index == 13) || (loopindex.index == 20) || (loopindex.index == 27)
							|| (loopindex.index == 34) || (loopindex.index == 41) || (loopindex.index == 48)}">
							<c:out value="</div>" escapeXml="false"/>
						</c:if>
					</c:forEach>
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