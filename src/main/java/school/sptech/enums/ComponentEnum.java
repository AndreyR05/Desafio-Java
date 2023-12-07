package school.sptech.enums;

public enum ComponentEnum {
    CPU(1, "CPU"),
    MEMORY(2, "Memory"),
    DISK(3, "Disk");

    private int value;
    private String name;

    ComponentEnum(int value, String name){
        this.value = value;
        this.name = name;
    }

    public int getValue(){
        return this.value;
    }

    public String getName(){
        return this.name;
    }
    
}