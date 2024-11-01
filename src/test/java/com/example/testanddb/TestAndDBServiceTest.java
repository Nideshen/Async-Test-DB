package com.example.testanddb;

import com.example.testanddb.business.po.AppUser;
import com.example.testanddb.business.repository.AppUserRepository;
import com.example.testanddb.business.service.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class TestAndDBServiceTest {
    @Mock
    private AppUserRepository appUserRepository;
    @InjectMocks
    private AppUserService appUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAppUser() {
        AppUser appUser = new AppUser("Ray", "Ray@hotmail.com");
//        when(appUserRepository.save(appUser)).thenReturn(new AppUser("Ray", "Ray@hotmail.com"));
        AppUser createdAppUser = appUserService.createAppUser(appUser);
        assertThat(createdAppUser.getId()).isNotNull();
        assertThat(createdAppUser.getName()).isEqualTo("Ray");
    }
}
