package TimeUpx.ViasEVozes.Backend.services;

import TimeUpx.ViasEVozes.Backend.dtos.*;
import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.repositories.*;
import jakarta.persistence.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
public class CommentService {

	@Autowired
	private CommentRepository repository;

	public Comment insert (@Valid Comment comment) {
		if (repository.exists(Example.of(comment)))
			throw new EntityExistsException("Comment already exists");
		return repository.save(comment);
	}

	public Page<Comment> getAll (Pageable page) {
		return repository.findAllByIsActiveTrue(page);
	}

	public Comment getById (long id) {
		return repository.findByIdAndIsActive(id, true)
				.orElseThrow(() -> new EntityNotFoundException("Comment not found"));
	}

	public Page<Comment> getAllByContribution (Pageable page, long contributionId) {
		return repository.findAllByIsActiveAndContributionId(page, contributionId);
	}

	public Comment updateBody (long id, @Valid CommentUpdatingDTO dto) {
		return repository.findByIdAndIsActive(id, true)
				.orElseThrow(() -> new EntityNotFoundException("Comment not found"))
				.body(dto.body());
	}

	public Comment deactivate (long id) {
		return repository.findByIdAndIsActive(id, true)
				.orElseThrow(() -> new EntityNotFoundException("Comment not found"))
				.isActive(false);
	}

	public Comment activate (long id) {
		return repository.findByIdAndIsActive(id, false)
				.orElseThrow(() -> new EntityNotFoundException("Comment not found"))
				.isActive(true);
	}

	public Comment adjustLikes (long id, int value) {
		var comment = repository.findByIdAndIsActive(id, true)
				.orElseThrow(() -> new EntityNotFoundException("Comment not found"));
		return comment.adjustLikes(value);
	}

	public Comment remove(long id) {
		var comment = repository.findByIdAndIsActive(id, true)
				.orElseThrow(() -> new EntityNotFoundException("Comment not found"));
		repository.deleteById(id);
		return comment;
	}

}
