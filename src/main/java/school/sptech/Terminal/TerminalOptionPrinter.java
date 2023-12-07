package school.sptech.Terminal;

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
        Cpu ------------------------
            1- |x| Temperatura
            2- | | Uso em Porcentagem

        Ram ------------------------
            3- | | Uso em Porcentagem
            4- |x| Uso em Gigabyte

        Disco -----------------------
            5- | | Uso em Porcentagem
            6- |x| Uso em Gigabyte

        Para alterar digite o numero, para mais de um valor use virgula 
        examplo: 1,2
        """);
    }
    
}
