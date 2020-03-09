/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erg2_thema2_server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author timos
 */
public class ServerObject {
   static File folder = new File("\\Users\\timos\\Documents\\eap\\plh47\\ergasies\\2\\html\\");

    
   static ServerSocket serverSocket;
   static Socket clientSocket;
    
    public ServerObject(Socket clientSocket,ServerSocket serverSocket) throws IOException{
        this.clientSocket = clientSocket;
        this.serverSocket=serverSocket;
    }
    
       public  void doThings() {
       
              
           BufferedReader in = null;
           
           PrintWriter out = null;
           try {
               //create output stream
               out = new PrintWriter(clientSocket.getOutputStream(), true);
           } catch (IOException ex) {
               Logger.getLogger(ServerObject.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           
           //send client a greeting message, the list of html files
           //+ the new port to make a session
           out.println(returnListHtmlFiles()+"port: " + serverSocket.getLocalPort());
           out.close();
               
   }
       
       public static String returnListHtmlFiles(){
      String filenames="";
       File[] listOfFiles = folder.listFiles();
       for (File file : listOfFiles) {
    if (file.isFile()) {
        //if filenames is empty don't add comma
        if(filenames.equals("")){
            filenames=file.getName();
        }
        else{ //add comma 
       filenames=filenames+","+file.getName();
        }
        System.out.println(file.getName());
    }

   }
       System.out.println("filenames: " + filenames);
       return filenames;
       
   }
  
   
       
}
