public class Accounts {
    String accountNumber;
        String accountType;
        String firstName;
        String lastName;
        double balance;
        
        public String getAccountNumber( ) {
            return accountNumber;
        }
        public String getAccountType( ) {
            return accountType;
        }
        public String getFirstName( ) {
            return firstName;
        }
        public String getLastname( ) {
            return lastName;
        }
        public double getBalance( ) {
            return balance;
        }
        public void setAccountNumber(double accNumber) {
            accountNumber = accNumber;
        }
        public void setAccountType(String accType) {
            accountType = accType;
        }
        public void setFirstName(String fName) {
            firstName = fName;
        }
        public void setLastname(String lName) {
            lastName = lName;
        }
        public void setBalance(double bal) {
            balance = bal;
        }
        public String toString( ) {
            return "nothing for now";
        }
}
