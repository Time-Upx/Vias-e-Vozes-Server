package TimeUpx.ViasEVozes.Backend.dto.list;

import TimeUpx.ViasEVozes.Backend.entities.*;
import lombok.*;

@Builder (setterPrefix = "with")
public record UserListDTO(
		Long id,
		String name,
		byte[] profilePicture,
		String email,
		boolean preferAnonymous,
		boolean isActive
) {
	public static UserListDTO of (User user) {
		return builder()
				.withId(user.id())
				.withName(user.name())
				.withProfilePicture(user.profilePicture().content())
				.withEmail(user.email())
				.withPreferAnonymous(user.preferAnonymous())
				.withIsActive(user.isActive())
				.build();
	}
}
