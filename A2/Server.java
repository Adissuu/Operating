package A2;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/** Server class
 *
 * @authors Ali Fetanat (40158208), Gabriel Dubois (40209252), Kerly Titus
 */

public class Server extends Thread{
    /* Variables are now static to shared between threads */
	private static int numberOfTransactions;         /* Number of transactions handled by the server */
    private static int numberOfAccounts;             /* Number of accounts stored in the server */
    private static int maxNbAccounts;                /* maximum number of transactions */
    private static Accounts [] account;              /* Accounts to be accessed or updated */
    private String serverThreadId;                  /* Identify the two Threads*/

    private static String serverThreadRunningStatus1;	 /* Running status of thread 1 - idle, busy, terminated */
    private static String serverThreadRunningStatus2;	 /* Running status of thread 2 - idle, busy, terminated */
    private static String serverThreadRunningStatus3;	 /* Running status of thread 3 - idle, busy, terminated */

    public static boolean thirdServerThread = false;
    Transactions transaction;         /* Transaction being processed */
	Network objNetwork;               /* Server object to handle network operations */


    /** 
     * Constructor method of Client class
     * 
     * @return 
     * @param
     */
    Server(String sTId)
    {
        if(!(objNetwork.getServerConnectionStatus().equals("connected"))) {
            System.out.println("\n Initializing the server ...");
            numberOfTransactions = 0;
            numberOfAccounts = 0;
            maxNbAccounts = 100;
            transaction = new Transactions();
            serverThreadId = sTId;
            account = new Accounts[maxNbAccounts];
            //objNetwork = new Network("server");
            System.out.println("\n Initializing the Accounts database ...");
            initializeAccounts();
            System.out.println("\n Connecting server to network ...");
            if (!(objNetwork.connect(objNetwork.getServerIP()))) {
                System.out.println("\n Terminating server application, network unavailable");
                System.exit(0);
            }
        }else {
            serverThreadId = sTId;

            switch (serverThreadId) {
                case "Thread1":
                    serverThreadRunningStatus1 = "idle";
                    break;
                case "Thread2":
                    serverThreadRunningStatus2 = "idle";
                    break;
                case "Thread3":
                    serverThreadRunningStatus3 = "idle";
                    break;
            }
        }
    }
  
    /** 
     * Accessor method of Server class
     * 
     * @return numberOfTransactions
     * @param
     */
     public int getNumberOfTransactions()
     {
         return numberOfTransactions;
     }
         
    /** 
     * Mutator method of Server class
     * 
     * @return 
     * @param nbOfTrans
     */
     public void setNumberOfTransactions(int nbOfTrans)
     { 
         numberOfTransactions = nbOfTrans;
     }

    /** 
     * Accessor method of Server class
     * 
     * @return numberOfAccounts
     * @param
     */
     public int getNumberOfAccounts()
     {
         return numberOfAccounts;
     }
         
    /** 
     * Mutator method of Server class
     * 
     * @return 
     * @param nbOfAcc
     */
     public void setNumberOfAccounts(int nbOfAcc)
     { 
         numberOfAccounts = nbOfAcc;
     }
         
     /** 
      * Accessor method of Server class
      * 
      * @return maxNbAccounts
      * @param
      */
      public int getmMxNbAccounts()
      {
          return maxNbAccounts;
      }
          
     /** 
      * Mutator method of Server class
      * 
      * @return 
      * @param nbOfAcc
      */
      public void setMaxNbAccounts(int nbOfAcc)
      { 
    	  maxNbAccounts = nbOfAcc;
      }

