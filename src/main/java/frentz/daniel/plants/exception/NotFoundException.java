package frentz.daniel.plants.exception;

public class NotFoundException extends RuntimeException{
    private long id;
    private Class type;

    public NotFoundException(Class type, long id){
        this.type = type;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }
}
