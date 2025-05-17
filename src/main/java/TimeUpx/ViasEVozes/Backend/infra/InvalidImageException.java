package TimeUpx.ViasEVozes.Backend.infra;

import java.io.*;

public class InvalidImageException extends RuntimeException {
	public InvalidImageException (String message) {
		super(message);
	}
	public InvalidImageException (IOException exception) {
		super(exception);
	}
}
