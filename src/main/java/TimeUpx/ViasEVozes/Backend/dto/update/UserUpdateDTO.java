package TimeUpx.ViasEVozes.Backend.dto.update;

import TimeUpx.ViasEVozes.Backend.values.*;

public record UserUpdateDTO(
	String name,
	UserRole role,
	String password,
	byte[] pictureContent,
	String email,
	Boolean preferAnonymous
) {}
