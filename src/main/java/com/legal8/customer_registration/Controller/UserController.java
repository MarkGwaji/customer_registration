package com.legal8.customer_registration.Controller;

import com.legal8.customer_registration.Entity.User;
import com.legal8.customer_registration.Exception.APIException;
import com.legal8.customer_registration.Exception.GlobalExceptionHandler;
import com.legal8.customer_registration.Exception.ResourceNotFoundException;
import com.legal8.customer_registration.Repository.UserRepository;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@Validated
@RequestMapping("/customer")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // get all users or search specific user
    @GetMapping(value = "/customerinfo")
    public ResponseEntity<List<User>> searchForUser(@SearchSpec Specification<User> specs){
        return new ResponseEntity<>(userRepository.findAll(Specification.where(specs)), HttpStatus.OK);
    }

    // create user
    @PostMapping("register")
    public User createUser(@Valid @RequestBody User user) {
        return this.userRepository.save(user);
    }

    // update user
    @PutMapping("/update/{id}")
    public User updateUser(@Valid @RequestBody User user,  @PathVariable ("id") long userId) {
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));

        existingUser.setFirstName(user.getFirstName());

        existingUser.setLastName(user.getLastName());

        existingUser.setDateOfBirth(user.getDateOfBirth());

        existingUser.setEmail(user.getEmail());

        existingUser.setPhoneNumber(user.getPhoneNumber());

        existingUser.setAddress(user.getAddress());

        existingUser.setCountry(user.getCountry());

        existingUser.setCurrency(user.getCurrency());

        return this.userRepository.save(existingUser);
    }

    // delete user by id
    @DeleteMapping("delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable ("id") long userId){
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
        existingUser.setStatus("Deactivated");
        existingUser.setDateDeactivated(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Africa/Johannesburg")));
        this.userRepository.save(existingUser);
        return ResponseEntity.ok().build();
    }

}
