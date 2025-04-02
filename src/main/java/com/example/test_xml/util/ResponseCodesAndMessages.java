package com.example.test_xml.util;

public class ResponseCodesAndMessages {

    public static final int SUCCESS_STATUS = 200;
    public static final String SUCCESS_CODE = "SUCCESS";
    public static final String SUCCESS_MESSAGES = "Success";

    public static final int UNSUCCESS_STATUS = 400;
    public static final String UNSUCCESS_CODE = "UNSUCCESS";
    public static final String UNSUCCESS_MESSAGES = "Unsuccess";

    public static final int INVALID_ARGUMENT_STATUS = 400;
    public static final String INVALID_ARGUMENT_CODE = "INVALID_ARGUMENT";
    public static final String INVALID_ARGUMENT_MESSAGES = "Client specified an invalid argument, request body or query param.";

    //    UNAUTHENTICATED
    public static final int UNAUTHENTICATED_STATUS = 401;
    public static final String UNAUTHENTICATED_CODE = "UNAUTHENTICATED";
    public static final String UNAUTHENTICATED_MESSAGES = "Request not authenticated due to missing, invalid, or expired credentials.";

    //    PERMISSION_DENIED
    public static final int PERMISSION_DENIED_STATUS = 403;
    public static final String PERMISSION_DENIED_CODE = "PERMISSION_DENIED";
    public static final String PERMISSION_DENIED_MESSAGE = "Client does not have sufficient permissions to perform this action.";

    //    NOT_FOUND
    public static final int NOT_FOUND_STATUS = 404;
    public static final String NOT_FOUND_CODE = "NOT_FOUND";
    public static final String NOT_FOUND_MESSAGE = "is not found";

    //    QUOTA_EXCEEDED
    public static final int QUOTA_EXCEEDED_STATUS = 429;
    public static final String QUOTA_EXCEEDED_CODE = "QUOTA_EXCEEDED";
    public static final String QUOTA_EXCEEDED_MESSAGE = "Either out of resource quota or reaching rate limiting.";

    //    INTERNAL
    public static final int INTERNAL_STATUS = 500;
    public static final String INTERNAL_CODE = "INTERNAL";
    public static final String INTERNAL_MESSAGE = "Internal server error";

    //    UNAVAILABLE
    public static final int UNAVAILABLE_STATUS = 503;
    public static final String UNAVAILABLE_CODE = "UNAVAILABLE";
    public static final String UNAVAILABLE_MESSAGE = "Service unavailable";

    // --------------------- CUSTOM EXCEPTION ---------------------
    // No Such Algorithm Exception
    public static final int NO_SUCH_ALGORITHM_STATUS = 501;
    public static final String NO_SUCH_ALGORITHM_CODE = "NO_SUCH_ALGORITHM";
    public static final String NO_SUCH_ALGORITHM_MESSAGE = "Requested algorithm is not available";

    // Client Error
    public static final int CLIENT_ERROR_STATUS = 400;
    public static final String CLIENT_ERROR_CODE = "CLIENT_ERROR";
    public static final String CLIENT_ERROR_MESSAGE = "Bad request from the client";

    // Validation Error
    public static final int VALIDATION_ERROR_STATUS = 422;
    public static final String VALIDATION_ERROR_CODE = "VALIDATION_ERROR";
    public static final String VALIDATION_ERROR_MESSAGE = "Validation failed for the input data";

    // Data Power Error
    public static final int DATA_POWER_ERROR_STATUS = 503;
    public static final String DATA_POWER_ERROR_CODE = "DATA_POWER_ERROR";
    public static final String DATA_POWER_ERROR_MESSAGE = "Data power service is temporarily unavailable";

    // Status Invalid Error
    public static final int STATUS_INVALID_STATUS = 400;
    public static final String STATUS_INVALID_CODE = "STATUS_INVALID";
    public static final String STATUS_INVALID_MESSAGE = "Invalid status provided in the request";

    // Jwt Token Error
    public static final int JWT_TOKEN_ERROR_STATUS = 400;
    public static final String JWT_TOKEN_ERROR_CODE = "ERROR";
    public static final String JWT_TOKEN_ERROR_MESSAGE = "Something went wrong";
}
