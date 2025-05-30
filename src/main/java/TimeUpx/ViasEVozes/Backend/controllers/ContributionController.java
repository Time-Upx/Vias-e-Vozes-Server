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
import org.springframework.web.multipart.*;
import org.springframework.web.util.*;

import java.io.*;

@RestController
@RequestMapping ("/contribution")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*")
public class ContributionController {

	private final ContributionService service;
	private final UserService userService;
	private final CommentService commentService;

	@Transactional
	@PostMapping
	public ResponseEntity save (
			@Valid @RequestBody ContributionSavingDTO dto,
			UriComponentsBuilder uriBuilder
	) {
		var author = userService.getById(dto.authorId());
		var contribution = service.insert(Contribution.of(dto, author));
		var uri = uriBuilder.path("/contribution/{id}").buildAndExpand(contribution.id()).toUri();
		return ResponseEntity.created(uri).body(contribution.details());
	}

	@Transactional
	@PostMapping ("/{id}/like")
	public ResponseEntity increaseLike (@PathVariable long id) {
		return ResponseEntity.ok(service.adjustLikes(id, 1).details());
	}

	@GetMapping
	@CrossOrigin("*")
	public ResponseEntity getAll (
			@PageableDefault (
					size = 10,
					sort = {"timeOfCreation"}
			) Pageable page
	) {
		return ResponseEntity.ok(service.getAll(page).map(ContributionListingDTO::of));
	}

	@GetMapping ("/{id}")
	public ResponseEntity getDetails (@PathVariable long id) {
		return ResponseEntity.ok(service.getById(id).details());
	}

	@GetMapping ("/{id}/favorites")
	public ResponseEntity getFavoritedBy (
			@PageableDefault (
					size = 5,
					sort = {"timeOfArrival"}
			) Pageable page,
			@PathVariable long id
	) {
		return ResponseEntity.ok(userService.getFavoritedBy(page, id).map(UserListingDTO::of));
	}

	@GetMapping ("/{id}/comments")
	public ResponseEntity getComments (
			@PageableDefault (
					size = 5,
					sort = {"timeOfComment"}
			) Pageable page,
			@PathVariable long id
	) {
		return ResponseEntity.ok(commentService.getAllByContribution(page, id).map(CommentListingDTO::of));
	}

	@Transactional
	@PutMapping ("/{id}")
	public ResponseEntity update (
			@PathVariable long id,
			@Valid @RequestBody ContributionUpdatingDTO dto
	) {
		return ResponseEntity.ok(service.update(id, dto).details());
	}

	@Transactional
	@DeleteMapping ("/{id}")
	public ResponseEntity desactivate (@PathVariable long id) {
		return ResponseEntity.ok(service.deactivate(id).details());
	}

	@Transactional
	@DeleteMapping ("/{id}/like")
	public ResponseEntity decreaseLike (@PathVariable long id) {
		return ResponseEntity.ok(service.adjustLikes(id, -1).details());
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
			@RequestBody MultipartFile file,
			@RequestParam ("placeholder") String placeholder
	) throws IOException {
		return ResponseEntity.ok(service.getById(id).image(Image.of(file, placeholder)).details());
	}
}
