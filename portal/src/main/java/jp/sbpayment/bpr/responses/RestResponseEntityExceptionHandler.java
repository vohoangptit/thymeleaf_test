package jp.sbpayment.bpr.responses;

import java.util.NoSuchElementException;
import javax.persistence.EntityNotFoundException;
import jp.sbpayment.bpr.common.exceptions.BadRequestException;
import jp.sbpayment.bpr.common.exceptions.ConstException;
import jp.sbpayment.bpr.common.exceptions.ResourceNotFoundException;
import jp.sbpayment.bpr.common.response.CustomResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  public RestResponseEntityExceptionHandler() {
    super();
  }

  // 400
  @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class,
          ConstraintViolationException.class,
          DataIntegrityViolationException.class,
          BadRequestException.class})
  public ResponseEntity<Object> handleBadRequest(final RuntimeException ex,
          final WebRequest request) {
    return handleExceptionInternal(ex, new CustomResponse(ConstException.BAD_REQUEST),
            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
          final HttpMessageNotReadableException ex,
          final HttpHeaders headers,
          final HttpStatus status,
          final WebRequest request) {
    return handleExceptionInternal(ex, new CustomResponse(ConstException.BAD_REQUEST),
            headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          final MethodArgumentNotValidException ex,
          final HttpHeaders headers,
          final HttpStatus status,
          final WebRequest request) {
    return handleExceptionInternal(ex,
            new CustomResponse(ConstException.BAD_REQUEST),
            headers, HttpStatus.BAD_REQUEST, request);
  }

  // 404
  @ExceptionHandler(value = {NoSuchElementException.class,
          EntityNotFoundException.class, ResourceNotFoundException.class})
  protected ResponseEntity<Object> handleNotFound(final RuntimeException ex,
                                                  final WebRequest request) {
    return handleExceptionInternal(ex,
            new CustomResponse(
                    ConstException.NOT_FOUND), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  // 409
  @ExceptionHandler({InvalidDataAccessApiUsageException.class, DataAccessException.class})
  protected ResponseEntity<Object> handleConflict(final RuntimeException ex,
                                                  final WebRequest request) {
    return handleExceptionInternal(ex, new CustomResponse(ConstException.CONFLICT),
            new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  // 500
  @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class,
          IllegalStateException.class})
  public ResponseEntity<Object> handleInternal(final RuntimeException ex,
                                               final WebRequest request) {
    return handleExceptionInternal(ex, new CustomResponse(ConstException.INTERNAL_SERVER_ERROR),
            new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

}
