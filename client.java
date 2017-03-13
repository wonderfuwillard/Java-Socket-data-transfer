import java.io.*;
import java.net.Socket;
import java.net.*;

class client {

    public static void main(String argv[]) throws Exception {
        int r_port;
        String server_address=argv[0];
        InetAddress address=InetAddress.getByName(server_address);
        int n_port=Integer.parseInt(argv[1]);
        String req_code=argv[2];
        String msg=argv[3];
        
        //start TCP connection
        BufferedReader tcpFromUser = new BufferedReader(
                new InputStreamReader(System.in));
        Socket tcpsocket = new Socket(address, n_port);
        DataOutputStream outToServer = new DataOutputStream(tcpsocket.getOutputStream());
        BufferedReader tcpFromServer
                = new BufferedReader(new InputStreamReader(tcpsocket.getInputStream()));
        //send the req_code
        outToServer.writeBytes(req_code+'\n');
        //read the r_port
        r_port = Integer.parseInt(tcpFromServer.readLine());
        tcpsocket.close();
		//TCP connection close
        
        //Start UPD connection
        BufferedReader udpFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket udpSocket = new DatagramSocket();
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        
        //copy the string to buffer and send to server
        sendData = msg.getBytes();
        DatagramPacket sendPacket
                = new DatagramPacket(sendData, sendData.length,address, r_port);
        udpSocket.send(sendPacket);
        
        //recieve the data from server
        DatagramPacket receivePacket
                = new DatagramPacket(receiveData,
                        receiveData.length);
        udpSocket.receive(receivePacket);
        
        //convert to string and print
        String modifiedSentence
                = new String(receivePacket.getData());
        System.out.println(modifiedSentence);
        
        udpSocket.close();
        //UPD connection close
    }
}
