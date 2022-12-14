package com.heybys.optimusamicus.common.exception;

import com.heybys.optimusamicus.common.model.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<CommonResponse> handleException(Exception exception) {
    log.error(
        "[Exception]: {}, [Cause]: {}, [Message]: {}",
        exception.getClass().getName(),
        exception.getCause(),
        exception.getMessage());

    return ResponseEntity.internalServerError().body(CommonResponse.fail("Internal Server Error"));
  }

  @ExceptionHandler(InvalidParameterException.class)
  protected ResponseEntity<CommonResponse> handleException(InvalidParameterException exception) {
    log.error(exception.getMessage());

    String message = CommonError.INVALID_PARAMETER.getMessage();
    return ResponseEntity.badRequest().body(CommonResponse.fail(message));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<CommonResponse> handleException(
      MethodArgumentNotValidException exception) {
    log.error(exception.getMessage());

    BindingResult bindingResult = exception.getBindingResult();
    log.error("===============");
    for (FieldError error : bindingResult.getFieldErrors()) {
      log.error(
          "{} ({}={})", error.getDefaultMessage(), error.getField(), error.getRejectedValue());
    }
    log.error("===============");

    String message = CommonError.METHOD_ARGUMENT_NOT_VALID.getMessage();
    return ResponseEntity.badRequest().body(CommonResponse.fail(message));
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  protected ResponseEntity<CommonResponse> handleException(
      MethodArgumentTypeMismatchException exception) {
    log.error(exception.getMessage());

    String message = CommonError.METHOD_ARGUMENT_TYPE_MISMATCH.getMessage();
    return ResponseEntity.badRequest().body(CommonResponse.fail(message));
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  protected ResponseEntity<CommonResponse> handleException(
      HttpRequestMethodNotSupportedException exception) {
    log.error(exception.getMessage());

    String message = CommonError.HTTP_REQUEST_METHOD_NOT_SUPPORTED.getMessage();
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(CommonResponse.fail(message));
  }
}
