package TimeUpx.ViasEVozes.Backend.services;

import TimeUpx.ViasEVozes.Backend.dto.list.*;
import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.repositories.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ContributionService {

	@Autowired
	private ContributionRepository repository;

	public void register(@Valid Contribution contribution) {
		repository.save(contribution);
	}

	public List<ContributionListDTO> list() {
		return repository.findAll().stream().map(ContributionListDTO::of).toList();
	}
}
