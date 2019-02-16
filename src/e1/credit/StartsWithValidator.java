package e1.credit;

import java.util.List;

public class StartsWithValidator extends Validator {

    public StartsWithValidator(List<String> possibilities) {
        super(possibilities);
    }

    @Override
    public boolean isValid(String cardNumber) {
        List<String> beginnings = getPossibilities();
        for (String beginning : beginnings) {
            if (cardNumber.startsWith(beginning)) {
                return true;
            }
        }
        return false;
    }
}
