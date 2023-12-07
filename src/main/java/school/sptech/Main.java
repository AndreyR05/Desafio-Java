package school.sptech;

import java.util.Scanner;


import school.sptech.Looca.network.Network;
import school.sptech.Terminal.TerminalOptionPrinter;
import school.sptech.serverDao.ServerDao;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ServerDao serverDao = new ServerDao();
        Network net = new Network();
        int option = 0;


        String mac = net.getMacAddress();
        if(!net.verifyMacAddress(mac)){
            String name;
            System.out.println("O seu servidor aindo não está cadastrado no sistema");
            do {
                System.out.println("Digite o nome do servidor: ");
                name = sc.nextLine();
            } while (name.isEmpty());

            serverDao.insertServer(mac, name);
        }


        do {
            TerminalOptionPrinter.printServerOptions();
            option = sc.nextInt();
            sc.nextLine();

            if(option == 1){
                // TODO: when select get data of hardware
            }
            else if(option == 2){
                TerminalOptionPrinter.printConfiguration();
                String config  = sc.nextLine();
                String[] configChoices = config.split(",");

                for (String string : configChoices) {
                    // TODO: logic to update configuration
                }
            }
            
        } while (option != 0);

        sc.close();

    }
}