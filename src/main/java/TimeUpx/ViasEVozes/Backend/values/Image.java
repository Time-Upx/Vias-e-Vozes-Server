package TimeUpx.ViasEVozes.Backend.values;

import TimeUpx.ViasEVozes.Backend.infra.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;

@AllArgsConstructor (access = AccessLevel.PRIVATE)
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@Embeddable
@Accessors (fluent = true)
@Builder (setterPrefix = "with")
@Getter
public class Image {
	private static final int MAX_IMAGE_SIZE = 5 * 1024 * 1024; // 5MB
	private static final Set<String> ALLOWED_EXTENSIONS = Set.of("jpg", "jpeg", "png", "gif", "webp");

	private String fileName;
	private String extension;
	@Lob private byte[] content;
	private String placeholder;

	public record DTO(
			String fileName,
			String extension,
			byte[] content,
			String placeholder
	) {}

	public static DTO toDTO (Image image) {
		if (image == null) return null;
		return new DTO(image.fileName, image.extension, image.content, image.placeholder);
	}

	public static Image draft (String placeholder) {
		return Image.builder().withPlaceholder(placeholder).build();
	}

	public static Image of (MultipartFile file, String placeholder) {
		if (file == null) {
			return null;
		}
		try {
			String name = file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			String extension = file.getContentType().replace("image/", "");
			if (bytes.length > MAX_IMAGE_SIZE) {
				throw new InvalidImageException("Image limit exceeded: " + bytes.length + " (max: 5MB)");
			}
			if (!ALLOWED_EXTENSIONS.contains(extension)) {
				String allowed = Arrays.toString(ALLOWED_EXTENSIONS.toArray(String[]::new));
				String msg = String.format("Wrong image format: %s (allowed: %s)", extension, allowed);
				throw new InvalidImageException(msg);
			}
			return Image.builder()
					.withFileName(name)
					.withContent(bytes)
					.withExtension(extension)
					.withPlaceholder(placeholder)
					.build();
		} catch (IOException e) {
			throw new InvalidImageException(e);
		}
	}
}