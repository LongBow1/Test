package singleton;

public enum Elvis {

    INSTANVE(1,"2");
    private int key;
    private String value;

    Elvis(int key, String value){
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key+ ":" + value;
    }
}
