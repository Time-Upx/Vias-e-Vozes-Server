package TimeUpx.ViasEVozes.Backend.dtos;

import jakarta.validation.constraints.*;

public record CommentUpdatingDTO(@NotBlank String body) {
}
