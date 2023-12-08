package school.sptech.utils.Looca.memory;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;

import school.sptech.utils.interfaces.Executable;

public class Memory implements Executable {
    Memoria ram = new Looca().getMemoria();

    @Override
    public Object execute() {
        return null;
    }

    public Memoria getRam(){
        return ram;
    }
}
