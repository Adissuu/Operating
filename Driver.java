
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @authors Ali Fetanat (40158208), Gabriel Dubois (40209252), Kerly Titus
 */
public class Driver {

    /** 
     * main class
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    	 /*******************************************************************************************************************************************
    	  * TODO : implement all the operations of main class   																					*
    	  ******************************************************************************************************************************************/
        
    	Network objNetwork = new Network("network");           /* Activate the network */
        objNetwork.start();
        Client objsender = new Client("sending");
        objsender.start();
        Server objServer = new Server();                    /* Initializing the server */
        objServer.start();                               
        Client objreceiver = new Client("receiving");
        objreceiver.start();

        try{
        objNetwork.join();
        objsender.join();
        objServer.join();
        objreceiver.join();
        }catch(InterruptedException e){}
        /* Complete here the code for the main method ...*/
        
    }
}
