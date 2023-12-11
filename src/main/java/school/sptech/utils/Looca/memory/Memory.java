package school.sptech.utils.Looca.memory;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;

import school.sptech.utils.interfaces.Executable;

public class Memory implements Executable {
    Memoria ram = new Looca().getMemoria();

    @Override
    public Object execute() {
        Double percent = (double) ram.getEmUso() / (double) ram.getTotal() * 100;
        return percent;
    }

    public Memoria getRam(){
        return ram;
    }
}
