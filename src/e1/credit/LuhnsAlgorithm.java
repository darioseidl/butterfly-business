package e1.credit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LuhnsAlgorithm {

    public Integer calculate(String cardNumber) {
        List<Integer> digits = getDigits(cardNumber);
        Collections.reverse(digits);
        return accumulate(digits);
    }

    private List<Integer> getDigits(String cardNumber) {
        String[] split = cardNumber.split("");
        List<String> letters = Arrays.asList(split);
        List<Integer> digits = new ArrayList<>();
        for (String letter : letters) {
            Integer digit = Integer.valueOf(letter);
            digits.add(digit);
        }
        return digits;
    }

    private Integer accumulate(List<Integer> digits) {
        List<Operator> operators = Arrays.asList(
                new StayOperator(), new MultiplyOperator()
        );

        Integer total = 0;
        for (Integer digit : digits) {
            Operator operator = operators.get(0);
            total = total + operator.operate(digit);
            Collections.rotate(operators, -1);
        }
        return total;
    }
}
