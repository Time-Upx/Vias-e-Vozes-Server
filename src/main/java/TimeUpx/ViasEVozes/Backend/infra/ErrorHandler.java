package TimeUpx.ViasEVozes.Backend.infra;

import jakarta.persistence.*;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler (EntityNotFoundException.class)
	public ResponseEntity handleNotFoundException () {
		return ResponseEntity.notFound().build();
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
		record Message(String error) {}
		return ResponseEntity.badRequest().body(new Message(exception.getMessage()));
	}

	@ExceptionHandler (EntityExistsException.class)
	public ResponseEntity handleAlreadyExistingEntity (EntityExistsException exception) {
		record Message(String error) {}
		return ResponseEntity.badRequest().body(new Message(exception.getMessage()));
	}

	@ExceptionHandler (InvalidImageException.class)
	public ResponseEntity handleInvalidImageException (InvalidImageException exception) {
		record Message(String error) {}
		return ResponseEntity.badRequest().body(new Message(exception.getMessage()));
	}
}
