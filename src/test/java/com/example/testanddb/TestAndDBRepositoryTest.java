package com.example.testanddb;

import com.example.testanddb.business.po.AppUser;
import com.example.testanddb.business.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TestAndDBRepositoryTest {
    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    @Transactional
    public void testCreateUser() {
        AppUser appUser = new AppUser("Jacob", "jacob@gmail.com");
        AppUser savedAppUser = appUserRepository.save(appUser);
        assertThat(savedAppUser.getId()).isNotNull();
        assertThat(savedAppUser.getName()).isEqualTo("Jacob");
    }
}
