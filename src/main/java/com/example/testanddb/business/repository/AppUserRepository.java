package com.example.testanddb.business.repository;

import com.example.testanddb.business.po.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    List<AppUser> findAllByName(String name);
}
