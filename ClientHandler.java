/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg2_thema2_server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author timos
 */
public class ClientHandler extends Thread{
    
             static File folder = new File("\\Users\\timos\\Documents\\eap\\plh47\\ergasies\\2\\html\\");

    
    static ServerSocket serverSocket;
   static Socket clientSocket;
    
    public ClientHandler(ServerSocket serverSocket) throws IOException{
     this.serverSocket=serverSocket;
    }
    
       public  void run() {
          
           while(true){
               try {
                   
                   //connection is created
                   clientSocket=serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 
                 //if connected
                   if(clientSocket.isConnected()){
                        String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if(inputLine.equals("exit")){
                    out.close();
                    System.out.println("Connection closed");
                    break;  
                }
                System.out.println("name send is: "+ inputLine);
                
                //getfile
               File myFile=getFile(inputLine);
               System.out.println("File is: " + myFile.getName()+" size is: "+myFile.length());
                        
                    //create bytearray with length of the file
                     byte[] mybytearray = new byte[(int) myFile.length()];
                     //putfile in bufferinput
      BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
      //copy to mybytearray
      bis.read(mybytearray, 0, mybytearray.length);
      //get outputstream of port
      OutputStream os = clientSocket.getOutputStream();
      //send
      os.write(mybytearray, 0, mybytearray.length);
      //flush
      os.flush();


                   }
            break;
               }
               }
               
               catch (IOException ex) {
                   System.out.println("OOOOPS");
                   Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
               }
               }
                   
                  
                  
                
             
            }
                    
                   
               
           
       
       
       
        public static File getFile(String s) throws FileNotFoundException, IOException{
       
            
        
        
        
            System.out.println("looking for: " + s);
       //pare ta arxeia
       File[] listOfFiles = folder.listFiles();
       for (File file : listOfFiles) {
    if (file.isFile()) {
        
        //ean brikes to arxeio me bash ton titlo
        if(file.getName().equals(s)){
            //kane return to arxeio
            System.out.println("found file: " + s );
           return file;
        }
       
   }
           
    
}
        
        
       return null;
   }
}
