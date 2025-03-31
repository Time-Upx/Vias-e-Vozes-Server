package TimeUpx.ViasEVozes.Backend.controllers;

import TimeUpx.ViasEVozes.Backend.dto.list.*;
import TimeUpx.ViasEVozes.Backend.dto.register.*;
import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.services.*;
import jakarta.transaction.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
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
	public ResponseEntity<ContributionRegisterDTO> register(@Valid @RequestBody ContributionRegisterDTO dto) {
		service.register(Contribution.of(dto, userService));
		return ResponseEntity.ok(dto);
	}

	@GetMapping
	@Transactional
	public List<ContributionListDTO> list() {
		return service.list();
	}
}
