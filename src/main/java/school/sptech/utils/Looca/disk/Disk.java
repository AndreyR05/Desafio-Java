package school.sptech.utils.Looca.disk;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;

import school.sptech.utils.interfaces.Executable;

public class Disk implements Executable {
    DiscoGrupo discos = new Looca().getGrupoDeDiscos();

    @Override
    public Object execute() {
        return null;
    }

    public DiscoGrupo getDiscos(){
        return discos;
    }

}
