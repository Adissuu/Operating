package A2;
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

        Network objNetwork = new Network("network");            /* Activate the network */
        objNetwork.start();

        Server objServer1 = new Server("Thread1");		/* Start the server thread1 */
        objServer1.start();
        Server objServer2 = new Server("Thread2");		/* Start the server thread2 */
        objServer2.start();
        if(Server.thirdServerThread) {
            Server objServer3 = new Server("Thread3");		/* Start the server thread3 */
            objServer3.start();
        }

        Client objClient1 = new Client("sending");          /* Start the sending client thread */
        objClient1.start();
        Client objClient2 = new Client("receiving");        /* Start the receiving client thread */
        objClient2.start();

    }

}

