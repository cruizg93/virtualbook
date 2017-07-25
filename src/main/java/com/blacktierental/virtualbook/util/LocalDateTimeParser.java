package com.blacktierental.virtualbook.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateTimeParser {

	public static void main(String[] args) {
		String fecha = "2017-07-1308:07:21";
		System.out.println(fecha.split(" ").length);
		DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime.now().format(formatter);
        //System.out.println(formatDateTime);
        DecimalFormatSymbols decimalSymbols = DecimalFormatSymbols.getInstance();
        decimalSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##",decimalSymbols);
        Locale.setDefault(new Locale("en","US"));
        System.out.println(df.format(35.42324));
        System.out.println(new Double(df.format(35.64000000001)));
        //LocalDate ld = LocalDate.parse(fecha, DATEFORMATTER);
/*	    LocalTime lt = LocalTime.parse(fecha, DATEFORMATTER);
	    LocalDateTime ldt = LocalDateTime.of(ld,lt);
	*/    
	    //System.out.println(ld);
	}

}
