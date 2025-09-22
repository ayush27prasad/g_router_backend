package net.gpu.grouterbe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    public GlobalExceptionHandler() {}

    // Handle LLM-related exceptions
    @ExceptionHandler(LlmException.class)
    public ResponseEntity<Map<String, Object>> handleLlmException(LlmException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "LLM_ERROR", ex.getMessage());
    }

    // Handle Chat-related exceptions
    @ExceptionHandler(ChatException.class)
    public ResponseEntity<Map<String, Object>> handleChatException(ChatException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "CHAT_ERROR", ex.getMessage());
    }

    // Fallback for all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String code, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("code", code);
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}
