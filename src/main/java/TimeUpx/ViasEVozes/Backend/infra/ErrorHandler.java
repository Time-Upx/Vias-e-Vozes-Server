package TimeUpx.ViasEVozes.Backend.infra;

import jakarta.persistence.*;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ErrorHandler {

	record Message(String error) {}

	@ExceptionHandler (EntityNotFoundException.class)
	public ResponseEntity handleNotFoundException (EntityNotFoundException exception) {
		return ResponseEntity.status(404).body(new Message(exception.getMessage()));
	}

	@ExceptionHandler (MethodArgumentNotValidException.class)
	public ResponseEntity handleValidationError (MethodArgumentNotValidException exception) {
		record ValidationError(String field, String message) {}
		var errors = exception.getFieldErrors().stream()
				.map(e -> new ValidationError(e.getField(), e.getDefaultMessage()))
				.toList();
		return ResponseEntity.badRequest().body(errors);
	}

	@ExceptionHandler (EntityActivityException.class)
	public ResponseEntity handleEntityActivityException (EntityActivityException exception) {
		return ResponseEntity.badRequest().body(new Message(exception.getMessage()));
	}

	@ExceptionHandler (EntityExistsException.class)
	public ResponseEntity handleAlreadyExistingEntity (EntityExistsException exception) {
		return ResponseEntity.badRequest().body(new Message(exception.getMessage()));
	}

	@ExceptionHandler (InvalidImageException.class)
	public ResponseEntity handleInvalidImageException (InvalidImageException exception) {
		return ResponseEntity.badRequest().body(new Message(exception.getMessage()));
	}
}
