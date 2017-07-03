/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.middleware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author joselimaico
 */
public class BackEnd {

    public static void main(String[] args) throws IOException, InterruptedException {

        if (args.length != 2) {
            System.err.println(
                    "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }
        Timer t = new Timer();
        
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        try (
                Socket beSocket = new Socket(hostName, portNumber);
                PrintWriter out
                = new PrintWriter(beSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(beSocket.getInputStream()));) {
            
        
                
            String fromServer;
            String ack;    
            
             while ((fromServer = in.readLine()) != null) {
               t.scheduleAtFixedRate(new TimerTask() {
    @Override
    public void run() {
        out.println("estoy vivo :D");
                 //System.out.println("Server: " + fromServer);
    }
}, 0, 1000);
                //if (fromServer.equals("Bye."))
                //    break;
                 
                 
                
            }
        } 
        
        catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        }catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }

    }
}
