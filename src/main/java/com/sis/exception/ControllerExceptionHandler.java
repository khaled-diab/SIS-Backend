//package com.sis.exception;
//
//import com.sis.util.MessageResponse;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//
//import java.util.Objects;
//
//
//@ControllerAdvice
//public class ControllerExceptionHandler {
//    private static final Logger log = LogManager.getLogger(ControllerExceptionHandler.class);
//
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<MessageResponse> globalExceptionHandler(Exception ex, WebRequest request) {
//        log.error(ex.getMessage());
//        return new ResponseEntity<>(new MessageResponse("An error has occurred please try again later"),
//                HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(InvalidUserNameOrPasswordException.class)
//    public ResponseEntity<MessageResponse> globalExceptionHandler(InvalidUserNameOrPasswordException ex, WebRequest request) {
//        log.error(ex.getMessage());
//        return new ResponseEntity<>(new MessageResponse(ex.getMessage()),
//                HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(UserNameOrEmailAlreadyExistException.class)
//    public ResponseEntity<MessageResponse> globalExceptionHandler(UserNameOrEmailAlreadyExistException ex, WebRequest request) {
//        log.error(ex.getMessage());
//        return new ResponseEntity<>(new MessageResponse(ex.getMessage()),
//                HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<MessageResponse> globalExceptionHandler(UserNotFoundException ex, WebRequest request) {
//        log.error(ex.getMessage());
//        return new ResponseEntity<>(new MessageResponse(ex.getMessage()),
//                HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(ItemNotFoundException.class)
//    public ResponseEntity<MessageResponse> globalExceptionHandler(ItemNotFoundException ex, WebRequest request) {
//        log.error(ex.getMessage());
//        return new ResponseEntity<>(new MessageResponse(ex.getMessage()),
//                HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<MessageResponse> globalExceptionHandler(MethodArgumentNotValidException ex, WebRequest request) {
//        MessageResponse messageResponse = new MessageResponse(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage());
//        messageResponse.setField(ex.getFieldError().getField());
//
//        log.error(ex.getFieldError());
//        return new ResponseEntity<>(messageResponse,
//                HttpStatus.BAD_REQUEST);
//
//    }
//
//
//    @ExceptionHandler(StudentFieldNotUniqueException.class)
//    public ResponseEntity<MessageResponse> globalExceptionHandler(StudentFieldNotUniqueException ex, WebRequest request) {
//        log.error(ex.getMessage());
//        return new ResponseEntity<>(new MessageResponse( ex.getMessage(),ex.getField()),
//                HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(SectionFieldNotUniqueException.class)
//    public ResponseEntity<MessageResponse> globalExceptionHandler(SectionFieldNotUniqueException ex, WebRequest request) {
//        log.error(ex.getMessage());
//        return new ResponseEntity<>(new MessageResponse(ex.getMessage(), ex.getField()),
//                HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(StudentEnrollmentFieldNotUniqueException.class)
//    public ResponseEntity<MessageResponse> globalExceptionHandler(StudentEnrollmentFieldNotUniqueException ex, WebRequest request) {
//        log.error(ex.getMessage());
//        return new ResponseEntity<>(new MessageResponse(ex.getMessage(), ex.getField()),
//                HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(TimetableFieldNotUniqueException.class)
//    public ResponseEntity<MessageResponse> globalExceptionHandler(TimetableFieldNotUniqueException ex, WebRequest request) {
//        log.error(ex.getMessage());
//        return new ResponseEntity<>(new MessageResponse(ex.getMessage(), ex.getField()),
//                HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(CourseFieldNotUniqueException.class)
//    public ResponseEntity<MessageResponse> globalExceptionHandler(CourseFieldNotUniqueException ex, WebRequest request) {
//        log.error(ex.getMessage());
//        return new ResponseEntity<>(new MessageResponse(ex.getMessage(), ex.getField()),
//                HttpStatus.BAD_REQUEST);
//    }
//
//
//}
