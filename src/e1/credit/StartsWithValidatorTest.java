package e1.credit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class StartsWithValidatorTest {

    @Test
    void testWithOneNumber() {
        StartsWithValidator validator = new StartsWithValidator(Arrays.asList("4"));
        boolean result = validator.isValid("4321");
        Assertions.assertTrue(result);
    }

    @Test
    void testWithSeveralNumbers() {
        StartsWithValidator validator = new StartsWithValidator(Arrays.asList("34", "37"));
        boolean result = validator.isValid("374321");
        Assertions.assertTrue(result);
    }

    @Test
    void testWithNoNumber() {
        StartsWithValidator validator = new StartsWithValidator(new ArrayList<>());
        boolean result = validator.isValid("374321");
        Assertions.assertFalse(result);
    }

}