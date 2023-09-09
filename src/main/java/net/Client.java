package net;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++) {
            SimpleClient sc = new SimpleClient();
            sc.start();
        }
        
        
        sendRequest();
    }

    private static void sendRequest() throws IOException {

        Socket socket = new Socket("127.0.0.1", 25225);

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String sb = new String("Serhio");
        bw.write(sb);
        bw.newLine();
        bw.flush();

        String answer = br.readLine();
        System.out.println("Client got string: " + answer);

        br.close();
        bw.close();

        socket.close();
    }
}

class SimpleClient extends Thread {

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", 25225);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String sb = new String("Serhio");
            bw.write(sb);
            bw.newLine();
            bw.flush();

            String answer = br.readLine();
            System.out.println("Client got string: " + answer);

            br.close();
            bw.close();

        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}