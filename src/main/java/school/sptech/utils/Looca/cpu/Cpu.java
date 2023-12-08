package school.sptech.utils.Looca.cpu;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;

import school.sptech.utils.interfaces.Executable;

public class Cpu implements Executable {
    private final Processador processor = new Looca().getProcessador();

    @Override
    public Object execute() {
        return null;
    }

    public Processador getProcessador(){
        return processor;
    }
}
