package school.sptech.utils.Looca.cpu;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;

import school.sptech.utils.interfaces.Capturable;

public class Cpu implements Capturable {
    private final Processador processador = new Looca().getProcessador();

    @Override
    public Object execute() {
        return processador.getUso();
    }

    @Override
    public String getName(){
        return processador.getNome();
    }
}
