package e1.credit;

public class LuhnValidator {

    public boolean isValid(String cardNumber) {
        LuhnsAlgorithm calculator = new LuhnsAlgorithm();
        Integer checksum = calculator.calculate(cardNumber);
        return checksum % 10 == 0;
    }
}
