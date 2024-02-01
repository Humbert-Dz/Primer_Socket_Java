//Importaciones necesarias

import java.io.*;
import java.net.*;
import java.util.Scanner;


void main() {

    //declaracion de arreglo con la ip del servidor Echo de internet
    byte[] byteIp = {52, 43, 121, 77};

    // numero de puerto del servidor
    int puerto = 9001;

    //objeto de tipo InetAddress
    InetAddress ip = null;
    Socket socket = null;
    BufferedReader sockInput = null;
    PrintWriter socketOuput = null;

    //manejar la posible excepcion que arroja el metodo getByAddress
    try {
        // obteniendo un objeto de tipo InetByAddress
        ip = InetAddress.getByAddress(byteIp);
    } catch (UnknownHostException e) {
        e.printStackTrace(System.out);
    }

    //manejar la posble excepcion que arroja el constructor Socket
    try {
        // creando un socket a partir de la direccion ip
        socket = new Socket(ip, puerto);

    } catch (IOException e) {
        e.printStackTrace(System.out);
    }

    //manejar las posibles excepciones que arrojan los metodos Input y Output Stream
    try {
        // objeto que permite leer la respuesta del servidor
        sockInput = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        //objeto que permite enviar algo al servidor
        socketOuput = new PrintWriter(socket.getOutputStream(), true);
    } catch (IOException e) {
        e.printStackTrace(System.out);
    }

    //objeto Scanner para leer la consola
    Scanner consola = new Scanner(System.in);

    String lecturaDeConsola;
    System.out.println("Escribe lo que sea! ");

    while (true) {

        //mostrar la respuesta del servidor
        try {
            System.out.println(sockInput.readLine());
            lecturaDeConsola = consola.nextLine();
            if (lecturaDeConsola.isBlank()) {
                System.out.print("Ya no escribiste nada, ejercicio finalizado!");
                break;
            }
            socketOuput.println(lecturaDeConsola);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}