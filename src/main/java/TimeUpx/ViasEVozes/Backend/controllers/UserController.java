package TimeUpx.ViasEVozes.Backend.controllers;

import TimeUpx.ViasEVozes.Backend.dto.*;
import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.services.*;
import TimeUpx.ViasEVozes.Backend.values.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController
{
	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<UserRegisterDTO> register(@Valid @RequestBody UserRegisterDTO dto) {
		service.register(User.toEntity(dto));
		return ResponseEntity.ok(dto);
	}
}
