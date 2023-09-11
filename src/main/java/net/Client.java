package net;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class Client {

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 4; i++) {
            SimpleClient sc = new SimpleClient(i);
            sc.start();
        }
    }
}

class SimpleClient extends Thread {

    private final static String[] COMMAND = {
            "HELLO", "MORNING", "DAY", "EVENING" };

    private int cmdNumber;

    public SimpleClient(int cmdNumber) {
        this.cmdNumber = cmdNumber;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", 25225);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String command = COMMAND[cmdNumber % COMMAND.length];
            String sb = command + " " + "Serhio";
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