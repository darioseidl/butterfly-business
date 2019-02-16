package e1.credit;

import java.util.Arrays;
import java.util.List;

public class CreditCards {

    private static List<CreditCard> creditCards = Arrays.asList(
            new AmericanExpress(), new MasterCard(), new Visa()
    );

    public static String getCreditCard(String cardNumber) {
        for (CreditCard creditCard : creditCards) {
            if(creditCard.isValid(cardNumber)){
                return creditCard.getName();
            }
        }
        return "INVALID";
    }
}
