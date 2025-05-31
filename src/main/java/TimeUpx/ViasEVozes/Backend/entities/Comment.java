package TimeUpx.ViasEVozes.Backend.entities;

import TimeUpx.ViasEVozes.Backend.dtos.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

import java.time.*;

@AllArgsConstructor (access = AccessLevel.PRIVATE)
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@EqualsAndHashCode (of = {"id"})
@Accessors (fluent = true)
@Builder (setterPrefix = "with")
@Getter
@Entity
@Table (name = "comments")
public class Comment {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@Column (columnDefinition = "TEXT")
	private String body;

	@ManyToOne
	@JoinColumn (name = "author_id")
	private User author;

	@ManyToOne
	@JoinColumn (name = "contribution_id")
	private Contribution contribution;

	private int quantityOfLikes;

	public Comment adjustLikes (int value) {
		quantityOfLikes += value;
		if (quantityOfLikes < 0) quantityOfLikes = 0;
		return this;
	}

	private LocalDateTime timeOfComment;

	@Setter
	private boolean isActive;

	public CommentListingDTO listing () {
		return CommentListingDTO.of(this);
	}

	public static Comment of (CommentSavingDTO dto, User author, Contribution contribution) {
		if (dto == null) return null;

		return Comment.builder()
				.withBody(dto.body())
				.withAuthor(author)
				.withContribution(contribution)
				.withTimeOfComment(LocalDateTime.now())
				.withQuantityOfLikes(0)
				.withIsActive(true)
				.build();
	}
}
