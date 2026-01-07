package com.IndustrialDataAPI.service;

import com.IndustrialDataAPI.common.LogConfig;
import com.IndustrialDataAPI.model.UserPrincipal;
import com.IndustrialDataAPI.model.Users;
import com.IndustrialDataAPI.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger LOGGER = LogManager.getLogger(MyUserDetailsService.class);

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
       LogConfig.initLog4j2();

       Optional<Users> usersOptional = userRepository.findUserByEmail(email);
       if(usersOptional.isPresent()) {
           LOGGER.info("Login user from database");
           Users users = usersOptional.get();
           return new UserPrincipal(users);
       }else {
           throw new UsernameNotFoundException("User not found");
       }
   }
}
