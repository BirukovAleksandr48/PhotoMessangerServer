import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class MesServer {
	static public ArrayList<Socket> sockets;
	static Scanner sc;
	static PrintWriter pw;
    static int port = 6666;
    
    public static void main(String[] ar) {
	    try {
			sockets = new ArrayList<>();
			ServerSocket ss = new ServerSocket(port);

			while(true) {
				Socket socket = ss.accept();
				System.out.println("Got a client");
				sockets.add(socket);
				
				new Thread(new MyThread(socket)).start();
			}
	   } catch (Exception e) {e.printStackTrace();}
   }
}