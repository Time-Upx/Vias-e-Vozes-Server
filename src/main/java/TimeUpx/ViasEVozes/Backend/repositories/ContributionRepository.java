package TimeUpx.ViasEVozes.Backend.repositories;

import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.infra.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.Optional;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long> {
	Page<Contribution> findAllByIsActiveTrue(Pageable page);

	@Query("""
        SELECT c FROM Contribution c
        JOIN c.favoritedBy u
        WHERE c.isActive = true
        AND u.id = :userId
        """)
	Page<Contribution> findAllActiveFavorites(Pageable pageable, @Param("userId") long userId);

	Optional<Contribution> findByIdAndIsActive (Long id, boolean isActive);
}
