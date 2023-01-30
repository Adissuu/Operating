class Client extends Thread{

    int numberOfTransactions;
    int maxNbTransactions;
    Transactions[] transcations;
    String clientOperation;
    //Network objNetwork;

    public int getNumberOfTransacrions(){
        return numberOfTransactions;
    }
    public String getClientOperation(){
        return clientOperation;
    }
    public void setNumberOfTransactions(int nbOfTrans){
        numberOfTransactions = nbOfTrans;
    }
    public void setClientOperation(String operation){
        clientOperation = operation;
    }
    public void readTransactions(){

    }
    public void sendTransactions(){

    }
    public void receiveTransactions(Transactions transact){

    }
    public String toString(){
        return "Nothing for now!";
    }
    public void run(){

    }

}