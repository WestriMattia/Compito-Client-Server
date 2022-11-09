    package server;
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.io.PrintWriter;
    import java.net.Socket;
    
    public class Handler extends Thread {
        private Socket s;
        private PrintWriter pr = null;
        private BufferedReader br = null;
        private static int contatore;
        private static int biglietti;

        public Handler(Socket s, int c, int b, String nome) {
            this.s = s;
            contatore=c;
            biglietti=b;
            try {
                pr = new PrintWriter(s.getOutputStream(), true);
                br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        public void run() {
            pr.println("'A' ->  acquista biglietto 'D' -> richiesta disponibilità 'Q' -> disconnessione");
            try {
                while(true){
                    String str = br.readLine();
                    if(str.equals("D")){
                        disponibili(pr, biglietti);
                    }else if(str.equals("A")){
                        biglietti = acquista(pr, biglietti);

                    }else if(str.equals("Q")){
                        pr.println("Addio...");
                        s.close();
                    }else{
                        pr.println("Comando " + str + " non trovato");  
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void disponibili(PrintWriter pr, int biglietti) throws Exception{
            if(biglietti>0){
                pr.println("Sono disponibili " + biglietti + " biglietti");
            }else{
                pr.println("Biglietti esauriti");
            }
            return;
        }

        public static int acquista(PrintWriter pr, int biglietti) throws Exception{
            if(biglietti>0){
                biglietti=biglietti-1;
                pr.println("Biglietto acquistato");
            }else{
                pr.println("Biglietti esauriti");
            }
            return biglietti;
        }
    }