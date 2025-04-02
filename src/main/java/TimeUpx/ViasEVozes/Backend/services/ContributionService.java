package TimeUpx.ViasEVozes.Backend.services;

import TimeUpx.ViasEVozes.Backend.dto.list.*;
import TimeUpx.ViasEVozes.Backend.dto.update.*;
import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.exceptions.*;
import TimeUpx.ViasEVozes.Backend.repositories.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ContributionService {

	@Autowired
	private ContributionRepository repository;

	public void register(Contribution contribution) {
		repository.save(contribution);
	}

	public Page<ContributionListDTO> list(Pageable page) {
		return repository.findAll(page).map(ContributionListDTO::of);
	}

	public Contribution update(ContributionUpdateDTO dto) {
		Contribution contribution = retrieveFromId(dto.id());
		return contribution.update(dto);
	}

	public Contribution retrieveFromId(Long id) {
		Optional<Contribution> contribution = repository.findById(id);
		if (contribution.isPresent()) {
			return contribution.get();
		} else {
			throw new NotFoundException("Contribution with id:" + id + " not found");
		}
	}
}
