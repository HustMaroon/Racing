/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Administrator
 */
import java.net.*;
/**
 *
 * @author Administrator
 */
class Server {
    public Server() throws Exception{
       DatagramSocket serverSocket = new DatagramSocket(9876);
       byte[] receiveData = new byte[1024];
       while(true){
           DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
           serverSocket.receive(receivePacket);
           String sentence = new String(receivePacket.getData());
           InetAddress IPAddress = receivePacket.getAddress();
           int port = receivePacket.getPort();
       }
    }
}
