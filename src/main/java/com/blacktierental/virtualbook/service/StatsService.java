package com.blacktierental.virtualbook.service;

import java.util.List;

public interface StatsService {

	List monthlyCountByYear(int year);
	List clientsByYear(int year);
	List itemsByYear(int year);
}
