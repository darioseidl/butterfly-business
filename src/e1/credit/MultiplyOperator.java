package e1.credit;

public class MultiplyOperator implements Operator {

    @Override
    public Integer operate(Integer number) {
        number *= 2;
        if (number < 10) {
            return number;
        }
        Integer units = number % 10;
        Integer docens = number / 10;
        return units + docens;
    }
}

//        Integer result = number * 2;
//        if (number >= 5) {
//            result -= 9;
//        }
//        return result;

