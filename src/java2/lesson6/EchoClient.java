package java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;

    Thread listenerThread;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public EchoClient() {
        try {
            openConnection();
            startStreamReader();
            startStreamer();
            listenerThread.join();
            closeConnection();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

    }

    private void startStreamReader() {
        listenerThread = new Thread(() -> {
            try {
                while (true) {
                    String strFromServer = in.readUTF();
                    if (strFromServer.equalsIgnoreCase("/end")) {
                        closeConnection();
                        System.exit(0);
                        break;
                    }
                    System.out.println(strFromServer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        listenerThread.start();
    }

    public void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startStreamer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Клиентская часть приложения. Если здесь ввести сообщение, то оно отобразится в консоли серевера и вернется сюда в виде эха. " +
                "Разорвать соединение можно введя сообщение /end.");
        while (true) {
            String message = scanner.nextLine();
            if (!message.trim().isEmpty()) {
                try {
                    out.writeUTF(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new EchoClient();
    }

}