package com.IndustrialDataAPI.service;

import com.IndustrialDataAPI.dto.RolesResponse;
import com.IndustrialDataAPI.dto.UsersResponse;
import com.IndustrialDataAPI.exception.NullOrEmptyParametersException;
import com.IndustrialDataAPI.exception.RoleNotFoundException;
import com.IndustrialDataAPI.exception.UserNotFoundException;
import com.IndustrialDataAPI.model.Roles;
import com.IndustrialDataAPI.model.Users;
import com.IndustrialDataAPI.repository.UserRepository;
import com.IndustrialDataAPI.security.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    public List<UsersResponse> findAllUsers(){
        return userRepository.findAll().stream()
                .map(users -> new UsersResponse(users.getEmail(),users.getName(), users.getLastname(), users.getRoles().getRole(), users.getCreatedAt()))
                .toList();
    }

    public UsersResponse findUserById(Long id){
        Optional<Users> userOptional = userRepository.findById(id);
        Users user = userOptional.orElseThrow(UserNotFoundException::new);
        return new UsersResponse(user.getEmail(), user.getName(), user.getLastname(), user.getRoles().getRole(), user.getCreatedAt());
    }

    public UsersResponse findUserByEmail(String email){
        Optional<Users> usersOptional = userRepository.findUserByEmail(email);
        Users user = usersOptional.orElseThrow(UserNotFoundException::new);
        return new UsersResponse(user.getEmail(), user.getName(), user.getLastname(), user.getRoles().getRole(), user.getCreatedAt());
    }

    public RolesResponse findUserRoleById(Long id){
        Optional<Users> usersOptional = userRepository.findById(id);
        Users user = usersOptional.orElseThrow(UserNotFoundException::new);
        return new RolesResponse(user.getRoles().getRole());
    }

    public RolesResponse findUserRoleByEmail(String email){
        Optional<Users> usersOptional = userRepository.findUserByEmail(email);
        Users user = usersOptional.orElseThrow(UserNotFoundException::new);
        return new RolesResponse(user.getRoles().getRole());
    }

    public UsersResponse saveUser(String email, String password, String name, String lastname, String role){
        Roles roles = roleService.findRolesByRole(role);
        Users user = new Users(
                email,
                password,
                name,
                lastname,
                LocalDateTime.now(),
                roles
        );
        userRepository.save(user);
        return new UsersResponse(user.getEmail(), user.getName(), user.getLastname(), user.getRoles().getRole(),user.getCreatedAt());
    }

    public UsersResponse changePasswordUserById(String password, Long id){
        if(password == null || password.isBlank()){
            throw new NullOrEmptyParametersException();
        }

        Optional<Users> usersOptional = userRepository.findById(id);
        Users user = usersOptional.orElseThrow(UserNotFoundException::new);
        user.setPassword(myPasswordEncoder.encode(password));
        userRepository.save(user);
        return new UsersResponse(user.getEmail(), user.getName(), user.getLastname(), user.getRoles().getRole(),user.getCreatedAt());
    }

    public UsersResponse changePasswordUserByEmail(String password, String email){
        if(password == null || password.isBlank()){
            throw new NullOrEmptyParametersException();
        }

        Optional<Users> usersOptional = userRepository.findUserByEmail(email);
        Users user = usersOptional.orElseThrow(UserNotFoundException::new);
        user.setPassword(myPasswordEncoder.encode(password));
        userRepository.save(user);
        return new UsersResponse(user.getEmail(), user.getName(), user.getLastname(), user.getRoles().getRole(),user.getCreatedAt());
    }

    public UsersResponse updateUserCompleteById(Long id, String email, String password, String name, String lastname, String role){
        Roles roles = roleService.findRolesByRole(role);

        if(!userRepository.existsById(id)) throw new UserNotFoundException();

        Users user = new Users(
                id,
                email,
                myPasswordEncoder.encode(password),
                name,
                lastname,
                roles,
                LocalDateTime.now()
        );

        userRepository.save(user);
        return new UsersResponse(user.getEmail(), user.getName(), user.getLastname(), user.getRoles().getRole(), user.getCreatedAt());
    }

    public UsersResponse updateUserCompleteByEmail(String emailRequest, String email, String password, String name, String lastname, String role ){
        Roles roles = roleService.findRolesByRole(role);
        Optional<Users> usersOptional = userRepository.findUserByEmail(emailRequest);
        Users user = usersOptional.orElseThrow(UserNotFoundException::new);

        user.setEmail(email);
        user.setName(name);
        user.setPassword(myPasswordEncoder.encode(password));
        user.setLastname(lastname);
        user.setRoles(roles);

        userRepository.save(user);
        return new UsersResponse(user.getEmail(), user.getName(), user.getLastname(), user.getRoles().getRole(), user.getCreatedAt());
    }

    public UsersResponse updateUserPartiallyById(Long id, String email, String name, String lastname, String role){
        Users user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if(role != null) {
            Roles roles = roleService.findRolesByRole(role);
            user.setRoles(roles);
        }
        if(email != null) user.setEmail(email);

        if(name != null) user.setName(name);

        if(lastname != null) user.setLastname(lastname);

        userRepository.save(user);

        return new UsersResponse(user.getEmail(), user.getName(), user.getLastname(), user.getRoles().getRole(), user.getCreatedAt());
    }

    public UsersResponse updateUserPartiallyByEmail(String emailRequest, String email, String name, String lastname, String role){
        Users user = userRepository.findUserByEmail(emailRequest).orElseThrow(UserNotFoundException::new);
        if(role != null) {
            Roles roles = roleService.findRolesByRole(role);
            user.setRoles(roles);
        }
        if(email != null) user.setEmail(email);

        if(name != null) user.setName(name);

        if(lastname != null) user.setLastname(lastname);

        userRepository.save(user);

        return new UsersResponse(user.getEmail(), user.getName(), user.getLastname(), user.getRoles().getRole(), user.getCreatedAt());
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) throw new UserNotFoundException();
        userRepository.deleteById(id);
    }

    //Authentication

    public String authenticateUser(String email, String password){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return jwtService.generateToken(userDetails.getUsername());
    }


}
