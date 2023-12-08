package school.sptech;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import school.sptech.database.dao.ComponentDao;
import school.sptech.database.dao.ServerDao;
import school.sptech.model.ComponentWithType;
import school.sptech.terminal.TerminalOptionPrinter;
import school.sptech.utils.Looca.network.Network;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ServerDao serverDao = new ServerDao();
        ComponentDao componentDao = new ComponentDao();
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
                List<ComponentWithType> components = componentDao.searchComponentByMac(mac);

                TerminalOptionPrinter.printConfiguration(components);
                String config  = sc.nextLine();

                List<Integer> configChoicesInt = 
                        List.of(config.split(","))
                        .stream()
                        .map(Integer::parseInt)
                        .toList();

                for (Integer choice : configChoicesInt) {
                    Optional<ComponentWithType> component = components.stream()
                        .filter(comp -> comp.getFkComponentType().equals(choice))
                        .findFirst();

                    if(component.isPresent()){
                        componentDao.toggleComponent(mac, !component.get().isEnable(), choice);
                    }
                }
            }
            
        } while (option != 0);

        sc.close();

    }
}