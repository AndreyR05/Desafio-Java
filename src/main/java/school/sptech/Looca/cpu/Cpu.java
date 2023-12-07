package school.sptech.Looca.cpu;

import com.github.britooo.looca.api.core.Looca;
import school.sptech.interfaces.Executable;
import com.github.britooo.looca.api.group.processador.Processador;

public abstract class Cpu implements Executable {
    protected final Processador processor = new Looca().getProcessador();

}
