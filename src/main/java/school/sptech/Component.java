package school.sptech;

public class Component {
    private Integer idComponent;
    private boolean enable;
    private Integer fkComponentType;
    private Integer fkServer;

    public Component() {}

    public Integer getIdComponent() {
        return idComponent;
    }

    public Integer getFkComponentType() {
        return fkComponentType;
    }

    public int getFkServer() {
        return fkServer;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setIdComponent(Integer idComponent) {
        this.idComponent = idComponent;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setFkComponentType(Integer fkComponentType) {
        this.fkComponentType = fkComponentType;
    }

    public void setFkServer(Integer fkServer) {
        this.fkServer = fkServer;
    }

}