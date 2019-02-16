package e1;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class textRader {

    public static void main(String[] args) {

        FileReader fileReader = new FileReader();
        List<String> lines = fileReader.asList("Files/buterfly-business.txt");

        List<String> parsedLines = IntStream.iterate(0, i -> i + 3).limit(lines.size()/3)
                .mapToObj(i -> {

            List<String> partition0 = partition(lines.get(i));
            List<String> partition1 = partition(lines.get(i+1));
            List<String> partition2 = partition(lines.get(i+2));

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
    }

    static List<String> partition(String line) {
        return IntStream.iterate(0, i -> i + 3).limit(line.length()/3)
                .mapToObj(index -> line.substring(index, index + 3))
                .collect(Collectors.toList());
    }


}
