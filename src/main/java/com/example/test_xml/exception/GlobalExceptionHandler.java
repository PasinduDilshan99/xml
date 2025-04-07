package com.example.test_xml.exception;

import com.example.test_xml.model.response.ErrorResponse;
import com.example.test_xml.util.Constant;
import com.example.test_xml.util.ResponseCodesAndMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ConvertErrorException.class})
    public ResponseEntity<ErrorResponse> handleConvertErrorException(ConvertErrorException e) {
        logger.error("{} Convert Error Exception : {} {}", Constant.ERROR_DOTS_START, e, Constant.ERROR_DOTS_END);
        ErrorResponse errorResponse = new ErrorResponse(
                ResponseCodesAndMessages.CONVERT_ERROR_STATUS,
                ResponseCodesAndMessages.CONVERT_ERROR_CODE,
                e.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {InternalServerErrorException.class})
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(InternalServerErrorException e) {
        logger.error("{} Internal Server Error Exception : {} {}", Constant.ERROR_DOTS_START, e, Constant.ERROR_DOTS_END);
        ErrorResponse errorResponse = new ErrorResponse(
                ResponseCodesAndMessages.INTERNAL_STATUS,
                ResponseCodesAndMessages.INTERNAL_CODE,
                e.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InvalidArgumentErrorException.class})
    public ResponseEntity<ErrorResponse> handleInvalidArgumentErrorException(InvalidArgumentErrorException e) {
        logger.error("{} Invalid Argument Error Exception : {} {}", Constant.ERROR_DOTS_START, e, Constant.ERROR_DOTS_END);
        ErrorResponse errorResponse = new ErrorResponse(
                ResponseCodesAndMessages.INVALID_ARGUMENT_STATUS,
                ResponseCodesAndMessages.INVALID_ARGUMENT_CODE,
                e.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InvalidDataErrorException.class})
    public ResponseEntity<ErrorResponse> handleInvalidDataErrorException(InvalidDataErrorException e) {
        logger.error("{} Invalid Data Error Exception : {} {}", Constant.ERROR_DOTS_START, e, Constant.ERROR_DOTS_END);
        ErrorResponse errorResponse = new ErrorResponse(
                ResponseCodesAndMessages.INVALID_DATA_STATUS,
                ResponseCodesAndMessages.INVALID_DATA_CODE,
                e.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NotFoundErrorException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundErrorException(NotFoundErrorException e) {
        logger.error("{} Not Found Error Exception : {} {}", Constant.ERROR_DOTS_START, e, Constant.ERROR_DOTS_END);
        ErrorResponse errorResponse = new ErrorResponse(
                ResponseCodesAndMessages.NOT_FOUND_STATUS,
                ResponseCodesAndMessages.NOT_FOUND_CODE,
                e.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ValidationErrorException.class})
    public ResponseEntity<ErrorResponse> handleValidationErrorException(ValidationErrorException e) {
        logger.error("{} Validation Error Exception : {} {}", Constant.ERROR_DOTS_START, e, Constant.ERROR_DOTS_END);
        ErrorResponse errorResponse = new ErrorResponse(
                ResponseCodesAndMessages.VALIDATION_ERROR_STATUS,
                ResponseCodesAndMessages.VALIDATION_ERROR_CODE,
                e.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
