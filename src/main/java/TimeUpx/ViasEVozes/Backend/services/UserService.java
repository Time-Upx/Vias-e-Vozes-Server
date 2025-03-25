package TimeUpx.ViasEVozes.Backend.services;

import TimeUpx.ViasEVozes.Backend.dto.*;
import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.exceptions.*;
import TimeUpx.ViasEVozes.Backend.repositories.*;
import TimeUpx.ViasEVozes.Backend.values.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public void register(@Valid User user) {
		repository.save(user);
	}
	public User retrieveFromId(Long id) {
		Optional<User> user = repository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new NotFoundException("User with id:" + id + " not found");
		}
	}
}
