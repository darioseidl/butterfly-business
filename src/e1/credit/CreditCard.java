package e1.credit;

import java.util.List;

public abstract class CreditCard {

    private List<Validator> validators;

    public CreditCard(List<Validator> validators) {
        this.validators = validators;
    }

    public abstract String getName();

    public boolean isValid(String cardNumber) {
        for (Validator validator : validators) {
            if (!validator.isValid(cardNumber)) {
                return false;
            }
        }
        return true;
    }
}
