class Netwrok extends Thread{
    String clientIP;/* IP of client application */
    String serverIP; /* IP of server application */
    int portID; /* port ID of client application */
    String clientConnectionStatus; /* connected, disconnected */
    String serverConnectionStatus; /* connected, disconnected */
    int maxNbPackets; /* capacity of network buffers */
    Transactions[10]; /* network input buffer */
    Transactions[10]; /* network output buffer */
    String inBufferStatus, outBufferStatus; /* normal, full, empty */
    int inputIndexClient, inputIndexServer, outputIndexServer, outputIndexClient; /* buffer index position */
    String networkStatus;

    
    public String getClientIP( ){
        
    }
    public String getServerIP( ){
        
    }
    public int getPortID( ){
        
    }
    public String getClientConnectionStatus( ){
        
    }
    public String getServerConnectionStatus( ){
        
    }
    public String getInBufferStatus( ) {
        
    }
    public String getOutBufferStatus( ){
        
    }
    public String getNetworkStatus( ){

    }
    public int getInputIndexClient( ){
        
    }
    public int getInputIndexServer( ){

    }
    public int getIOutputIndexClient( ){

    }
    public int getOutputIndexServer( ){

    }
    public void setClientIP(String cip){

    }
    public void setServerIP(String sip){
        
    }
    public void setPortID(int pid){
        
    }
    public void setClientConnectionStatus(String connectStatus){
        
    }
    public void setServerConnectionStatus(String connectStatus){
        
    }
    public void setNetworkStatus(String netStatus ){
        
    }
    public void setInBufferStatus(String inBufStatus){
        
    }
    public void setOutBufferStatus(String outBufStatus){
        
    }
    public void setInputIndexClient(int i1){
        
    }
    public void setInputIndexServer(int i2){
        
    }
    public void setOutputIndexClient(int o2){
        
    }
    public void setOutputIndexServer(int o1){
        
    }
    public boolean connect(String IP){
        
    }
    public booleandisconnect(String IP){
        
    }
    public booleansend(Transactions inPacket ){
        
    }
    public booleanreceive(Transactions outPacket){
        
    }
    public booleantransferOut(Transactions outPacket){
        
    }
    public booleantransferIn(Transactions inPacket){
        
    }
    public String toString( ){
        
    }
    public void run(){
        
    }
}
