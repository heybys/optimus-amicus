package com.heybys.optimusamicus.user.advice;

import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.common.model.CommonResponse.StatusCode;
import com.heybys.optimusamicus.user.controller.UserController;
import com.heybys.optimusamicus.user.exception.UserGroupNotCreatedException;
import com.heybys.optimusamicus.user.exception.UserGroupNotFoundException;
import com.heybys.optimusamicus.user.exception.UserNotCreatedException;
import com.heybys.optimusamicus.user.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = {UserController.class})
public class UserExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  protected ResponseEntity<CommonResponse> handleException(UserNotFoundException exception) {
    log.error(exception.getMessage());
    CommonResponse response = new CommonResponse(StatusCode.FAIL, exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UserNotCreatedException.class)
  protected ResponseEntity<CommonResponse> handleException(UserNotCreatedException exception) {
    log.error(exception.getMessage());
    CommonResponse response = new CommonResponse(StatusCode.FAIL, exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(UserGroupNotFoundException.class)
  protected ResponseEntity<CommonResponse> handleException(UserGroupNotFoundException exception) {
    log.error(exception.getMessage());
    CommonResponse response = new CommonResponse(StatusCode.FAIL, exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UserGroupNotCreatedException.class)
  protected ResponseEntity<CommonResponse> handleException(UserGroupNotCreatedException exception) {
    log.error(exception.getMessage());
    CommonResponse response = new CommonResponse(StatusCode.FAIL, exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }
}
