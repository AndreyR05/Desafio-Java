package school.sptech;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.memoria.Memoria;

import school.sptech.database.dao.ComponentDao;
import school.sptech.database.dao.ServerDao;
import school.sptech.model.ComponentWithType;
import school.sptech.terminal.TerminalOptionPrinter;
import school.sptech.utils.Looca.cpu.Cpu;
import school.sptech.utils.Looca.disk.Disk;
import school.sptech.utils.Looca.memory.Memory;
import school.sptech.utils.Looca.network.Network;
import school.sptech.utils.enums.ComponentEnum;

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

            componentDao.insertComponent(cpu.getName(),mac, 1);

            Memoria serverRam = ram.getRam();
            componentDao.insertComponent("RAM "+serverRam.getTotal(),mac, 2);

            List<Disco> serverDisk = disk.getDiscos();
            String mainDisk = serverDisk.get(0).getModelo();
            componentDao.insertComponent(mainDisk, mac, 3);
        }


        do {
            System.out.print("\033[H\033[2J");

            TerminalOptionPrinter.printServerOptions();
            option = sc.nextInt();
            sc.nextLine();

            if(option == 1){
                System.out.print("\033[H\033[2J");

                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        List<ComponentWithType> components = componentDao.searchComponentByMac(mac);

                        Map<ComponentEnum, Boolean> mapComponents = components.stream().collect(
                                Collectors.toMap(
                                    ComponentWithType::getComponentType,
                                    ComponentWithType::isEnable
                                ));

                        Double cpuValue = 0.0;
                        Double ramValue = 0.0;
                        Double diskValue = 0.0;

                        System.out.print("\033[H\033[2J");
                        if(mapComponents.get(ComponentEnum.CPU)){
                            cpuValue = (Double) cpu.execute();
                        }
                        if(mapComponents.get(ComponentEnum.RAM)){
                            ramValue = (Double) ram.execute();
                        }
                        if(mapComponents.get(ComponentEnum.DISK)){
                            diskValue = (Double) disk.execute();
                        }
                        TerminalOptionPrinter.printCaptureData(cpuValue, ramValue, diskValue);
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 0, 10000);

                sc.nextLine();
                timer.cancel();
            }
            else if(option == 2){
                System.out.print("\033[H\033[2J");
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