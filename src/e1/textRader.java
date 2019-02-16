package e1;

import e1.credit.CreditCards;
import e1.credit.LuhnValidator;

import java.util.List;
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

            System.out.println(digits);

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

        System.out.println(documents);

        List<String> csvLines = documents.stream().map(e -> e.toCSV()).collect(Collectors.toList());

        csvLines.add(0, "Date, Credit-Card-Number, Credit-Card-Issuer, Amount-Paid");
        WritingFiles.write(csvLines, "butterfly.csv");
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
                        columns[0],
                        columns[1],
                        columns[2]))
                .collect(Collectors.toList());


    }

    static List<String> partition(String line) {
        return IntStream.iterate(0, i -> i + 3).limit(line.length() / 3)
                .mapToObj(index -> line.substring(index, index + 3))
                .collect(Collectors.toList());
    }


}
