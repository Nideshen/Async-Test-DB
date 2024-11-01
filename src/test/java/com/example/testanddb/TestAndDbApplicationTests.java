package com.example.testanddb;

import com.example.testanddb.business.controller.AppUserController;
import com.example.testanddb.business.po.AppUser;
import com.example.testanddb.business.service.AppUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestAndDbApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Transactional
    void createUser_shouldReturnCreatedUser() throws Exception {
        AppUser appUser = new AppUser("Test User3", "test3@dummy.com");
        mockMvc.perform(post("/api/app-user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(appUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test User3"))
                .andExpect(jsonPath("$.email").value("test3@dummy.com"));
        mockMvc.perform(get("/api/app-user/Test User3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test User3"));
    }

    @Test
    void getUserByName_shouldReturnUser() throws Exception {
        mockMvc.perform(get("/api/app-user/Test User"));  // use space instead of %20
    }
}