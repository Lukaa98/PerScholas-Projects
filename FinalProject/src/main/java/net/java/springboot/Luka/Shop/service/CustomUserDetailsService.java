package net.java.springboot.Luka.Shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.java.springboot.Luka.Shop.Global.CustomUserDetails;
import net.java.springboot.Luka.Shop.model.User;
import net.java.springboot.Luka.Shop.repository.UserRepository;
 
public class CustomUserDetailsService implements UserDetailsService {
 // to implement method LoadUserByUserName
    @Autowired
    private UserRepository userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
 
}