package com.IndustrialDataAPI.controller;

import com.IndustrialDataAPI.dto.*;
import com.IndustrialDataAPI.service.UserService;
import org.apache.coyote.BadRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("industrial")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @GetMapping("/users")
    public ResponseEntity<List<UsersResponse>> getUsers(Authentication authentication){
        String email = authentication.getName();
        LOGGER.info("Retrieving all users by: {}", email);
        List<UsersResponse> users = userService.findAllUsers();
        LOGGER.info("Returning {} users by: {}", users.size(), email);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UsersResponse> getUserById(@PathVariable long id, Authentication authentication) throws BadRequestException {
        String email = authentication.getName();
        LOGGER.info("Retrieving user by id: {} by: {}", id, email);
        UsersResponse userResponse = userService.findUserById(id);
        LOGGER.info("Returning user: {} by: {}", userResponse.getEmail(), email);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/users/{email}/email")
    public ResponseEntity<UsersResponse> getUserByEmail(@PathVariable String email, Authentication authentication){
        String emailAuthentication = authentication.getName();
        LOGGER.info("Retrieving user by email: {} by: {}", email, emailAuthentication);
        UsersResponse userResponse = userService.findUserByEmail(email);
        LOGGER.info("Returning user: {} by: {}", userResponse.getEmail(), emailAuthentication);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/users/{id}/roles")
    public ResponseEntity<RolesResponse> getUserRolesById(@PathVariable Long id, Authentication authentication){
        String email = authentication.getName();
        LOGGER.info("Retrieving user role by id: {} by: {}", id, email);
        RolesResponse rolesResponse = userService.findUserRoleById(id);
        LOGGER.info("Returning rol: {} by: {}", rolesResponse.getRole(), email);
        return ResponseEntity.ok(rolesResponse);
    }

    @GetMapping("/users/{email}/email/roles")
    public ResponseEntity<RolesResponse> getUserRolesByEmail(@PathVariable String email, Authentication authentication){
        String emailAuthentication = authentication.getName();
        LOGGER.info("Retrieving user role by email: {} by: {}", email, emailAuthentication);
        RolesResponse rolesResponse = userService.findUserRoleByEmail(email);
        LOGGER.info("Returning rol: {} by: {}", rolesResponse.getRole(), emailAuthentication);
        return ResponseEntity.ok(rolesResponse);
    }

    @PostMapping("/users")
    public ResponseEntity<UsersResponse> createUser(@Validated @RequestBody UsersRequest request, Authentication authentication){
        String email = authentication.getName();
        LOGGER.info("Creating new user by: {}", email);
        UsersResponse usersResponse = userService.saveUser(request.getEmail(), request.getPassword(), request.getName(), request.getLastname(), request.getRole());
        LOGGER.info("User created by: {}", email);
        return ResponseEntity.ok(usersResponse);
    }

    @PostMapping("users/{id}/change-password")
    public ResponseEntity<UsersResponse> changePasswordUserById(@PathVariable Long id, @RequestBody String password, Authentication authentication){
        String email = authentication.getName();
        LOGGER.info("Changing password user by id: {} by: {}", id, email);
        UsersResponse usersResponse = userService.changePasswordUserById(password, id);
        LOGGER.info("Password changed of user: {} by: {}",usersResponse.getEmail(), email);
        return ResponseEntity.ok(usersResponse);
    }

    @PostMapping("users/{email}/email/change-password")
    public ResponseEntity<UsersResponse> changePasswordUserByEmail(@PathVariable String email, @RequestBody String password, Authentication authentication){
        String emailAuthentication = authentication.getName();
        LOGGER.info("Changing password user by email: {} by: {}", email, emailAuthentication);
        UsersResponse usersResponse = userService.changePasswordUserByEmail(password, email);
        LOGGER.info("Password changed of user: {} by: {}",usersResponse.getEmail(), emailAuthentication);
        return ResponseEntity.ok(usersResponse);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<UsersResponse> updateUserCompleteById(@RequestBody UsersRequest request, @PathVariable Long id, Authentication authentication){
        String email = authentication.getName();
        LOGGER.info("Changing user data by id: {} by: {}", id, email);
        UsersResponse usersResponse = userService.updateUserCompleteById(id, request.getEmail(), request.getPassword(), request.getName(), request.getLastname(), request.getRole());
        LOGGER.info("User: {} data changed by: {}", usersResponse.getEmail(), email);
        return ResponseEntity.ok(usersResponse);
    }

    @PutMapping("users/{email}/email")
    public ResponseEntity<UsersResponse> updateUserCompleteByEmail(@RequestBody UsersRequest request, @PathVariable String email, Authentication authentication){
        String emailAuthentication = authentication.getName();
        LOGGER.info("Changing user data by email: {} by: {}", email, emailAuthentication);
        UsersResponse usersResponse = userService.updateUserCompleteByEmail(email, request.getEmail(), request.getPassword(), request.getName(), request.getLastname(), request.getRole());
        LOGGER.info("User: {} data changed by: {}", usersResponse.getEmail(), emailAuthentication);
        return ResponseEntity.ok(usersResponse);
    }

    @PatchMapping("users/{id}")
    public ResponseEntity<UsersResponse> updateUserPartiallyById(@PathVariable Long id, @RequestBody UsersPatchRequest request, Authentication authentication){
        String email = authentication.getName();
        LOGGER.info("Changing user data by id: {} by: {}", id, email);
        UsersResponse usersResponse = userService.updateUserPartiallyById(id, request.getEmail(), request.getName(), request.getLastname(), request.getRole());
        LOGGER.info("User: {} data changed by: {}", usersResponse.getEmail(), email);
        return  ResponseEntity.ok(usersResponse);
    }

    @PatchMapping("users/{email}/email")
    public ResponseEntity<UsersResponse> updateUserPartiallyByEmail(@PathVariable String email, @RequestBody UsersPatchRequest request, Authentication authentication){
        String emailAuthentication = authentication.getName();
        LOGGER.info("Changing user data by email: {} by: {}", email, emailAuthentication);
        UsersResponse usersResponse = userService.updateUserPartiallyByEmail(email, request.getEmail(), request.getName(), request.getLastname(), request.getRole());
        LOGGER.info("User: {} data changed email: {}", usersResponse.getEmail(), emailAuthentication);
        return  ResponseEntity.ok(usersResponse);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id, Authentication authentication){
        String email = authentication.getName();
        LOGGER.info("Deleting user by id: {} by: {}", id, email);
        userService.deleteUserById(id);
        LOGGER.info("User deleted by: {}", email);
        return ResponseEntity.ok("User deleted");
    }

    // Authentication

    @PostMapping("auth/login")
    public ResponseEntity<String> loginUser (@Validated @RequestBody LoginRequest request){
        LOGGER.info("Login attempt by User: {}", request.getEmail());
        String jwtToken = userService.authenticateUser(request.getEmail(), request.getPassword());
        LOGGER.info("Login successful by User: {}", request.getEmail());
        return ResponseEntity.ok(jwtToken);
    }
}
