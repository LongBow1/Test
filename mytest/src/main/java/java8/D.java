package java8;

public interface D {
    default void name(){
        System.out.println("name D");
    }

    default void hello(){
        System.out.println("hello D");
    }
}
