package TimeUpx.ViasEVozes.Backend.dtos;

import jakarta.validation.constraints.*;

public record CommentSavingDTO(@NotBlank String body, @NotNull long authorId, @NotNull long contributionId) {
}
