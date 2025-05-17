package TimeUpx.ViasEVozes.Backend.repositories;

import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.infra.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Page<User> findAllByIsActiveTrue(Pageable page);

	@Query("""
        SELECT u FROM User u
        JOIN u.favorites c
        WHERE u.isActive = true
        AND c.id = :contributionId
        """)
	Page<User> findAllActiveFavoritedBy(Pageable pageable, @Param ("contributionId") long contributionId);

	default User findByIdAndIsActiveTrue(long id) {
		var user = this.getReferenceById(id);
		if (!user.isActive()) {
			throw new EntityActivityException(User.class, false);
		}
		return user;
	}
	default User findByIdAndIsActiveFalse(long id) {
		var user = this.getReferenceById(id);
		if (user.isActive()) {
			throw new EntityActivityException(User.class, true);
		}
		return user;
	}
}
