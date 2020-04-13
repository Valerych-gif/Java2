package java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    public static void main(String[] args) {
        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Серверная часть приложения. Если здесь ввести сообщение, то оно отбразится в консоли клиента. " +
                    "Разорвать соединение можно только со стороны клиента, введя сообщение /end.");
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            new Thread(()->{
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String message = scanner.nextLine();
                    if (!message.trim().isEmpty()){
                        try {
                            out.writeUTF("Сообщение от сервера: " + message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            while (true) {
                String str = in.readUTF();
                if (str.equals("/end")) {
                    out.writeUTF("/end");
                    System.exit(0);
                    break;
                }
                System.out.println(str);
                out.writeUTF("Эхо: " + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
