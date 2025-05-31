package TimeUpx.ViasEVozes.Backend.controllers;

import TimeUpx.ViasEVozes.Backend.dtos.*;
import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.services.*;
import jakarta.transaction.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.*;

@RestController
@RequestMapping ("comment")
@RequiredArgsConstructor
@CrossOrigin (allowedHeaders = "*")
public class CommentController {

	private final CommentService service;
	private final UserService userService;
	private final ContributionService contributionService;

	@Transactional
	@PostMapping
	public ResponseEntity save (
			@Valid @RequestBody CommentSavingDTO dto,
			UriComponentsBuilder uriBuilder
	) {
		var author = userService.getById(dto.authorId());
		var contribution = contributionService.getById(dto.contributionId());
		var comment = service.insert(Comment.of(dto, author, contribution));
		var uri = uriBuilder.path("/comment/{id}").buildAndExpand(comment.id()).toUri();
		return ResponseEntity.created(uri).body(comment.listing());
	}

	@Transactional
	@PostMapping ("/{id}/like")
	public ResponseEntity increaseLike (@PathVariable long id) {
		return ResponseEntity.ok(service.adjustLikes(id, 1).listing());
	}

	@GetMapping
	public ResponseEntity getAll (
			@PageableDefault (
					size = 10,
					sort = {"timeOfComment"}
			) Pageable page
	) {
		return ResponseEntity.ok(service.getAll(page).map(CommentListingDTO::of));
	}

	@GetMapping ("/{id}")
	public ResponseEntity getOne (@PathVariable long id) {
		return ResponseEntity.ok(service.getById(id).listing());
	}

	@Transactional
	@PatchMapping ("/{id}/edit")
	public ResponseEntity updateBody (
			@PathVariable long id,
			@Valid @RequestBody CommentUpdatingDTO dto
	) {
		return ResponseEntity.ok(service.updateBody(id, dto).listing());
	}

	@Transactional
	@DeleteMapping ("/{id}")
	public ResponseEntity deactivate (@PathVariable long id) {
		return ResponseEntity.ok(service.deactivate(id).listing());
	}

	@Transactional
	@DeleteMapping ("/{id}/like")
	public ResponseEntity decreaseLike (@PathVariable long id) {
		return ResponseEntity.ok(service.adjustLikes(id, -1).listing());
	}

	@Transactional
	@DeleteMapping ("/{id}/remove")
	public ResponseEntity remove (@PathVariable long id) {
		return ResponseEntity.ok(service.remove(id).listing());
	}

	@Transactional
	@PatchMapping ("/{id}/activate")
	public ResponseEntity activate (@PathVariable long id) {
		return ResponseEntity.ok(service.activate(id).listing());
	}
}
