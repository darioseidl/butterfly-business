package e1.operation;

import java.time.LocalDate;
import java.time.YearMonth;

public class PaymentOperation {

    private LocalDate date;
    private String creditNumber;
    private Double amount;
    private String issuer;

    public PaymentOperation(LocalDate date, String creditNumber, Double amount) {
        this.date = date;
        this.creditNumber = creditNumber;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public YearMonth getYearMonth() {
        return YearMonth.from(date);
    }

    public String getCreditNumber() {
        return creditNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @Override
    public String toString() {
        return "document{" +
                "date=" + date +
                ", creditNumber=" + creditNumber +
                ", amount=" + amount +
                ", issuer=" + issuer +
                '}';
    }

    public String toCSV() {
        return date + ", " + creditNumber + ", " + issuer + ", " + amount;
    }

}


