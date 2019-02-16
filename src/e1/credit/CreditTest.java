package e1.credit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditTest {

    private Credit credit = new Credit();

    @Test
    void testAmericanExpress() {
        Long number = 378282246310005L;
        String creditCard = credit.getType(number);
        Assertions.assertEquals("American Express", creditCard);
    }

    @Test
    void testMasterCard() {
        Long number = 5555555555554444L;
        String creditCard = credit.getType(number);
        Assertions.assertEquals("MasterCard", creditCard);
    }

    @Test
    void testVisa() {
        Long number = 4222222222222L;
        String creditCard = credit.getType(number);
        Assertions.assertEquals("Visa", creditCard);
    }

    @Test
    void testInvalid() {
        Long number = 2223016768739314L;
        String creditCard = credit.getType(number);
        Assertions.assertEquals("INVALID", creditCard);
    }
}