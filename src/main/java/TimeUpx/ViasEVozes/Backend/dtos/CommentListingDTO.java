package TimeUpx.ViasEVozes.Backend.dtos;

import TimeUpx.ViasEVozes.Backend.entities.*;
import lombok.*;

import java.time.*;

@Builder (setterPrefix = "with")
public record CommentListingDTO(
		long id,
		String body,
		UserListingDTO author,
		long contributionId,
		int quantityOfLikes,
		LocalDateTime timeOfComment
) {
	public static CommentListingDTO of (Comment comment) {
		return CommentListingDTO.builder()
				.withId(comment.id())
				.withBody(comment.body())
				.withAuthor(UserListingDTO.of(comment.author()))
				.withContributionId(comment.contribution().id())
				.withQuantityOfLikes(comment.quantityOfLikes())
				.withTimeOfComment(comment.timeOfComment())
				.build();
	}
}
