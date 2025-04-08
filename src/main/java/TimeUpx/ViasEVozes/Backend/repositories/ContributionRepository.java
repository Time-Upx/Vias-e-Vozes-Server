package TimeUpx.ViasEVozes.Backend.repositories;

import TimeUpx.ViasEVozes.Backend.entities.*;
import aj.org.objectweb.asm.commons.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

public interface ContributionRepository extends JpaRepository<Contribution, Long> {
	Page<Contribution> findAllByIsActiveTrue(Pageable page);
}
