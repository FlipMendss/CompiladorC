package felipe.org.example;

public class Valor {
    private int valInt;
    private double valorReal;
    private String valorID;

    public Valor(int valInt) {
        this.valInt = valInt;
    }
    public Valor(double valorReal) {
        this.valorReal = valorReal;
    }

    public Valor(String valorID) {
        this.valorID = valorID;
    }

    public Valor() {
    }

    public int getValInt() {
        return valInt;
    }

    public void setValInt(int valInt) {
        this.valInt = valInt;
    }

    public double getValorReal() {
        return valorReal;
    }

    public void setValorReal(double valorReal) {
        this.valorReal = valorReal;
    }

    public String getValorID() {
        return valorID;
    }

    public void setValorID(String valorID) {
        this.valorID = valorID;
    }

    @Override
    public String toString() {
        return "Valor{" +
                "valInt=" + valInt +
                ", valorReal=" + valorReal +
                ", valorID='" + valorID + '\'' +
                '}';
    }
}
