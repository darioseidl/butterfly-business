package e1.credit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LuhnValidatorTest {

    private LuhnValidator validator = new LuhnValidator();

    @Test
    void isValid() {
        String number = "378281";
        boolean result = validator.isValid(number);
        Assertions.assertTrue(result);
    }

    @Test
    void isInvalid() {
        String number = "378282";
        boolean result = validator.isValid(number);
        Assertions.assertFalse(result);
    }
}