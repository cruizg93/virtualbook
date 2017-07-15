<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="<c:url value='/static/css/calendar.css' />" rel="stylesheet"></link>
<script>
var month = ${month};
var year = '${year}';
</script>
<div id="divCurrentMonth" class="container well text-center">
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
	 		<button type="button" class="btn btn-primary btn-lg formAddButtonIcon" 
				onclick="window.location.href ='<c:url value="/newEvent" />'">
				<span class="glyphicon glyphicon-plus"></span>New Event</button>
		</div>
	</div>
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
			<div class="row ${row}">
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
				<c:if test="${(loopindex.index == 7) || (loopindex.index == 14) || (loopindex.index == 21) || (loopindex.index == 28)|| (loopindex.index == 35) || (loopindex.index == 42) || (loopindex.index == 49)}">
					<c:out value="<div class='row ${row}'>" escapeXml="false"/>
				</c:if>
				<div class="calendarDay ${row} ${hiddenXS}">
					<c:choose>
						<c:when
							test="${(loopindex.count > emptySpotsAtBegin) && (loopindex.count < fn:length(events)-emptySpotsAtEnd)}">
							<p class="dayNumber">${loopindex.count - emptySpotsAtBegin}</p>
							<c:forEach var="event" items="${dayEvents}">
								<a href='<c:url value="/edit-event-${event.id}"/>'><span>${fn:substring(event.client.companyName, 0, 15)}</span></a><br>
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
<div class="container divBottomButton">
	<button type="button" class="btn btn-primary btn-lg " 
		onclick="window.location.href ='<c:url value="/newEvent" />'">
	<span class="glyphicon glyphicon-plus"></span>New Event</button>
</div>
<script type="text/javascript" src="<c:url value='/static/js/event.js' />"></script>
