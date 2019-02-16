package e1.credit;

import java.util.Arrays;

public class AmericanExpress extends CreditCard {

    public AmericanExpress() {
        super(Arrays.asList(
                new LengthValidator(Arrays.asList("15")),
                new StartsWithValidator(Arrays.asList("34", "37"))
        ));
    }

    @Override
    public String getName() {
        return "American Express";
    }

}
