/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.middleware;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.management.remote.rmi.RMIConnectorServer;

/**
 *
 * @author andres
 */
public class Front_End {
    public static void main (String args[])
    {
      if (args.length < 1) 
      {
        System.err.println("Usage: java EchoServer <port number>");
        System.exit(1);
      }
    
      int portNumber = Integer.parseInt(args[0]);
      boolean listening = true;
      try 
      (
        ServerSocket serverSocket = new ServerSocket(portNumber);
        
      )
      {
          
          String indice, inputline;
          while (listening)
          {
              
              Socket clientSocket = serverSocket.accept();
              PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
              BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
              out.println("ACK");
              
              indice = clientSocket.getInetAddress().toString();
              
              
              while ((inputline=in.readLine())!=null)
              {
                  
                  System.out.println(inputline);
                  File original = new File("registro.txt");
                  File cambiado = new File("registro_mod.txt");
                  PrintWriter writer = new PrintWriter(new FileWriter(original));
                  writer.println(indice);
                  writer.close();
                  
                  if (!(inputline.equals("estoy vivo")))
                  {
                       System.out.println("cerrado");
                       BufferedReader reader = new BufferedReader(new FileReader(original));
                       BufferedWriter esWriter = new BufferedWriter(new FileWriter(cambiado));
                       String remove = indice;
                       String currentline;
                       
                       while((currentline=reader.readLine())!=null)
                        {
                               String trimmed = currentline.toString().trim();
                               if(trimmed.equals(remove))continue;
                               esWriter.write(currentline + System.getProperty("line.separator"));
                               
                        }
                       esWriter.close();
                       reader.close();
                       if (!cambiado.renameTo(original))System.out.println("No es posible cambiar nombre");
                  }
                  
              }
              
          }
          
          
            
	} catch (Exception e) {
            e.getStackTrace();
       	}
    }
}
