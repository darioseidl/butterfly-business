package e1;

import e1.credit.CreditCards;
import e1.credit.LuhnValidator;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class textRader {

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

//        for (String s : strangeList) {
//            System.out.println(s);
//        }

                    String digits = strangeList.stream()
                            .map(digit -> SevenSegmentMapping.strangeDigits.get(digit))
                            .collect(Collectors.joining());

//            System.out.println(digits);

                    return digits;

                }).collect(Collectors.toList());

//        System.out.println(parsedLines);
        List<document> documents = getDocument(parsedLines);

//        System.out.println(documents);
//        LuhnValidator luhnValidator = new LuhnValidator();
//        List<String> collect = documents.stream()
//                .map(e -> getIssuer(e.getCriditNumb()))
//                .collect(Collectors.toList());
//        System.out.println(collect);

        documents.forEach(e -> e.setIssuer(getIssuer(e.getCriditNumb())));

        documents.sort(Comparator.comparing(document::getDate));

//        String collect = documents.stream().map(e -> e.getAmount().toString() + "+").collect(Collectors.joining());
//        System.out.println(collect);

        List<String> csvLines = documents.stream().map(e -> e.toCSV()).collect(Collectors.toList());

        System.out.println(String.join("\n", csvLines));

        csvLines.add(0, "Date, Credit-Card-Number, Credit-Card-Issuer, Amount-Paid");
        WritingFiles.write(csvLines, "butterfly.csv");
        double totalAmount = documents.stream()
                .mapToDouble(document::getAmount)
                .sum();

        System.out.println("The total is "+ totalAmount);


//        Map<YearMonth, List<document>> groupByYearMonth = documents.stream()
//                .collect(Collectors.groupingBy(document::getYearMonth));
//        System.out.println(groupByYearMonth);

        Map<YearMonth, Double> groupSumByYearMonth = documents.stream()
                .collect(Collectors.groupingBy(document::getYearMonth, Collectors.summingDouble(document::getAmount)));

        System.out.println("sum by month: " + groupSumByYearMonth);

        Map.Entry<YearMonth, Double> mostProfitable =
                groupSumByYearMonth.entrySet().stream()
                        .max((entry1, entry2) -> Double.compare(entry1.getValue(), entry2.getValue()))
                        .get();

        System.out.println("most profitable month: " + mostProfitable.getKey().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));

//        Map<YearMonth, Double> groupAverageByYearMonth = documents.stream()
//                .collect(Collectors.groupingBy(document::getYearMonth, Collectors.averagingDouble(document::getAmount)));
//
//        System.out.println("average by month: " + groupAverageByYearMonth);

        Map<String, Long> issuerCount = documents.stream()
                .collect(Collectors.groupingBy(document::getIssuer, Collectors.counting()));

        System.out.println("issuer count: " + issuerCount);

        Map.Entry<String, Long> mostUsedIssuer =
                issuerCount.entrySet().stream()
                        .max((entry1, entry2) -> Long.compare(entry1.getValue(), entry2.getValue()))
                        .get();

        System.out.println("most used issuer: " + mostUsedIssuer);
        List<String> resultLine=new ArrayList<>();
        resultLine.add("The total is "+ totalAmount);
        resultLine.add("most profitable month: " + mostProfitable.getKey().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                + " with a total of " + mostProfitable.getValue());
        int numberOfMonths = groupSumByYearMonth.keySet().size();
        System.out.println(numberOfMonths);
        resultLine.add("average by month: " + totalAmount/numberOfMonths);
        resultLine.add("most used issuer: " + mostUsedIssuer);

        WritingFiles.write(resultLine,"Result.txt");
    }

    public static String getIssuer(String creditNumber) {
        LuhnValidator luhnValidator = new LuhnValidator();
        if (!luhnValidator.isValid(creditNumber))
            return "invalid";

        String issuer =  CreditCards.getCreditCard(creditNumber);

        if (issuer == null)
            return "unknown";
        else
            return issuer;
    }

    public static List<document> getDocument(List<String> parts) {
        return parts.stream()
                .map(line -> line.split(" "))
                .map(columns -> new document(
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
