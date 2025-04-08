package TimeUpx.ViasEVozes.Backend.services;

import TimeUpx.ViasEVozes.Backend.dto.list.*;
import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.exceptions.*;
import TimeUpx.ViasEVozes.Backend.repositories.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public void register(@Valid User user) {
		repository.save(user);
	}

	public Page<UserListDTO> list(Pageable page) {
		return repository.findAllByIsActiveTrue(page).map(UserListDTO::of);
	}

	public void delete(long id) {
		User user = retrieveFromId(id);
		user.setInactive();
	}

	public User retrieveFromId(long id) {
		Optional<User> user = repository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new NotFoundException("User with id:" + id + " not found");
		}
	}
}
