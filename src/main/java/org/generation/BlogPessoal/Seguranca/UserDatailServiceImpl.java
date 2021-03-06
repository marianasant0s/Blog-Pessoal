package org.generation.BlogPessoal.Seguranca;

import java.util.Optional;

import org.generation.BlogPessoal.Repository.UsuarioRepository;
import org.generation.BlogPessoal.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDatailServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName){
		Optional<Usuario> user = userRepository.findByUsuario(userName);
		user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));
		return user.map(UserDetailsImplementacao :: new).get();
		
	}
	
	
	
	
	
}
