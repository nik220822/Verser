import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 51794;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    System.out.println("New connection accepted Port: " + clientSocket.getPort());
                    printWriter.println("Назовись!");
                    final String name = bufferedReader.readLine();
                    printWriter.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
                    System.out.println("Ответ клиента: " + bufferedReader.readLine());
                    printWriter.println(String.format("%s, Сколько тебе лет? ", name));
                    final int age = Integer.parseInt(bufferedReader.readLine());
                    System.out.println("Ответ клиента: " + age);
                    if (age >= 13) {
                        printWriter.println(String.format("%s, Ты уже не ребёнок. Ты нам подходишь ", name));
                    } else {
                        printWriter.println(String.format("%s, Ты слишком мал для этого, подожди %d лет ", name, (13 - age)));
                    }
                    printWriter.println(String.format("%s, Какой у тебя цвет кожи? ", name));
                    final String skinColor = bufferedReader.readLine();
                    System.out.println("Ответ клиента: " + skinColor);
                    if (Objects.equals(skinColor, "зелёный") || Objects.equals(skinColor, "Зелёный")) {
                        printWriter.println(String.format("Фу, %s, ты - орк! Иди прочь с нашего сервера.", name));
                    } else {
                        printWriter.println(String.format("Дорогой %s, этот вопрос был чистой формальностью, мы абсолютно " +
                                "толерантны к любому цвету кожи! Добро пожаловать на наш сервер!", name));
                    }
                }
            }
        }
    }
}
