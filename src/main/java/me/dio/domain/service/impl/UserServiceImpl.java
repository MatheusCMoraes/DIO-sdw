package me.dio.domain.service.impl;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.domain.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository; //Um campo final é aquele que não pode ter seu conteúdo alterado depois de inicializado
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User findByid(Long id) {
		return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
		//return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
		/*Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
		    return optionalUser.get();
		} else {
		    throw new NoSuchElementException("No user found with id: " + id);
		}
		 * */
	}

	@Override
	public User create(User userToCreate) {
		if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
			throw new IllegalArgumentException("This Account number already exists");
		}
		
		return userRepository.save(userToCreate);
	}

}
