package com.bridgelabz.service;

import com.bridgelabz.model.User;
import com.bridgelabz.model.UserPrincipal;
import com.bridgelabz.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if(user == null) {
            System.out.println("User");
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }
}
