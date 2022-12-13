package com.heybys.optimusamicus.user.exception;

import com.heybys.optimusamicus.user.exception.enums.UserError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserBaseException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final UserError error;

  public UserBaseException(UserError error) {
    this.error = error;
  }

  public UserBaseException(UserError error, Throwable e) {
    super(e);
    this.error = error;
  }

  public UserError getError() {
    return error;
  }

  public void log() {
    log.error("[Exception]: " + this.getClass().getName() + ", [Cause]: " + this.getCause());
  }
}
