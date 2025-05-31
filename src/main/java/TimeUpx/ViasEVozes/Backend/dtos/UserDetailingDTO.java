package TimeUpx.ViasEVozes.Backend.dtos;

import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.services.*;
import TimeUpx.ViasEVozes.Backend.values.*;
import lombok.*;
import org.springframework.data.domain.*;

import java.time.*;

@Builder (setterPrefix = "with")
public record UserDetailingDTO(
		Long id,
		String name,
		UserRole role,
		String password,
		Image.DTO profilePicture,
		String email,
		boolean preferAnonymous,
		LocalDateTime timeOfArrival,
		boolean isActive
) {
	public static UserDetailingDTO of (User user) {
		if (user == null) return null;

		return UserDetailingDTO.builder()
				.withId(user.id())
				.withName(user.name())
				.withRole(user.role())
				.withPassword(user.password())
				.withProfilePicture(Image.toDTO(user.profilePicture()))
				.withEmail(user.email())
				.withPreferAnonymous(user.preferAnonymous())
				.withTimeOfArrival(user.timeOfArrival())
				.withIsActive(user.isActive())
				.build();
	}
}
