import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyThread implements Runnable {
	private Socket socket;
	private Scanner sc;
	private PrintWriter pw;
	private String fromClient;
	
	public MyThread(Socket s) {
		this.socket = s;
	}
	
	public void run() {
		try {
			sc = new Scanner(socket.getInputStream());
			pw = new PrintWriter(socket.getOutputStream());
			String message;
			while(true) {
				try{
					message = sc.nextLine();
					
					System.out.println("got a message:" + String.valueOf(message.length()));
					
					for(Socket socket : MesServer.sockets) {
						if(socket.isConnected()) {
							pw = new PrintWriter(socket.getOutputStream());
							pw.write(message);
							pw.flush();
						}
					}
				}catch(Exception e){
					if(socket.isClosed()){
						System.out.println("Disconect");
						return;
					}
				}
			}
		} catch (IOException e){e.printStackTrace();}
	}	
} 