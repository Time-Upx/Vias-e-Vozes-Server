package TimeUpx.ViasEVozes.Backend.dtos;

import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.values.*;
import lombok.*;

import java.time.*;

@Builder (setterPrefix = "with")
public record UserListingDTO(
		Long id,
		String name,
		LocalDateTime dateOfArrival,
		UserRole role,
		boolean preferAnonymous
) {
	public static UserListingDTO of (User user) {
		return builder()
				.withId(user.id())
				.withName(user.name())
				.withDateOfArrival(user.dateOfArrival())
				.withRole(user.role())
				.withPreferAnonymous(user.preferAnonymous())
				.build();
	}
}
