public class Payment {
    private String cardNumber;
    private String cardHolderName;
    private double amount;

    public Payment(String cardNumber, String cardHolderName, double amount) {
        setCardNumber(cardNumber);
        setCardHolderName(cardHolderName);
        setAmount(amount);
    }

    public void setCardNumber(String cardNumber) {
        if (cardNumber == null) {throw new IllegalArgumentException("Invalid card number. It must be a 16-digit number.");}
        String sanitizedCardNumber = cardNumber.replaceAll("\\s", "");
        if (!sanitizedCardNumber.matches("\\d{16}")) {
            throw new IllegalArgumentException("Invalid card number. It must be a 16-digit number.");
        }
        this.cardNumber = sanitizedCardNumber;
    }
    
    public void setCardHolderName(String cardHolderName) {
        if (cardHolderName == null || cardHolderName.trim().isEmpty()) {
            throw new IllegalArgumentException("Cardholder name cannot be null or empty.");
        }
        this.cardHolderName = cardHolderName.trim();
    }

    public void setAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        this.amount = amount;
    }

    public String getCardNumber() {return cardNumber;}
    public String getCardHolderName() {return cardHolderName;}
    public double getAmount() {return amount;}
    
    private String maskCardNumber(String cardNumber) {return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);}

    @Override
    public String toString() {
        return "Payment Details:\n" +
               "Card Holder: " + cardHolderName + "\n" +
               "Card Number: " + maskCardNumber(cardNumber) + "\n" +
               "Amount Paid: $" + String.format("%.2f", amount);
    }
}
