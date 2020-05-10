package rpc;

public class UserServiceImpl implements UserService {
    @Override
    public void sayUser(String name) {
        //erServiceImpl.class.newInstance()
        System.out.println(" Hello "+ name);
    }
}