    /** 
     * Initialization of the accounts from an input file
     * 
     * @return 
     * @param
     */  
     public void initializeAccounts()
     {
        Scanner inputStream = null; /* accounts input file stream */
        int i = 0;                  /* index of accounts array */
        
        try
        {
         inputStream = new Scanner(new FileInputStream("src/account.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File account.txt was not found");
            System.out.println("or could not be opened.");
            System.exit(0);
        }
        while (inputStream.hasNextLine())
        {
            try
            {   account[i] = new Accounts();
                account[i].setAccountNumber(inputStream.next());    /* Read account number */
                account[i].setAccountType(inputStream.next());      /* Read account type */
                account[i].setFirstName(inputStream.next());        /* Read first name */
                account[i].setLastName(inputStream.next());         /* Read last name */
                account[i].setBalance(inputStream.nextDouble());    /* Read account balance */                
            }
            catch(InputMismatchException e)
            {
                System.out.println("Line " + i + "file account.txt invalid input");
                System.exit(0);
            }
            i++;
        }
        setNumberOfAccounts(i);			/* Record the number of accounts processed */
        
        //System.out.println("\n DEBUG : Server.initializeAccounts() " + getNumberOfAccounts() + " accounts processed");
        
        inputStream.close();
     }
         
    /** 
     * Find and return the index position of an account 
     * 
     * @return account index position or -1
     * @param accNumber
     */
    public int findAccount(String accNumber) {
        int i = 0;

        /* Find account */
        while (!(account[i].getAccountNumber().equals(accNumber)))
            i++;
        if (i == getNumberOfAccounts())
            return -1;
        else
            return i;
    }
     
    /** 
     * Processing of the transactions
     * 
     * @return 
     * @param trans
     */
     public boolean processTransactions(Transactions trans) {
         int accIndex;             	/* Index position of account to update */
         double newBalance; 		/* Updated account balance */

         /* Process the accounts until the client disconnects */
         while ((!objNetwork.getClientConnectionStatus().equals("disconnected")))
         {
        	 while( (objNetwork.getInBufferStatus().equals("empty"))){
                 if(objNetwork.getClientConnectionStatus().equals("disconnected")){
                     break;
                 }
                 Thread.yield();

             };   /* Alternatively, busy-wait until the network input buffer is available */

        	 if (!objNetwork.getInBufferStatus().equals("empty"))
        	 {
        		 //System.out.println("\n DEBUG : Server.processTransactions() - transferring in account " + trans.getAccountNumber());

                 try {
                     objNetwork.transferIn(trans);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }                                   /* Transfer a transaction from the network input buffer */

        		 accIndex = findAccount(trans.getAccountNumber());
        		 /* Process deposit operation */
        		 if (trans.getOperationType().equals("DEPOSIT"))
        		 {
        			 newBalance = deposit(accIndex, trans.getTransactionAmount());
        			 trans.setTransactionBalance(newBalance);
        			 trans.setTransactionStatus("done");

        			 //System.out.println("\n DEBUG : Server.processTransactions() - Deposit of " + trans.getTransactionAmount() + " in account " + trans.getAccountNumber());
        		 }
        		 else
        			 /* Process withdraw operation */
        			 if (trans.getOperationType().equals("WITHDRAW"))
        			 {

        				 newBalance = withdraw(accIndex, trans.getTransactionAmount());
        				 trans.setTransactionBalance(newBalance);
        				 trans.setTransactionStatus("done");
        				 //System.out.println("\n DEBUG : Server.processTransactions() - Withdrawal of " + trans.getTransactionAmount() + " from account " + trans.getAccountNumber());
        			 }
        			 else
        				 /* Process query operation */
        				 if (trans.getOperationType().equals("QUERY"))
        				 {
                             newBalance = query(accIndex);
                             trans.setTransactionBalance(newBalance);
                             trans.setTransactionStatus("done");
                            //System.out.println("\n DEBUG : Server.processTransactions() - Obtaining balance from account" + trans.getAccountNumber());
        				 }

        		 while( (objNetwork.getOutBufferStatus().equals("full"))){
                     Thread.yield();
                 }; /* Alternatively,  busy-wait until the network output buffer is available */

        		 //System.out.println("\n DEBUG : Server.processTransactions() - transferring out account " + trans.getAccountNumber());

                 try {
                     objNetwork.transferOut(trans);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }                            		/* Transfer a completed transaction from the server to the network output buffer */
        		 setNumberOfTransactions( (getNumberOfTransactions() +  1) ); 	/* Count the number of transactions processed */
        	 }
         }

         //System.out.println("\n DEBUG : Server.processTransactions() - " + getNumberOfTransactions() + " accounts updated");

         return true;
     }
         
    /** 
     * Processing of a deposit operation in an account
     * 
     * @return balance
     * @param i, amount
     */
     public double deposit(int i, double amount) {
         double curBalance;      /* Current account balance */
         synchronized (account[i]) {
             curBalance = account[i].getBalance();          /* Get current account balance */
             if (((i + 1) % 10 ) == 0)
             {
                 try {
                     Thread.sleep(100);
                 }
                 catch (InterruptedException e) {

                 }
             }
             account[i].setBalance(curBalance + amount);     /* Deposit amount in the account */
             return account[i].getBalance();
         }               /* Return updated account balance */
     }

    /**
     *  Processing of a withdrawal operation in an account
     *
     * @return balance
     * @param i, amount
     */
     public double withdraw(int i, double amount) {
         double curBalance;      /* Current account balance */
         synchronized (account[i]) {
             curBalance = account[i].getBalance();          /* Get current account balance */
             account[i].setBalance(curBalance - amount);     /* Withdraw amount in the account */
             return account[i].getBalance();
         }              /* Return updated account balance */
     }
    /**
     *  Processing of a query operation in an account
     *
     * @return balance
     * @param i
     */
     public double query(int i)  {
         double curBalance;      /* Current account balance */
         synchronized (account[i]) {
             curBalance = account[i].getBalance();          /* Get current account balance */
             /* NEW : A server thread is blocked before updating the 10th , 20th, ... 70th account balance in order to simulate an inconsistency situation */
             return curBalance;
         }                             /* Return current account balance */
     }

     /**
      *  Create a String representation based on the Server Object
     * 
     * @return String representation
     */
     public String toString() 
     {	
    	 return ("\n server IP " + objNetwork.getServerIP() + "connection status " + objNetwork.getServerConnectionStatus() + "Number of accounts " + getNumberOfAccounts());
     }

    /**
     * Code for the run method
     * 
     * @return 
     * @param
     */
    public void run()
    {
        switch(serverThreadId) {
            case "Thread1":
                serverThreadRunningStatus1 = "busy";
                break;
            case "Thread2":
                serverThreadRunningStatus2 = "busy";
                break;
            case "Thread3":
                serverThreadRunningStatus3 = "busy";
                break;
        }

        Transactions trans = new Transactions();
    	long serverStartTime = 0, serverEndTime = 0;
    	//System.out.println("\n DEBUG : Server.run() - starting server thread " + objNetwork.getServerConnectionStatus());
     	serverStartTime = System.currentTimeMillis();
        switch(serverThreadId) {
            case "Thread1":
                processTransactions(trans);
                serverThreadRunningStatus1 = "terminated";
                break;
            case "Thread2":
                processTransactions(trans);
                serverThreadRunningStatus2 = "terminated";
                break;
            case "Thread3":
                processTransactions(trans);
                serverThreadRunningStatus3 = "terminated";
                break;
        }
        serverEndTime = System.currentTimeMillis();
        System.out.println("\n Terminating server thread - " + serverThreadId + " Running time " + (serverEndTime - serverStartTime) + " milliseconds");
        if(serverThreadRunningStatus1.equals("terminated") && serverThreadRunningStatus2.equals("terminated") && (!thirdServerThread || serverThreadRunningStatus3.equals("terminated"))) {
            objNetwork.disconnect(objNetwork.getServerIP());
        }
    }
}

