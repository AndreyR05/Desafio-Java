package school.sptech.terminal;

public class TerminalOptionPrinter {
    static public void printServerOptions(){
        System.out.println("""
        1 - Capturar
        2 - Configurar
        0 - Sair
        """);
    }

    static public void printConfiguration(){
        System.out.println("""
        1) Cpu -> Active
        2) Ram -> Active
        3) Disco -> Active

        Para alterar digite o numero, para mais de um valor use virgula 
        examplo: 1,2
        """);
    }
    
}
