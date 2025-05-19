/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 *
 *  Unit tests for HomeController using MockMvc with security disabled.
 */

package com.bookclub.web;

import com.bookclub.BookclubApplication;
import com.bookclub.security.TestSecurityConfig;
import com.bookclub.service.dao.BookOfTheMonthDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests basic routing and view resolution for HomeController.
 */
@WebMvcTest(HomeController.class)
@SpringJUnitConfig
@ContextConfiguration(classes = { HomeController.class, TestSecurityConfig.class })
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookclubApplication bookclubApplication;

    @MockitoBean
    private BookOfTheMonthDao bookOfTheMonthDao;

    /**
     * Verifies that the home page loads and includes the "books" model attribute.
     */
    @Test
    public void testHomePageLoads() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk())
               .andExpect(view().name("index"))
               .andExpect(model().attributeExists("books"));
    }

    /**
     * Verifies that the about page returns the correct view.
     */
    @Test
    public void testAboutPageLoads() throws Exception {
        mockMvc.perform(get("/about"))
               .andExpect(status().isOk())
               .andExpect(view().name("about"));
    }
}
