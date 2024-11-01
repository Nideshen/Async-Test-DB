package com.example.testanddb.business.service;

import com.example.testanddb.business.po.AppUser;
import com.example.testanddb.business.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }

    public AppUser createAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public List<AppUser> getAllUsersByName(String name) { return appUserRepository.findAllByName(name); }
}
