package e1.intelligence;

import e1.PaymentOperation;

import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BusinessIntelligence {

    public static double getTotalAmount(List<PaymentOperation> paymentOperations) {
        return paymentOperations.stream()
                .mapToDouble(PaymentOperation::getAmount)
                .sum();
    }

    public static Map<YearMonth, Double> getGroupSumByMonth(List<PaymentOperation> paymentOperations) {
        return paymentOperations.stream()
                .collect(Collectors.groupingBy(PaymentOperation::getYearMonth, Collectors.summingDouble(PaymentOperation::getAmount)));
    }

    public static Map.Entry<YearMonth, Double> getMostProfitableMonth(List<PaymentOperation> paymentOperations) {
        return getGroupSumByMonth(paymentOperations).entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .get();
    }

    public static double getAverageAmountPerMonth(List<PaymentOperation> paymentOperations) {
        return getTotalAmount(paymentOperations) / getNumberOfMonths(paymentOperations);
    }

    public static Map.Entry<String, Long> getMostUsedIssuer(List<PaymentOperation> paymentOperations) {
        return paymentOperations.stream()
                .collect(Collectors.groupingBy(PaymentOperation::getIssuer, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .get();
    }

    public static int getNumberOfMonths(List<PaymentOperation> paymentOperations) {
        return paymentOperations.stream()
                .map(PaymentOperation::getYearMonth)
                .collect(Collectors.toSet())
                .size();
    }

}
