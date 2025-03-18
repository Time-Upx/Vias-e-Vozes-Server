package TimeUpx.ViasEVozes.Backend.dto;

import TimeUpx.ViasEVozes.Backend.values.*;

public record UserRegisterDTO (
	String name,
	UserRole role,
	String password,
	ImageValue profilePicture,
	String email
) {}
