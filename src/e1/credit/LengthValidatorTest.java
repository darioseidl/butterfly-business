package e1.credit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class LengthValidatorTest {

    @Test
    void isValidWithOneNumber() {
        LengthValidator validator = new LengthValidator(Arrays.asList("5"));
        boolean result = validator.isValid("12345");
        Assertions.assertTrue(result);
    }

    @Test
    void isValidWithSeveralNumbers() {
        LengthValidator validator = new LengthValidator(Arrays.asList("3", "5", "7"));
        boolean result = validator.isValid("12345");
        Assertions.assertTrue(result);
    }

    @Test
    void isValidWithNoNumber() {
        LengthValidator validator = new LengthValidator(new ArrayList<>());
        boolean result = validator.isValid("12345");
        Assertions.assertFalse(result);
    }
}