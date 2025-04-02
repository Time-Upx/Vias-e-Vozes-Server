package TimeUpx.ViasEVozes.Backend.controllers;

import TimeUpx.ViasEVozes.Backend.dto.list.*;
import TimeUpx.ViasEVozes.Backend.dto.register.*;
import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.services.*;
import jakarta.transaction.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController
{
	@Autowired
	private UserService service;

	@PostMapping
	@Transactional
	public ResponseEntity<UserRegisterDTO> register(@Valid @RequestBody UserRegisterDTO dto) {
		service.register(User.of(dto));
		return ResponseEntity.ok(dto);
	}

	@GetMapping
	@Transactional
	public Page<UserListDTO> list(Pageable page) {
		return service.list(page);
	}
}
