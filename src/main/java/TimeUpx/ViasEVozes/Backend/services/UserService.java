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

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository repository;

	public Page<User> getAll(Pageable page) {
		return repository.findAllByIsActiveTrue(page);
	}
	public User getById(long id) {
		return repository.findByIdAndIsActive(id, true);
	}
	public Page<User> getFavoritedBy(Pageable page, long contributionId) {
		return repository.findAllActiveFavoritedBy(page, contributionId);
	}

	public User insert(@Valid User user) {
		if (repository.exists(Example.of(user)))
			throw new EntityExistsException("User already exists");
		return repository.save(user);
	}

	public User update(long id, @Valid UserUpdatingDTO dto) {
		return repository.findByIdAndIsActive(id, true).update(dto);
	}

	public User delete(long id) {
		return repository.findByIdAndIsActive(id, true).isActive(false);
	}
	public User activate(long id) {
		return repository.findByIdAndIsActive(id, false).isActive(true);
	}

	public User favoriteContribution(long userId, long contributionId, ContributionService service) {
		var contribution = service.getById(contributionId);
		var user = repository.findByIdAndIsActive(userId, true);
		user.favorites().add(contribution);
		return user;
	}

	public User unfavoriteContribution(long userId, long contributionId, ContributionService service) {
		var contribution = service.getById(contributionId);
		var user = repository.findByIdAndIsActive(userId, true);
		user.favorites().remove(contribution);
		return user;
	}
}
