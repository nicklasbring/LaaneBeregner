package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8000;
        DataInputStream input;
        DataOutputStream output;
        ServerSocket server;
        Socket socket;

        try {
            server = new ServerSocket(port);
            System.out.println("Serveren virker!");
            socket = server.accept();
            System.out.println("Klienten er tilsluttet");

            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            System.out.println("Streams oprettet");

            double renteProcent = input.readDouble() / 100;
            int antalAar = input.readInt() * 12;
            double beloeb = input.readDouble();
            System.out.println("Tal modtaget");

            double monthly = monthlyPayment(renteProcent, antalAar, beloeb);
            double total = monthly * antalAar;

            output.writeDouble(monthly);
            output.writeDouble(total);
            output.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static double monthlyPayment(double rentesats, int antalMaeneder, double beloeb){
        double monthly = beloeb * (rentesats * Math.pow(1 + rentesats, antalMaeneder))
                / (Math.pow(1 + rentesats, antalMaeneder) - 1);

        return monthly;

    }

}