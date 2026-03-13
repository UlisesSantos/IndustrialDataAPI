package com.IndustrialDataAPI.service;

import com.IndustrialDataAPI.model.Users;
import com.IndustrialDataAPI.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private JWTService jwtService;

    @Mock
    private UserRepository userRepository;

    @Mock
    AuthenticationManager authenticationManager;

    @InjectMocks
    private UserService userService;

    /*
    @Test
    void shouldReturnAllUsersWhenTokenIsValid() {
        List<Users> users = List.of(new Users(), new Users());

        when(userRepository.findAll()).thenReturn(users);

        List<Users> result = userService.findAllUsers();

        assertEquals(2, result.size());

        verify(userRepository)
                .findAll();

    }*/

    @Test
    void shouldReturnJwtTokenWhenUserIsValid(){
        Users user = new Users();
        user.setEmail("email");
        user.setPassword("password");

        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);

        when(jwtService.generateToken("email")).thenReturn("token");

        String result = userService.authenticateUser(user.getEmail(), user.getPassword());
        assertEquals("token", result);

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService).generateToken("email");
    }
}

