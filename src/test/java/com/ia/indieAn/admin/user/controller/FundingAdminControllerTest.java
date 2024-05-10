package com.ia.indieAn.admin.user.controller;

import com.ia.indieAn.admin.user.service.FundingAdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FundingAdminController.class)
@AutoConfigureMockMvc
class FundingAdminControllerTest {

    @MockBean
    FundingAdminService fundingAdminService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void fundList() throws Exception {
        mockMvc.perform(post("/api/admin/fundList"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}