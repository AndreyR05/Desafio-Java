package school.sptech;

import java.util.Scanner;

import school.sptech.Terminal.TerminalOptionPrinter;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = 0;

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
            
        } while (option == 0);

        sc.close();

    }
}