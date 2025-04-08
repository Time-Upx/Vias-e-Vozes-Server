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

	@Transactional
	@PostMapping
	public void save(@Valid @RequestBody UserRegisterDTO dto) {
		service.register(User.of(dto));
	}

	@GetMapping
	public Page<UserListDTO> list(Pageable page) {
		return service.list(page);
	}

	@Transactional
	@DeleteMapping ("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
