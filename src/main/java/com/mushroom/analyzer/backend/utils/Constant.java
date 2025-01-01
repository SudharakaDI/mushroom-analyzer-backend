package com.mushroom.analyzer.backend.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

    public static final String ERROR_TYPE_SERVER = "Internal server error";
    public static final String ERROR_TYPE_INVALID_INPUT = "Invalid input values";
    public static final String ERROR_TYPE_OTHER = "Other";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");



}
