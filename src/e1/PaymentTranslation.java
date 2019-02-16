package e1;

import e1.credit.CreditCards;
import e1.credit.LuhnValidator;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PaymentTranslation {

    public static void main(String[] args) {

        FileReader fileReader = new FileReader();

        List<String> lines = fileReader.asList("Files/buterfly-business.txt");
        List<String> parsedLines = IntStream.iterate(0, i -> i + 3).limit(lines.size() / 3)
                .mapToObj(i -> {


                    List<String> partition0 = partition(lines.get(i));
                    List<String> partition1 = partition(lines.get(i + 1));
                    List<String> partition2 = partition(lines.get(i + 2));


                    List<String> strangeList = IntStream.range(0, partition0.size())
                            .mapToObj(index -> partition0.get(index) + partition1.get(index) + partition2.get(index))
                            .collect(Collectors.toList());


                    String digits = strangeList.stream()
                            .map(digit -> SevenSegmentMapping.strangeDigits.get(digit))
                            .collect(Collectors.joining());


                    return digits;
                }).collect(Collectors.toList());


        List<PaymentOperation> documents = getDocument(parsedLines);


        documents.forEach(e -> e.setIssuer(getIssuer(e.getCriditNumb())));

        documents.sort(Comparator.comparing(PaymentOperation::getDate));


        List<String> csvLines = documents.stream().map(PaymentOperation::toCSV).collect(Collectors.toList());

        System.out.println(String.join("\n", csvLines));

        csvLines.add(0, "Date, Credit-Card-Number, Credit-Card-Issuer, Amount-Paid");
        WritingFiles.write(csvLines, "butterfly.csv");
        double totalAmount = documents.stream()
                .mapToDouble(PaymentOperation::getAmount)
                .sum();

        System.out.println("The total is " + totalAmount);



        Map<YearMonth, Double> groupSumByYearMonth = documents.stream()
                .collect(Collectors.groupingBy(PaymentOperation::getYearMonth, Collectors.summingDouble(PaymentOperation::getAmount)));

        System.out.println("sum by month: " + groupSumByYearMonth);

        Map.Entry<YearMonth, Double> mostProfitable =
                groupSumByYearMonth.entrySet().stream()
                        .max(Comparator.comparingDouble(Map.Entry::getValue))
                        .get();

        System.out.println("most profitable month: " + mostProfitable.getKey().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));


        Map<String, Long> issuerCount = documents.stream()
                .collect(Collectors.groupingBy(PaymentOperation::getIssuer, Collectors.counting()));

        System.out.println("issuer count: " + issuerCount);

        Map.Entry<String, Long> mostUsedIssuer =
                issuerCount.entrySet().stream()
                        .max(Comparator.comparingLong(Map.Entry::getValue))
                        .get();

        System.out.println("most used issuer: " + mostUsedIssuer);
        List<String> resultLine = new ArrayList<>();
        resultLine.add("The total is " + totalAmount);
        resultLine.add("most profitable month: " + mostProfitable.getKey().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                + " with a total of " + mostProfitable.getValue());
        int numberOfMonths = groupSumByYearMonth.keySet().size();
        System.out.println(numberOfMonths);
        resultLine.add("average by month: " + totalAmount / numberOfMonths);
        resultLine.add("most used issuer: " + mostUsedIssuer);

        WritingFiles.write(resultLine, "Result.txt");
    }

    public static String getIssuer(String creditNumber) {
        LuhnValidator luhnValidator = new LuhnValidator();
        if (!luhnValidator.isValid(creditNumber))
            return "invalid";

        String issuer = CreditCards.getCreditCard(creditNumber);

        if (issuer == null)
            return "unknown";
        else
            return issuer;
    }










    public static List<PaymentOperation> getDocument(List<String> parts) {
        return parts.stream()
                .map(line -> line.split(" "))
                .map(columns -> new PaymentOperation(
                        LocalDate.parse(columns[0]),
                        columns[1],
                        Double.valueOf(columns[2])))
                .collect(Collectors.toList());
    }

    static List<String> partition(String line) {
        return IntStream.iterate(0, i -> i + 3).limit(line.length() / 3)
                .mapToObj(index -> line.substring(index, index + 3))
                .collect(Collectors.toList());
    }


}
