package TimeUpx.ViasEVozes.Backend.services;

import TimeUpx.ViasEVozes.Backend.dtos.*;
import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.repositories.*;
import jakarta.persistence.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository repository;

	public Page<User> getAll(Pageable page) {
		return repository.findAllByIsActiveTrue(page);
	}
	public User getById(long id) {
		return repository.findByIdAndIsActive(id, true)
				.orElseThrow(() -> new EntityNotFoundException("User not found"));
	}
	public Page<User> getFavoritedBy(Pageable page, long contributionId) {
		return repository.findAllActiveFavoritedBy(page, contributionId);
	}

	public User insert(@Valid User user) {
		if (repository.findByNameOrEmailAndPassword(user.name(), user.email(), user.password()).isEmpty())
			throw new EntityExistsException("User already exists");
		return repository.save(user);
	}

	public User update(long id, @Valid UserUpdatingDTO dto) {
		return repository.findByIdAndIsActive(id, true)
				.orElseThrow(() -> new EntityNotFoundException("User not found"))
				.update(dto);
	}

	public User desactivate(long id) {
		return repository.findByIdAndIsActive(id, true)
				.orElseThrow(() -> new EntityNotFoundException("User not found"))
				.isActive(false);
	}
	public User activate(long id) {
		return repository.findByIdAndIsActive(id, false)
				.orElseThrow(() -> new EntityNotFoundException("User not found"))
				.isActive(true);
	}

	public User favoriteContribution(long userId, long contributionId, ContributionService service) {
		var contribution = service.getById(contributionId);
		var user = repository.findByIdAndIsActive(userId, true)
				.orElseThrow(() -> new EntityNotFoundException("User not found"));
		user.favorites().add(contribution);
		return user;
	}

	public User unfavoriteContribution(long userId, long contributionId, ContributionService service) {
		var contribution = service.getById(contributionId);
		var user = repository.findByIdAndIsActive(userId, true)
				.orElseThrow(() -> new EntityNotFoundException("User not found"));
		user.favorites().remove(contribution);
		return user;
	}

	public Optional<User> checkLoggin(String name, String email, String password) {
		return repository.findByNameOrEmailAndPassword(name, email, password);
	}

	public User remove(long id) {
		var user = repository.findByIdAndIsActive(id, true)
				.orElseThrow(() -> new EntityNotFoundException("User not found"));
		repository.deleteById(id);
		return user;
	}
}
