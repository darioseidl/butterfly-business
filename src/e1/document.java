package e1;

public class document {
    private String date;

    @Override
    public String toString() {
        return "document{" +
                "date='" + date + '\'' +
                ", criditNumb='" + criditNumb + '\'' +
                ", amount='" + amount + '\'' +
                ", issuer='" + issuer + '\'' +
                '}';
    }

    private String criditNumb;
    private String amount;
    private String issuer;

    public document(String date, String criditNumb, String amount) {
        this.date = date;
        this.criditNumb = criditNumb;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getCriditNumb() {
        return criditNumb;
    }


    public String getAmount() {
        return amount;
    }

public  String getIssuer(){
        return issuer;

}
public void setIssuer(String issuer){
        this.issuer=issuer;

}
}


