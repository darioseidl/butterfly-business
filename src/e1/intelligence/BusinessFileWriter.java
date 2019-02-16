package e1.intelligence;

import e1.files.FileWriter;
import e1.operation.PaymentOperation;

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

        List<String> resultLines = new ArrayList<>();
        resultLines.add("The total is " + totalAmount);
        resultLines.add("The most profitable month is " + getNameOfMonth(mostProfitable.getKey()) + " with a total of " + mostProfitable.getValue());
        resultLines.add("The average amount per month is " + averageAmountPerMonth);
        resultLines.add("The most used issuer is " + mostUsedIssuer.getKey() + " was used " + mostUsedIssuer.getValue() + " times");

        System.out.println(String.join("\n", resultLines));

        FileWriter.write(resultLines, filePath);
    }

    private static String getNameOfMonth(YearMonth yearMonth) {
        return yearMonth.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }
}
