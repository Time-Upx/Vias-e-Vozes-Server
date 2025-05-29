package TimeUpx.ViasEVozes.Backend.controllers;

import TimeUpx.ViasEVozes.Backend.dtos.*;
import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.services.*;
import TimeUpx.ViasEVozes.Backend.values.*;
import jakarta.transaction.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.*;

@RestController
@RequestMapping ("/user")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*")
public class UserController {

	private final UserService service;
	private final ContributionService contributionService;

	@Transactional
	@PostMapping
	public ResponseEntity insert (
			@Valid @RequestBody UserSavingDTO dto,
			UriComponentsBuilder uriBuilder
	) {
		var user = service.insert(User.of(dto));
		var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.id()).toUri();
		return ResponseEntity.created(uri).body(user.details());
	}

	@Transactional
	@PostMapping ("/{userId}/contribution/{contributionId}")
	public ResponseEntity favoriteContribution (
			@PathVariable long userId,
			@PathVariable long contributionId
	) {
		return ResponseEntity.ok(service.favoriteContribution(userId, contributionId, contributionService).details());
	}

	@PostMapping (path = "/loggin")
	public ResponseEntity checkLoggin (
			@RequestBody Loggin loggin
	) {
		var user = service.checkLoggin(loggin.name(), loggin.email(), loggin.password());
		return ResponseEntity.accepted().body(LogginDTO.of(user));
	}

	@GetMapping
	public ResponseEntity getAll (
			@PageableDefault (
					size = 10,
					sort = {"dateOfArrival"}
			) Pageable page
	) {
		return ResponseEntity.ok(service.getAll(page).map(UserListingDTO::of));
	}

	@GetMapping ("/{id}")
	public ResponseEntity getDetails (@PathVariable long id) {
		return ResponseEntity.ok(service.getById(id).details());
	}

	@GetMapping ("/{id}/favorites")
	public ResponseEntity getFavorites (
		@PageableDefault (
			size = 5,
			sort = {"timeOfCreation"}
		) Pageable page,
		@PathVariable long id
	) {
		return ResponseEntity.ok(contributionService.getAllFavorites(page, id).map(ContributionListingDTO::of));
	}

	@Transactional
	@PutMapping ("/{id}")
	public ResponseEntity update (@PathVariable long id, @Valid @RequestBody UserUpdatingDTO dto) {
		return ResponseEntity.ok(service.update(id, dto).details());
	}

	@Transactional
	@DeleteMapping ("/{id}")
	public ResponseEntity desactivate (@PathVariable long id) {
		return ResponseEntity.ok(service.desactivate(id).details());
	}

	@Transactional
	@DeleteMapping ("/{userId}/contribution/{contributionId}")
	public ResponseEntity unfavoriteContribution (
			@PathVariable long userId,
			@PathVariable long contributionId
	) {
		return ResponseEntity.ok(service.unfavoriteContribution(userId, contributionId, contributionService).details());
	}

	@Transactional
	@DeleteMapping ("/{id}/remove")
	public ResponseEntity remove (@PathVariable long id) {
		return ResponseEntity.ok(service.remove(id).details());
	}

	@Transactional
	@PatchMapping ("/{id}/activate")
	public ResponseEntity activate (@PathVariable long id) {
		return ResponseEntity.ok(service.activate(id).details());
	}

	@Transactional
	@PatchMapping (path = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity uploadImage (
			@PathVariable long id,
			@RequestParam("file") MultipartFile file,
			@RequestParam("placeholder") String placeholder

	) {
		var image = Image.of(file, placeholder);
		return ResponseEntity.ok(service.getById(id).profilePicture(image).details());
	}
}
