package com.busqueda.proyecto.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProjectUtils {

	public static final String LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

	public static LocalDateTime getLocalDateTimeNow() {
		return LocalDateTime.now();
	}

	public static LocalDate getLocalDateNow() {
		return LocalDate.now();
	}

}
