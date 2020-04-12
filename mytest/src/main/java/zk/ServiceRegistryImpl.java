package zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.io.IOException;

public class ServiceRegistryImpl implements IServiceRegistry {

    private static final String REGISTRY_ROOT= "/register";
    private CuratorFramework curatorFramework = null;
    {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("10.176.128.206:2181")
                .retryPolicy(new ExponentialBackoffRetry(1000,10))
                .build();
        //启动远程连接
        curatorFramework.start();
        System.out.println("启动远程连接");
    }
    @Override
    public void registry(String serviceName, String serviceAddress) {
        //服务注册
        String servicePath = REGISTRY_ROOT+"/"+serviceName;
        try {
            if(curatorFramework.checkExists().forPath(servicePath) == null){
                curatorFramework.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT)
                        .forPath(servicePath,"0".getBytes());
            }
            String addressPath = servicePath+"/"+serviceAddress;
            String rs = curatorFramework.create().withMode(CreateMode.EPHEMERAL)
                    .forPath(addressPath,"0".getBytes());
            System.out.println("服务注册成功:"+rs);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        IServiceRegistry serviceRegistry = new ServiceRegistryImpl();
        //启动多个服务
        serviceRegistry.registry("order-service","10.176.128.206:8080");
        //serviceRegistry.registry("order-service","10.176.128.12:8080");
        System.in.read();
    }
}
