package e1.intelligence;

import e1.FileWriter;
import e1.PaymentOperation;

import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class BusinessFileWriter {

    public static void writeBusinessIntelligenceReport(List<PaymentOperation> paymentOperations, String filePath) {
        double totalAmount = BusinessIntelligence.getTotalAmount(paymentOperations);

        Map.Entry<YearMonth, Double> mostProfitable = BusinessIntelligence.getMostProfitableMonth(paymentOperations);

        double averageAmountPerMonth = BusinessIntelligence.getAverageAmountPerMonth(paymentOperations);

        Map.Entry<String, Long> mostUsedIssuer = BusinessIntelligence.getMostUsedIssuer(paymentOperations);

        List<String> resultLine = new ArrayList<>();
        resultLine.add("The total is " + totalAmount);
        resultLine.add("The most profitable month is " + getNameOfMonth(mostProfitable.getKey()) + " with a total of " + mostProfitable.getValue());
        resultLine.add("The average amount per month is " + averageAmountPerMonth);
        resultLine.add("The most used issuer is " + mostUsedIssuer);

        FileWriter.write(resultLine, filePath);
    }

    private static String getNameOfMonth(YearMonth yearMonth) {
        return yearMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }
}
