package TimeUpx.ViasEVozes.Backend.dto.register;

import TimeUpx.ViasEVozes.Backend.values.*;
import jakarta.validation.constraints.*;

public record UserRegisterDTO (
	@NotBlank
	String name,
	@NotNull
	UserRole role,
	@NotBlank
	String password,
	byte[] profilePictureContent,
	@NotBlank
	@Email
	String email,
	Boolean preferAnonymous
) {}
