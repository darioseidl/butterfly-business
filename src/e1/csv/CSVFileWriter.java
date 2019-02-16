package e1.csv;

import e1.operation.PaymentOperation;
import e1.files.FileWriter;

import java.util.List;
import java.util.stream.Collectors;

public class CSVFileWriter {

    public static void writeCSV(List<PaymentOperation> paymentOperations, String filePath) {
        List<String> csvLines = paymentOperations.stream().map(PaymentOperation::toCSV).collect(Collectors.toList());

        System.out.println(String.join("\n", csvLines));

        csvLines.add(0, "Date, Credit-Card-Number, Credit-Card-Issuer, Amount-Paid");
        FileWriter.write(csvLines, filePath);
    }
}
