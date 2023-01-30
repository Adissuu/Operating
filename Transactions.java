public class Transactions {
    String accountNumber;
        String operationType;
        double transactionAmount;
        double transactionBalance;
        String transactionError;
        String transactionStatus;
    
        public String getTransactionType(){
            return operationType;

        }

        public String getAccountNumber(){
            return accountNumber;
        }

        public double getTransactionAmount(){
            return transactionAmount;
        }

        public double TransactionBalance(){
            return transactionBalance;
        }

        public String getTransaactionError(){
            return transactionError;
        }

        public String getTransactionStatus(){
            return transactionStatus;
        }

        public void setAccoundtNumber(String accNumber){

        }

        public void setTransactionType(String opType){

        }

        public void setTransactionAmount(double transAmount){

        }

        public void setTransactionBalance(double transBalance){

        }

        public void setTransactionError(String transError){

        }

        public void setTransactionStatus(String transStatus){

        }

        public String toString(){
            return "Nothing for now!";
        }
}
