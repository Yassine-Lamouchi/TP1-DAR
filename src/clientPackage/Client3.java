package clientPackage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client3 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Calculatrice ===");
        System.out.println("1. Addition");
        System.out.println("2. Soustraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.print("Votre choix : ");
        int choix = sc.nextInt();

        System.out.print("Entrez le premier nombre : ");
        double a = sc.nextDouble();
        System.out.print("Entrez le deuxième nombre : ");
        double b = sc.nextDouble();

        String ipServeur = "192.168.56.1";

        Socket socket = new Socket(ipServeur, 1234);
        System.out.println("Connecté au serveur " + ipServeur);

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        dos.writeInt(choix);
        dos.writeDouble(a);
        dos.writeDouble(b);

        boolean ok = dis.readBoolean();
        if (ok) {
            double res = dis.readDouble();
            System.out.println("Résultat = " + res);
        } else {
            System.out.println("Erreur : division par zéro !");
        }

        socket.close();
        sc.close();
    }
}