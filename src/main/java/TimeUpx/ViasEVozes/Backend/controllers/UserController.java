package TimeUpx.ViasEVozes.Backend.controllers;

import TimeUpx.ViasEVozes.Backend.dto.*;
import TimeUpx.ViasEVozes.Backend.services.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController
{
	@Autowired
	private UserService service;

	@PostMapping
	@Transactional
	public ResponseEntity<String> register(@Valid @RequestBody UserRegisterDTO dto) {
		this.service.register(dto);
		return ResponseEntity.ok("User registered successfully");
	}

	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
