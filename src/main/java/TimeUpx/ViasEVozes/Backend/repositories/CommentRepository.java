package TimeUpx.ViasEVozes.Backend.repositories;

import TimeUpx.ViasEVozes.Backend.entities.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	Page<Comment> findAllByIsActiveTrue (Pageable page);

	@Query("""
        SELECT c FROM Comment c
        WHERE c.isActive = true AND c.contribution.id = :contributionId
        """)
	Page<Comment> findAllByIsActiveAndContributionId (Pageable page, @Param("contributionId") long contributionId);

	@Query("""
        SELECT u FROM User u
        JOIN u.favorites c
        WHERE u.isActive = true
        AND c.id = :contributionId
        """)
	Page<User> findAllActiveFavoritedBy(Pageable pageable, @Param ("contributionId") long contributionId);

	Optional<Comment> findByIdAndIsActive (Long id, boolean isActive);
}
