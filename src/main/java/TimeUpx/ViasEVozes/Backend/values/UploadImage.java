package TimeUpx.ViasEVozes.Backend.values;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record UploadImage(
		@NotBlank MultipartFile file,
		String placeholder
) {}
