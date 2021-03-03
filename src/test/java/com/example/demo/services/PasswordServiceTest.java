package com.example.demo.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class PasswordServiceTest {

    @Test
    void getHashedPassword() {

        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {

                // given
                String testPassword1 = "test_password";
                String testPassword2 = "test_password";

                // when
                String hashedPassword1 = PasswordService.getHashedPassword(testPassword1);
                String hashedPassword2 = PasswordService.getHashedPassword(testPassword2);

                // then
                assertNotEquals(hashedPassword1, testPassword1);
                assertEquals(hashedPassword1, hashedPassword2);
            }
        });
    }
}