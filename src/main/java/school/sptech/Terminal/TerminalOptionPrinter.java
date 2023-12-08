package school.sptech.terminal;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import school.sptech.model.ComponentWithType;
import school.sptech.utils.enums.ComponentEnum;

public class TerminalOptionPrinter {
    static public void printServerOptions(){
        System.out.println("""
        1 - Capturar
        2 - Configurar
        0 - Sair
        """);
    }

    static public void printConfiguration(List<ComponentWithType> components){
        Map<ComponentEnum, String> map = components.stream()
            .collect(Collectors.toMap(
                ComponentWithType::getComponentType,
                comp -> comp.isEnable() ? "Ativo" : "Inativo"
            ));


        System.out.println("""
        1) Cpu -> %s
        2) Ram -> %s
        3) Disco -> %s

        Para alterar digite o numero, para mais de um valor use virgula 
        examplo: 1,2
        """.formatted(
                map.get(ComponentEnum.CPU),
                map.get(ComponentEnum.RAM),
                map.get(ComponentEnum.DISK)
            )
        );
    }
    
}
