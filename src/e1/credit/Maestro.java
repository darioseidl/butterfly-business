package e1.credit;

import java.util.Arrays;

public class Maestro extends CreditCard {

    public Maestro() {
        super(Arrays.asList(
                new LengthValidator(Arrays.asList("16","19")),
                new StartsWithValidator(Arrays.asList("5018", "5020", "5038", "5893", "6304", "6759", "6761", "6762", "6763"))
        ));
    }

    @Override
    public String getName() {
        return "Maestro";
    }

}
