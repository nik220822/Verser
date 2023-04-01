import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 51794;
        try (Socket socket = new Socket(host, port);
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println(bufferedReader.readLine());
            printWriter.println("Nikolai Pavlov");
            System.out.println(bufferedReader.readLine());
            printWriter.println("Рад, что есть контакт!");
            System.out.println(bufferedReader.readLine());
            printWriter.println("15");
            System.out.println(bufferedReader.readLine());
            printWriter.println("зелёный");
            System.out.println(bufferedReader.readLine());
            System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
