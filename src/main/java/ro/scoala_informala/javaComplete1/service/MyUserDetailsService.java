package ro.scoala_informala.javaComplete1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ro.scoala_informala.javaComplete1.exception.BusinessException;
import ro.scoala_informala.javaComplete1.model.User;
import ro.scoala_informala.javaComplete1.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("User not found by username: " + username));

        return new MyUserDetails(foundUser);
    }
}
