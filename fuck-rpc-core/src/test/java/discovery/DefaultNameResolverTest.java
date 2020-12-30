package discovery;

import com.shihang.fuck.rpc.discovery.DefaultNameResolver;
import org.junit.Test;

public class DefaultNameResolverTest {
    @Test
    public void test1() {
        DefaultNameResolver defaultNameResolver = new DefaultNameResolver();
        System.out.println(defaultNameResolver.resolve("test:80"));
        System.out.println(defaultNameResolver.resolve("test:8080"));
        System.out.println(defaultNameResolver.resolve("test:18080"));
        System.out.println(defaultNameResolver.resolve("test"));
    }
}