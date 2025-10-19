package com.attendance.auth.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.attendance.auth.dto.LoginRequest;
import com.attendance.auth.dto.RegisterRequest;
import com.attendance.auth.entity.Role;
import com.attendance.auth.entity.User;
import com.attendance.auth.repository.RoleRepository;
import com.attendance.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthService {
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ApplicationEventPublisher eventPublisher;
    
    
    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,
			JwtService jwtService, ApplicationEventPublisher eventPublisher) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.eventPublisher = eventPublisher;
	}


	// Registration
    public String register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("Email already in use");
        }

        Role role = roleRepository.findByName("USER")
        		.orElseThrow(()-> new IllegalStateException("Role not found"));
        
        User user = User.builder()
                        .username(request.getUsername())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(role)
                        .build();

        userRepository.save(user);

        // Publish post-registration event
        eventPublisher.publishEvent(new UserRegisteredEvent(this, user));

        return jwtService.generateToken(user);
    }
    
    
 // Login
    public String login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return jwtService.generateToken(user);
    }

}
