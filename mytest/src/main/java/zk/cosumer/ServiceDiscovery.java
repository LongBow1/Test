package zk.cosumer;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class ServiceDiscovery {
    private static final String REGISTRY_ROOT= "/register";
    @Autowired
    @Resource
    List<String> serviceRepos = new ArrayList();
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

    public void init(String serviceName) throws Exception {
        String path = REGISTRY_ROOT+"/"+serviceName;
        try {
            serviceRepos = curatorFramework.getChildren().forPath(path);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        //动态感知服务变化
        registWatch(path);
    }

    private void registWatch(String path) throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,path,true);
        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("目标服务地址变化");
                serviceRepos = curatorFramework.getChildren().forPath(path);
            }
        };

        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start();
    }

    public static void main(String[] args) throws Exception {
        ServiceDiscovery serviceDiscovery = new ServiceDiscovery();
        serviceDiscovery.init("order-service");

    }
}
