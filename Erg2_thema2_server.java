/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg2_thema2_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author timos
 */
public class Erg2_thema2_server {

       //port number
      
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        //initial server port
        int portNumber = 2222;
        
     ServerSocket serverSocket = null;
          ServerSocket serverSocket2 = null;

        Socket socket = null;
        

        
        try{
            
           //new serverSocket 
        serverSocket = new ServerSocket(portNumber); 
        
    }
    catch(IOException e){
    e.printStackTrace();
    System.out.println("Server error");

    }

        //run forever
    while(true){
        try{
            //if a client is connected
            socket= serverSocket.accept();
            if(socket.isConnected()){
                
                            System.out.println("connection Established");
            System.out.println("port is: " + socket.getPort());
            
                //create a new ServerPort, "0" for empty ports
                serverSocket2=new ServerSocket(0);
                  new ServerObject(socket,serverSocket2).doThings();
                  
                  
                  //close our basic port for some other client to connect
                  socket.close();
                  
                  //new thread to create a new session between client 
                  //and server with the new (random) port
                   ClientHandler clientHandler=new ClientHandler(serverSocket2);
                      clientHandler.start();
                 
                 
            }

        }
            catch(Exception e){
        e.printStackTrace();
        System.out.println("Connection Error");

    }
      
            

             
          
        
        
       
        

    }}
    
    
   
   
    
}
