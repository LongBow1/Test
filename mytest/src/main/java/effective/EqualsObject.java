package effective;

public class EqualsObject {
    private int id;
    private String name;
    public EqualsObject(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || this.getClass() != obj.getClass()){
            return false;
        }

        if(this == obj){
            return true;
        }
        EqualsObject temp = (EqualsObject)obj;
        if(temp.getId() == this.id && name.equals(temp.name)){
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return id+name.hashCode();
    }
}
