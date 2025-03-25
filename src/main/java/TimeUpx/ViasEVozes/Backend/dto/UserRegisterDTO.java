package TimeUpx.ViasEVozes.Backend.dto;

import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.values.*;

public record UserRegisterDTO (
	String name,
	UserRole role,
	String password,
	Image profilePicture,
	String email,
	boolean preferAnonymous
) {}
