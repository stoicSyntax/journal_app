package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping
    public User createEntry(@RequestBody User user){
        userService.saveEntry(user);
        return user;
    }

    @GetMapping("/id/{myId}")
    public Optional<User> getUserByID(@RequestBody User user){
        return userService.getById(user.getId());
    }

    @DeleteMapping("/id/{myId}")
    public User deleteUserById(@RequestBody User user){
        userService.deleteById(user.getId());
        return user;
    }

    @PutMapping("{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user ,@PathVariable String userName){
        User userInDB = userService.findByUserName(userName);
        if (userInDB != null){

            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            userService.saveEntry(userInDB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
