package client;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class App {
    public static void main(String[] args) throws Exception {
        try(Socket s = new Socket("localhost", 3000)){
            PrintWriter pr = new PrintWriter(s.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                System.out.println(br.readLine());
                pr.println(tastiera.readLine()); 
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
