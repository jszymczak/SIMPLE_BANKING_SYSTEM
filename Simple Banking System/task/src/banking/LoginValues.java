package banking;

public class LoginValues {
    String cardNumber;
    String pinNumber;

    public LoginValues(String cardNumber, String pinNumber) {
        this.cardNumber = cardNumber;
        this.pinNumber = pinNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

}
