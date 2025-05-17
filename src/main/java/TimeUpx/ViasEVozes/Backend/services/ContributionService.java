package TimeUpx.ViasEVozes.Backend.services;

import TimeUpx.ViasEVozes.Backend.dtos.*;
import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.repositories.*;
import jakarta.persistence.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@RequiredArgsConstructor
@Service
public class ContributionService {

	private final ContributionRepository repository;

	public Contribution insert (@Valid Contribution contribution) {
		if (repository.exists(Example.of(contribution)))
			throw new EntityExistsException("Contribution already exists");
		return repository.save(contribution);
	}

	public Page<Contribution> getAll (Pageable page) {
		return repository.findAllByIsActiveTrue(page);
	}

	public Page<Contribution> getAllFavorites (Pageable page, long id) {
		return repository.findAllActiveFavorites(page, id);
	}

	public Contribution update (long id, @Valid ContributionUpdatingDTO dto) {
		return repository.findByIdActive(id).update(dto);
	}

	public Contribution remove (long id) {
		return repository.findByIdActive(id).isActive(false);
	}

	public Contribution activate (long id) {
		return repository.findByIdInactive(id).isActive(true);
	}

	public Contribution getById (long id) {
		return repository.findByIdActive(id);
	}

	public Contribution adjustLikes (long id, int value) {
		var contribution = repository.findByIdActive(id);
		return contribution.adjustLikes(value);
	}
}
