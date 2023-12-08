package school.sptech;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;

import school.sptech.database.dao.ComponentDao;
import school.sptech.database.dao.ServerDao;
import school.sptech.model.ComponentWithType;
import school.sptech.terminal.TerminalOptionPrinter;
import school.sptech.utils.Looca.cpu.Cpu;
import school.sptech.utils.Looca.disk.Disk;
import school.sptech.utils.Looca.memory.Memory;
import school.sptech.utils.Looca.network.Network;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ServerDao serverDao = new ServerDao();
        ComponentDao componentDao = new ComponentDao();

        Network net = new Network();
        Cpu cpu = new Cpu();
        Memory ram = new Memory();
        Disk disk = new Disk();

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

            Processador serverCpu = cpu.getProcessador();
            componentDao.insertComponent(serverCpu.getNome(),mac, 1);

            Memoria serverRam = ram.getRam();
            componentDao.insertComponent("RAM "+serverRam.getTotal(),mac, 2);
            

            DiscoGrupo serverDisk = disk.getDiscos();
            String mainDisk = serverDisk.getDiscos().get(0).getModelo();
            componentDao.insertComponent(mainDisk, mac, 3);
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
                    if (choice == 0) break;
                    if (choice > 3){
                        System.out.println("A opção %d não existe, ela será ignorada".formatted(choice));
                        continue;
                    }

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