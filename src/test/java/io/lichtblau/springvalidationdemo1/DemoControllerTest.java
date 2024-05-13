package io.lichtblau.springvalidationdemo1;

import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    // PASSES: No validation errors
    void test1Works() {
        mockMvc.perform(post("/{pathVariable}/test1", "testVariable")
                        .contentType(MediaType.APPLICATION_JSON) //
                        .content("{ \"test\": \"bodyContent\" }")) //
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    // FAILS: Exception is of type org.springframework.web.bind.MethodArgumentNotValidException
    void test1ShouldThrowValidationException() {
        val result = mockMvc.perform(post("/{pathVariable}/test1", "testVariable")
                        .contentType(MediaType.APPLICATION_JSON) //
                        .content("{ \"test\": \"\" }")) //
                        .andReturn();
        val exception = Objects.requireNonNull(result.getResolvedException());
        assertEquals(HandlerMethodValidationException.class, exception.getClass());
    }

    @Test
    @SneakyThrows
    // PASSES: No validation errors
    void test2Works() {
        mockMvc.perform(post("/{pathVariable}/test2", "testVariable")
                .contentType(MediaType.APPLICATION_JSON) //
                .content("{ \"test\": \"bodyContent\" }")) //
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    // PASSES: Exception is of type org.springframework.web.method.annotation.HandlerMethodValidationException
    void test2ShouldThrowValidationException() {
        val result = mockMvc.perform(post("/{pathVariable}/test2", "testVariable")
                        .contentType(MediaType.APPLICATION_JSON) //
                        .content("{ \"test\": \"\" }")) //
                        .andReturn();
        val exception = Objects.requireNonNull(result.getResolvedException());
        assertEquals(HandlerMethodValidationException.class, exception.getClass());
    }

}