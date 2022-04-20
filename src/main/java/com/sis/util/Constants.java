package com.sis.util;

public class Constants {

    public static final String DATE_EXPRESSION = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$";

    /* Validation Regex */
    public static final String ENGLISH_CHARACTERS = "^[a-zA-Z ]+$";
    public static final String ENGLISH_CHARACTERS_OR_DIGITS = "^[a-zA-Z \\d]+$";
    public static final String ARABIC_CHARACTERS = "^[\\u0621-\\u064A ]+$";
    public static final String ARABIC_CHARACTERS_OR_DIGITS = "^[\\u0621-\\u064A \\d]+$";
    public static final String DIGITS_ONLY_14 = "^[0-9]{14}$";
    public static final String DIGITS_ONLY = "^[0-9]+$";
    public static final String DIGITS_ONLY_11 = "^[01][0-9]{10}$";
    public static final String TYPE_STUDENT = "STUDENT";
    public static final String TYPE_FACULTY_MEMBER = "FACULTY_MEMBER";
    public static final String COLLEGES_CASH_KEY = "COLLEGES";
    public static final String DEPARTMENTS_CASH_KEY = "DEPARTMENTS";


    private Constants() {

    }


}
