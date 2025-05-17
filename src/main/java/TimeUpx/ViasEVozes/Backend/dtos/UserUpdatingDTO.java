package TimeUpx.ViasEVozes.Backend.dtos;

import TimeUpx.ViasEVozes.Backend.values.*;
import jakarta.validation.constraints.*;

public record UserUpdatingDTO(
		String name,
		UserRole role,
		String password,
		String pictureContent,
		@Email String email,
		Boolean preferAnonymous
) {}
