package school.sptech.Looca.network;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;

import school.sptech.dao.ServerDao;


public class Network {
    ServerDao serverDao = new ServerDao();
    private Rede rede = new Looca().getRede(); 
    
    public Boolean verifyMacAddress(String mac){
        if(serverDao.searchServerByMac(mac).isEmpty()){
            return false;
        }
        return true;
    }

    public String getMacAddress(){
        return rede.getGrupoDeInterfaces().getInterfaces().get(0).getEnderecoMac();
    }
}