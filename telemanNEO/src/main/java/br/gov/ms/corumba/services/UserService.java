package br.gov.ms.corumba.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.ms.corumba.entities.User;
import br.gov.ms.corumba.repositories.UserRepository;
import br.gov.ms.corumba.resources.dto.user.UserDTO;
import br.gov.ms.corumba.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AuthService authService;
	
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		
		authService.validateSelfOrAdmin(id);
		
		User entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Entity not found, id: "+id));
		return new UserDTO(entity);
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if (user == null) {
			logger.error("User not found: "+ username);
			throw new UsernameNotFoundException("Email not found");
		}
		logger.info("User found: " + username);
		return user;
	}

}
