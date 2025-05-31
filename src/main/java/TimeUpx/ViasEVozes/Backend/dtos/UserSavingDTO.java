package TimeUpx.ViasEVozes.Backend.dtos;

import TimeUpx.ViasEVozes.Backend.values.*;
import jakarta.validation.constraints.*;

public record UserSavingDTO(
		@NotBlank String name,
		@NotNull UserRole role,
		@NotBlank String password,
		@NotBlank String picturePlaceholder,
		@NotBlank @Email String email,
		Boolean preferAnonymous
) {}
