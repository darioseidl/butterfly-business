package e1;

import java.time.LocalDate;
import java.time.YearMonth;

public class document {
    private LocalDate date;
    private String criditNumb;
    private Double amount;
    private String issuer;

    public document(LocalDate date, String criditNumb, Double amount) {
        this.date = date;
        this.criditNumb = criditNumb;
        this.amount = amount;

    }

    public LocalDate getDate() {
        return date;
    }

    public YearMonth getYearMonth() {
        return YearMonth.from(date);
    }

    public String getCriditNumb() {
        return criditNumb;
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
                ", criditNumb=" + criditNumb +
                ", amount=" + amount +
                ", issuer=" + issuer +
                '}';
    }

    public String toCSV() {
        return date + ", " + criditNumb + ", " + issuer + ", " + amount;
    }


}


