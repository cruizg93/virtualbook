package com.blacktierental.virtualbook.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeParser {

	public static void main(String[] args) {
		String fecha = "2017-07-1308:07:21";
		System.out.println(fecha.split(" ").length);
		DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    //LocalDate ld = LocalDate.parse(fecha, DATEFORMATTER);
/*	    LocalTime lt = LocalTime.parse(fecha, DATEFORMATTER);
	    LocalDateTime ldt = LocalDateTime.of(ld,lt);
	*/    
	    //System.out.println(ld);
	}

}
