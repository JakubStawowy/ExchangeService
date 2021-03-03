package com.example.demo.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberFormatValidatorTest {

    @Test
    void checkStringParseToBigDecimal() {

        // given
        NumberFormatValidator numberFormatValidator = new NumberFormatValidator();
        String correctNumberFormatString = "1000.1000";
        String wrongNumberFormatString = "1000.1000wrong";

        // when
        Boolean condition1 = numberFormatValidator.checkStringParseToBigDecimal(correctNumberFormatString);
        Boolean condition2 = numberFormatValidator.checkStringParseToBigDecimal(wrongNumberFormatString);

        // then
        assertTrue(condition1);
        assertFalse(condition2);

    }
}