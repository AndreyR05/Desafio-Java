package school.sptech.model;

import school.sptech.enums.ComponentEnum;

public class ComponentWithType extends Component{
    private ComponentEnum componentType;

    public ComponentWithType() {}

    public ComponentEnum getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentEnum componentType) {
        this.componentType = componentType;
    }    
    
}
