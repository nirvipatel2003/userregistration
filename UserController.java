package io.bytesbank.registration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.bytesbank.registration.dto.CreateUserDto;
import io.bytesbank.registration.exception.UserNotFoundException;
import io.bytesbank.registration.model.User;
import io.bytesbank.registration.service.UserService;
import io.bytesbank.registration.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final ObjectMapper objectMapper;
    private final UserService userService ;

    public UserController(ObjectMapper objectMapper, UserService userService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
    }


    @PostMapping()
    public ResponseEntity<String> create(@RequestBody CreateUserDto req) {
        try {
            String json = objectMapper.writeValueAsString(req);
            log.info("Received request to create user: {}", json);
        } catch (JsonProcessingException e) {
            log.error("Error parsing request: {}", e.getMessage());
        }

        userService.create(req);
        log.info("User created successfully.");

        return ResponseEntity.ok("User created successfully!"); // ✅ Return only success message
    }


    @GetMapping("/findByName/{firstName}")
    public ResponseEntity<List<User>> findByName(@PathVariable String firstName){
        List<User> result = userService.findByName(firstName);
        return ResponseEntity.ok(result);

    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<User> findById(@PathVariable String id){
        User result = userService.findById(id);
        return ResponseEntity.ok(result);
    }
    @PutMapping("/{id}/mobile/{mobile}")
    public ResponseEntity<User> updateMobile(@PathVariable String id,
                                             @PathVariable  String mobile) {
        User updatedUser = userService.updateMobile(id, mobile);
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/findById/{id}")
    public ResponseEntity<User> deleteById(@PathVariable String id) throws UserNotFoundException {
        long startedOn = System.currentTimeMillis();
        Optional<User> result = userService.deleteId(id);
        log.debug("[{}]ms deleteById Api Response time.", System.currentTimeMillis() - startedOn);
        if (result.isEmpty()) {
            throw new UserNotFoundException(String.format("User %s not found", id));
        } else {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/mapByMobile")
    public ResponseEntity<Map<String, User>> getUsersMappedByMobile(){
        return ResponseEntity.ok(userService.getUsersMappedByMobile());
    }

}