package school.sptech.utils.Looca.disk;

import java.util.List;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;

import school.sptech.utils.interfaces.Executable;

public class Disk implements Executable {
    DiscoGrupo discos = new Looca().getGrupoDeDiscos();

    @Override
    public Object execute() {
        Double inUse = (double)(discos.getVolumes().get(0).getTotal()-discos.getVolumes().get(0).getDisponivel());
        return inUse / (double)discos.getVolumes().get(0).getTotal() * 100;
    }

    public List<Disco> getDiscos(){
        return discos.getDiscos();
    }

}
