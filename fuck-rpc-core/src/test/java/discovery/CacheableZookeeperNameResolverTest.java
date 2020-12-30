package discovery;

import com.shihang.fuck.rpc.discovery.CacheableZookeeperNameResolver;
import org.junit.Test;

public class CacheableZookeeperNameResolverTest {
    @Test
    public void test1() {
        CacheableZookeeperNameResolver cacheableZookeeperNameResolver = new CacheableZookeeperNameResolver("192.250.110.16:2181");
        System.out.println(cacheableZookeeperNameResolver.resolve("dwzq_portfolio.updateConcernedQuantity"));
    }
}