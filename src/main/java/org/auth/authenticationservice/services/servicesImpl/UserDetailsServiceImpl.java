package org.auth.authenticationservice.services.servicesImpl;


import lombok.AllArgsConstructor;
import org.auth.authenticationservice.configs.UserPrincipal;
import org.auth.authenticationservice.exceptions.BadCredentialsException;
import org.auth.authenticationservice.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.auth.authenticationservice.constants.MessageKey.ERROR_BAD_CREDENTIAL;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameOrEmail(username, username)
            .map(UserPrincipal::build)
            .orElseThrow(() -> new BadCredentialsException(ERROR_BAD_CREDENTIAL));
    }
}
