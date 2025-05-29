package TimeUpx.ViasEVozes.Backend.dtos;

import TimeUpx.ViasEVozes.Backend.entities.User;
import lombok.extern.java.Log;

import java.util.Optional;

public record LogginDTO(
    UserDetailingDTO user,
    boolean result
) {
    public static LogginDTO of(Optional<User> user) {
        boolean result = user.isPresent();
        var details = user.map(User::details).orElse(null);
        return new LogginDTO(details, result);
    }
}
