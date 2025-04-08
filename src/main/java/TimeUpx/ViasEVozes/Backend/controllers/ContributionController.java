package TimeUpx.ViasEVozes.Backend.controllers;

import TimeUpx.ViasEVozes.Backend.dto.list.*;
import TimeUpx.ViasEVozes.Backend.dto.register.*;
import TimeUpx.ViasEVozes.Backend.dto.update.*;
import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.services.*;
import jakarta.transaction.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/contribution")
public class ContributionController
{
	@Autowired
	private ContributionService service;

	@Autowired
	private UserService userService;

	@Transactional
	@PostMapping
	public void register(@Valid @RequestBody ContributionRegisterDTO dto) {
		service.register(Contribution.of(dto, userService));
	}

	@GetMapping
	public Page<ContributionListDTO> list(
			@PageableDefault(size = 10, sort = {"timeOfCreation"})
			Pageable page
	) {
		return service.list(page);
	}

	@Transactional
	@PutMapping ("/{id}")
	public void update (@PathVariable long id, @Valid @RequestBody ContributionUpdateDTO dto) {
		service.update(id, dto);
	}

	@Transactional
	@DeleteMapping ("/{id}")
	public void delete (@PathVariable long id) {
		service.delete(id);
	}
}
