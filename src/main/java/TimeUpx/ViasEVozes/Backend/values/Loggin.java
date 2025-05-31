package TimeUpx.ViasEVozes.Backend.values;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Loggin(
      @Email
      String email,
      String name,
      @NotBlank
      String password
) {}
