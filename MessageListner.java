/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatapp.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAIYAMONDO_NTHN
 */
public class MessageListner extends  Thread{
    ServerSocket serverSocket;
    int port=8877;
    WritableGUI gui;
    public  MessageListner(WritableGUI gui,int port){
        this.port=port;
        this.gui=gui;
        try {
            serverSocket=new ServerSocket(this.port);
        } catch (IOException ex) {
            Logger.getLogger(MessageListner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public  MessageListner(){
        try {
            serverSocket=new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(MessageListner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        Socket clientsocket;
        try {
            while((clientsocket=serverSocket.accept())!=null){
                InputStream is=clientsocket.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String line=br.readLine();
                if(line!=null){
                    gui.write(line);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MessageListner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
