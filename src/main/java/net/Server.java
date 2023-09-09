package net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket socket = new ServerSocket(25225);

        System.out.println("Server is started.");
        while(true) {
            Socket client = socket.accept();
            new SimpleServer(client).start();
        }
    }
}

class SimpleServer extends Thread {

    private Socket client;

    public SimpleServer(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        handleRequest();
    }

    public void handleRequest() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            StringBuilder sb = new StringBuilder("Hello, ");
            String userName = String.valueOf(br.read());
            System.out.println("Server got string: " + userName);
            Thread.sleep(10000);

            sb.append(userName);
            bw.write(sb.toString());
            bw.newLine();
            bw.flush();

            br.close();
            bw.close();

            client.close();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}