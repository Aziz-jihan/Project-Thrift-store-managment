public class Customer {
    private String name;
    private String email;
    private String password;
    private String transactionDetails;

    public Customer(String name, String email, String password, String transactionDetails) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.transactionDetails = transactionDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(String transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    @Override
    public String toString() {
        return name + "|" + email + "|" + password + "|" + transactionDetails;
    }
}
