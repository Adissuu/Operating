class Server extends Thread {
    

    int numberOfTransactions;
    int numberOfAccounts;
    int maxNbAccounts;
    //Transactions transaction;
    //Network objNetwork;
    Accounts[] account;

    public int getNumberOfTransactions( ) {
        return numberOfTransactions;
    }
    public int getNumberOfAccounts( ) {
        return numberOfAccounts;
    }
    public int getMaxNbAccounts() {
        return maxNbAccounts;
    }
    public void setNumberOfTransactions(int nbOfTrans) {

    }
    public void setNumberOfAccounts(int nbOfAcc) {

    }
    public void setMaxNbAccounts(int nbOfAcc) {

    }
    public void initializeAccounts( ) {

    }
    public int findAccount(String accNumber)  {
        ;
    }
    public boolean processTransactions(Transaction trans)  {

    }
    public double deposit(int i, double amount) {

    }
    public double withdraw(int i, double amount) {

    }
    public double query(int i) {

    }
    public String toString() {
        return "nothing for now";
    }
    public void run() {

    }
}
