package server;

import java.net.ServerSocket;
import java.net.Socket;

public class App{
  public static void main(String[] args) throws Exception {
    ServerSocket ss = new ServerSocket(3000);
    System.out.println("In ascolto sulla porta 3000");
    int c = 1;
    int b = 3;
    boolean running = true;
    while (running) {
      Socket s = ss.accept();
      System.out.println("Client n."+c+" connesso");
      Handler client = new Handler(s, b);
      c++;
      client.start();
    }
    ss.close();
  }
}
