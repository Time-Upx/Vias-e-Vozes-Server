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

	@PostMapping
	@Transactional
	public ResponseEntity<ContributionRegisterDTO> register(
			@Valid @RequestBody
			ContributionRegisterDTO dto
	) {
		service.register(Contribution.of(dto, userService));
		return ResponseEntity.ok(dto);
	}

	@GetMapping
	public Page<ContributionListDTO> list(
			@PageableDefault(size = 10, sort = {"timeOfCreation"})
			Pageable page
	) {
		return service.list(page);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<ContributionListDTO> update (
			@Valid @RequestBody
			ContributionUpdateDTO dto
	) {
		var contribution = service.update(dto);
		var listDTO = ContributionListDTO.of(contribution);
		return ResponseEntity.ok(listDTO);
	}
}
