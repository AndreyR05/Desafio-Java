package school.sptech.utils.Looca.cpu;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;

import school.sptech.utils.interfaces.Executable;

public abstract class Cpu implements Executable {
    protected final Processador processor = new Looca().getProcessador();

}
