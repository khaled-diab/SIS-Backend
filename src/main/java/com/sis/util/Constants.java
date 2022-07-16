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
    public static final String FILE_TYPE_PROFILE_PICTURE = "PROFILE_PICTURE";
    public static final String FILE_TYPE_STUDENT_UPLOAD = "STUDENT_UPLOAD";
    public static final String FILE_TYPE_STAFF_UPLOAD = "STAFF_UPLOAD";
    public static final String PROFILE_PICTURE_FOLDER_NAME = "profilePicture";
    public static final String DASH_DELIMITER = "-";

    public static final String FILE_STATUS_IN_PROGRESS = "IN_PROGRESS";
    public static final String FILE_STATUS_DONE = "DONE";
    public static final String FIELD = "Field";
    public static final String ERROR = "Error";

    public static final String ADMIN_USER_NAME = "admin";
    public static final String STUDENT_UPLOAD_FOLDER_NAME = "studentUpload";


    private Constants() {

    }

    public static String from12To24System(String time) {
        if (time.charAt(1) == ':') {
            time = '0' + time;
        }
        time = time.substring(0, 5);
        if (time.indexOf('P') > 0) {
            int hours = Integer.parseInt(time.substring(0, 2));
            hours += 12;
            String h = String.valueOf(hours);
            time = time.replaceFirst(time.substring(0, 2), h);
        }
        return time;
    }

    public static String from24To12System(String time) {
        if (time.charAt(1) == ':') {
            time = '0' + time;
        }
        time = time.substring(0, 5);
        int hours = Integer.parseInt(time.substring(0, 2));
        if (hours > 12) {
            hours -= 12;
            String h = String.valueOf(hours);
            time = time.replaceFirst(time.substring(0, 2), h) + " PM";
        } else {
            time += " AM";
        }
        return time;
    }
}
