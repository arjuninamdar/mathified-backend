package com.arjuninamdar.congressionalchallenge.security.jwt;

import com.arjuninamdar.congressionalchallenge.models.sql.UserInfo;
import com.arjuninamdar.congressionalchallenge.repositories.UserInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authManager; 

    @Autowired
    private JwtTokenUtil jwtTokenUtil; 

    @Autowired
    private JwtUserDetailsService userDetailsService; 

    @Autowired
    private UserInfoRepository userInfoRepository; 

    @Autowired
    PasswordEncoder passwordEncoder; 


    //can be used to verify authentication
    @PostMapping("/check")
    public ResponseEntity<?> checkAuthentication() {
        return ResponseEntity.ok("AUTHENTICATION_VALID"); 
    }

    //can be used to verify authentication for tutors
    @PostMapping("/checktutor")
    public ResponseEntity<?> checkAuthenticationTutor() {
        return ResponseEntity.ok("AUTHENTICATION_VALID"); 
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthModel authRequest) throws Exception {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())); 
            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername()); 

        

            String tok = jwtTokenUtil.generateToken(userDetails); 

            return ResponseEntity.ok(new JwtResponse(tok)); 

        } catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
            
        }
    }


    @PostMapping("/createuser")
    public ResponseEntity<?> createUser(@RequestBody JwtAuthModel authRequest) {
        UserInfo newUser = new UserInfo(authRequest.getUsername(), passwordEncoder.encode(authRequest.getPassword()), "ROLE_USER", true); 
        userInfoRepository.save(newUser); 

        return ResponseEntity.ok("USER_SAVED"); 

    }
    
    
}
