package net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket socket = new ServerSocket(25225);

        Map<String, Greatable> handlers = loadHandlers();

        System.out.println("Server is started.");
        while(true) {
            Socket client = socket.accept();
            new SimpleServer(client, handlers).start();
        }
    }

    private static Map<String, Greatable> loadHandlers() {
        Map<String, Greatable> result = new HashMap<>();

        try(InputStream is = Server.class.getClassLoader().getResourceAsStream("server.properties")){

            Properties properties = new Properties();
            properties.load(is);

            for(Object command : properties.keySet()) {
                String className = properties.getProperty(command.toString());
                Class<Greatable> cl = (Class<Greatable>) Class.forName(className);

               Greatable handler = cl.getConstructor().newInstance();
               result.put(command.toString(), handler);
            }

        } catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return result;
    }
}

class SimpleServer extends Thread {

    private Socket client;
    private Map<String, Greatable> handlers;

    public SimpleServer(Socket client, Map<String, Greatable> handlers) {
        this.client = client;
        this.handlers = handlers;
    }

    @Override
    public void run() {
        handleRequest();
    }

    public void handleRequest() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            String request = br.readLine();
            String[] lines = request.split("\\s+");
            String command = lines[0];
            String userName = lines[1];

            System.out.println("Server got string 1: " + command);
            System.out.println("Server got string 2: " + userName);
//            Thread.sleep(10000);

            String response = buildResponse(command, userName);
            bw.write(response);
            bw.newLine();
            bw.flush();

            br.close();
            bw.close();

            client.close();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    private String buildResponse(String command, String userName) {
       Greatable handler = handlers.get(command);
       if(handler != null) {
           return handler.buildResponse(userName);
       }
       return "Hello, " + userName;
    }
}