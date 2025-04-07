package com.example.test_xml.util;

public class ResponseCodesAndMessages {

    // SUCCESS
    public static final int SUCCESS_STATUS = 200;
    public static final String SUCCESS_CODE = "SUCCESS";
    public static final String SUCCESS_MESSAGES = "Success";

    // UNSUCCESS
    public static final int UNSUCCESS_STATUS = 400;
    public static final String UNSUCCESS_CODE = "UNSUCCESS";
    public static final String UNSUCCESS_MESSAGES = "Unsuccess";

    // INVALID_ARGUMENT
    public static final int INVALID_ARGUMENT_STATUS = 400;
    public static final String INVALID_ARGUMENT_CODE = "INVALID_ARGUMENT";
    public static final String INVALID_ARGUMENT_MESSAGES = "Client specified an invalid argument, request body or query param.";

    //    NOT_FOUND
    public static final int NOT_FOUND_STATUS = 404;
    public static final String NOT_FOUND_CODE = "NOT_FOUND";
    public static final String NOT_FOUND_MESSAGE = "is not found";

    //    INTERNAL
    public static final int INTERNAL_STATUS = 500;
    public static final String INTERNAL_CODE = "INTERNAL";
    public static final String INTERNAL_MESSAGE = "Internal server error";

    //    INTERNAL
    public static final int INVALID_DATA_STATUS = 400;
    public static final String INVALID_DATA_CODE = "INVALID_DATA";
    public static final String INVALID_DATA_MESSAGE = "Load invalid data from database";

    // Validation Error
    public static final int VALIDATION_ERROR_STATUS = 422;
    public static final String VALIDATION_ERROR_CODE = "VALIDATION_ERROR";
    public static final String VALIDATION_ERROR_MESSAGE = "Validation failed for the input data";

    // Convert Error
    public static final int CONVERT_ERROR_STATUS = 400;
    public static final String CONVERT_ERROR_CODE = "CONVERT_ERROR";
    public static final String CONVERT_ERROR_MESSAGE = "Error Occur when converting.";

}
