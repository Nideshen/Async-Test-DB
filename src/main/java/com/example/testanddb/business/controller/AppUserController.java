package com.example.testanddb.business.controller;

import com.example.testanddb.business.po.AppUser;
import com.example.testanddb.business.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/app-user")
public class AppUserController {
    @Autowired
    private AppUserService appUserService;

    @GetMapping
    public List<AppUser> getAllUsers() {
        List<AppUser> appUsers = appUserService.getAllAppUsers();
        log.info("get appUsers: {}", appUsers.toString());
        return appUsers;
    }

    @PostMapping
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser) {
        AppUser createdAppUser = appUserService.createAppUser(appUser);
        log.info("created appUser: {}", createdAppUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppUser);
    }

    @GetMapping("/{name}")
    public AppUser getUserByName(@PathVariable("name") String name) {
        log.info("AppUserPO: {}", appUserService.getAllUsersByName(name).getFirst());
        return appUserService.getAllUsersByName(name).getFirst();
    }
}
