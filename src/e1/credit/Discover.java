package e1.credit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Discover extends CreditCard {

    public Discover() {
        super(Arrays.asList(
                new LengthValidator(Arrays.asList("16","19")),
                new StartsWithValidator(discoverStartWith())));
    }

    public static List<String>discoverStartWith() {
        List<String> startWith = new ArrayList<>();
        startWith.addAll(Arrays.asList("6011", "644", "645", "646", "647", "648", "649", "65"));
        startWith.addAll(IntStream.range(622126, 622925).mapToObj(String::valueOf).collect(Collectors.toList()));
        return startWith;
    }

    @Override
    public String getName() {
        return "Discover";
    }

}
