
/**
 *
 * @author Willard
 */
import java.io.*;
import java.net.Socket;
import java.net.*;
         
class server {

	

    public static void main(String argv[]) throws Exception {
        String req_code=argv[0];
        int n_ports[]={4567,6666,7654,2333};
        String clientSentence;
        //construct TCP server socket
        ServerSocket tcpsocket =create(n_ports);
        System.out.println("SERVER_PORT="+tcpsocket.getLocalPort());
        while (true) {//wating cilent request 
			
            Socket connectionSocket = tcpsocket.accept();
            //get the req_code from cilent and check
            BufferedReader inFromClient
                    = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            clientSentence = inFromClient.readLine();
            if (!req_code.equals(clientSentence)) {
				System.out.println("detect a connection with incorrect req_code");
            } else {
				
                DataOutputStream outToClient
                        = new DataOutputStream(connectionSocket.getOutputStream());
                //construct UDP server socket at random port  
                DatagramSocket udpsocket = new DatagramSocket(0);
                //send the random port number to client
                outToClient.writeBytes(Integer.toString(udpsocket.getLocalPort())+'\n');
                
                byte[] receiveData = new byte[1024];
                byte[] sendData = new byte[1024];
                boolean finish = false;
                while (!finish) {//wating for UDP transation
					
					//get the string from cilent
                    DatagramPacket receivePacket
                            = new DatagramPacket(receiveData, receiveData.length);
                    udpsocket.receive(receivePacket);
                    String sentence = new String(
                            receivePacket.getData());
                    InetAddress IPAddress = receivePacket.getAddress();
                    int port = receivePacket.getPort();

                    if (sentence.length() > 1) {
						//reverse the string and send back
                        String rev = new StringBuffer(sentence).reverse().toString();
                        sendData = rev.getBytes();
                        DatagramPacket sendPacket
                                = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                        udpsocket.send(sendPacket);
                        finish = true;
                    }
                }
               
            }
  
        }
    }
    
    
    
    /**
	 * create(int[] ports)
	 * use the port numbers in ports create ServerSocket until success
	 * */
	public static ServerSocket create(int[] ports) throws IOException {
	    for (int port : ports) {
	        try {
	            return new ServerSocket(port);
	        } catch (IOException ex) {
	            continue; // try next port
	        }
	    }
    // if the program gets here, no port in the range was found
    throw new IOException("no free port found");
	}
     
}
