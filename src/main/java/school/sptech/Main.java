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

import school.sptech.database.dao.CaptureDao;
import school.sptech.database.dao.ComponentDao;
import school.sptech.database.dao.MetricDao;
import school.sptech.database.dao.ServerDao;
import school.sptech.model.ComponentWithType;
import school.sptech.model.Metric;
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
        CaptureDao captureDao = new CaptureDao();
        MetricDao metricDao = new MetricDao();


        Network net = new Network();
        Cpu cpu = new Cpu();
        Memory ram = new Memory();
        Disk disk = new Disk();

        int option = 0;

        String mac = net.getMacAddress();
        if(!net.verifyMacAddress(mac)){
            System.out.print("\033[H\033[2J");
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


            System.out.println("Quase pronto, agora você precisa configurar o seu servidor");
            System.out.println("Pressione enter para continuar");
            sc.nextLine();

            System.out.println("""
            Para que a captura funcione corretamente
            E necessario estabelecer metricas para cada componente
            """);

            System.out.println("Acima de quantos porcento de uso da CPU você deseja ser notificado?");
            Double cpuMetric = sc.nextDouble();
            sc.nextLine();

            System.out.println("Acima de quantos porcento de uso da RAM você deseja ser notificado?");
            Double ramMetric = sc.nextDouble();
            sc.nextLine();
            
            System.out.println("Acima de quantos porcento de uso do Disco você deseja ser notificado?");
            Double diskMetric = sc.nextDouble();
            sc.nextLine();

            metricDao.insertMetric(mac, ComponentEnum.CPU.getValue(), cpuMetric);

            metricDao.insertMetric(mac, ComponentEnum.RAM.getValue(), ramMetric);

            metricDao.insertMetric(mac, ComponentEnum.DISK.getValue(), diskMetric);
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
                        List<Metric> metrics = metricDao.getMetricByServer(mac);

                        Map<ComponentEnum, Boolean> mapComponents = components.stream().collect(
                                Collectors.toMap(
                                    ComponentWithType::getComponentType,
                                    ComponentWithType::isEnable
                                ));

                        Map<ComponentEnum, Double> mapMetrics = metrics.stream().collect(
                                Collectors.toMap(
                                    Metric::getType,
                                    Metric::getValue
                                ));

                        Double cpuValue = 0.0;
                        Double ramValue = 0.0;
                        Double diskValue = 0.0;

                        System.out.print("\033[H\033[2J");
                        if(mapComponents.get(ComponentEnum.CPU)){
                            cpuValue = (Double) cpu.execute();
                            if(cpuValue > mapMetrics.get(ComponentEnum.CPU)){
                                System.out.println("CPU: %f".formatted(cpuValue));
                            }
                        }
                        if(mapComponents.get(ComponentEnum.RAM)){
                            ramValue = (Double) ram.execute();
                            if(ramValue > mapMetrics.get(ComponentEnum.RAM)){
                                System.out.println("RAM: %f".formatted(ramValue));
                            }
                        }
                        if(mapComponents.get(ComponentEnum.DISK)){
                            diskValue = (Double) disk.execute();
                            if(diskValue > mapMetrics.get(ComponentEnum.DISK)){
                                System.out.println("DISK: %f".formatted(diskValue));
                            }
                        }
                        TerminalOptionPrinter.printCaptureData(cpuValue, ramValue, diskValue);

                        captureDao.insertCapture(mac, ComponentEnum.CPU.getValue(), cpuValue);
                        captureDao.insertCapture(mac, ComponentEnum.RAM.getValue(), ramValue);
                        captureDao.insertCapture(mac, ComponentEnum.DISK.getValue(), diskValue);
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