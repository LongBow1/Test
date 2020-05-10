package java8;

public interface B extends A{
    default void name(){
        System.out.println("name B");
    }
}
