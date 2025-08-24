package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public User createEntry(@RequestBody User user){
        userService.saveEntry(user);
        return user;
    }

    @GetMapping("/health-check")
    public String healthCheck(){
        return "ok";
    }
}
