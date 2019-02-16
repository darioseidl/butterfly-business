package e1;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class textRader {
    public static void main(String[] args) {




        FileReader fileReader = new FileReader();
        List<String> lines = fileReader.asList("Files/buterfly-business.txt");

        List<String> partition0 = partition(lines.get(0));
        List<String> partition1 = partition(lines.get(1));
        List<String> partition2 = partition(lines.get(2));

        List<String> strangeList = IntStream.range(0, partition0.size())
                .mapToObj(index -> partition0.get(index) + partition1.get(index) + partition2.get(index))
                .collect(Collectors.toList());

//        for (String s : strangeList) {
//            System.out.println(s);
//        }

        List<String> digits = strangeList.stream()
                .map(digit -> SevenSegmentMapping.strangeDigits.get(digit))
                .collect(Collectors.toList());

        System.out.println(digits);

    }

    static List<String> partition(String line) {
        return IntStream.range(0, line.length()/3)
                .mapToObj(index -> line.substring(index*3, index*3 + 3))
                .collect(Collectors.toList());
    }


}
