package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class CacheProblemDemo {

    @Configuration
    @EnableCaching
    public static class Config {

        @Bean
        public Repository repository() {
            return new RepositoryImpl();
        }

        @Bean
        public CacheManager cacheManager() {
            return new ConcurrentMapCacheManager("default");
        }


    }

    @Autowired
    Repository rep;

    @Test
    public void testFind() {
        for (int k = 0; k < 40; k++) {
            nest(200, () -> {
                long s = System.nanoTime();
                for (int i = 0; i < 10000; i++) {
                    rep.find(CompositeKey.builder()
                            .id(UUID.randomUUID().toString())
                            .type("type")
                            .build());
                }
                long e = System.nanoTime();
                System.out.println("Overhead: " + (e - s) / 1000 / 10000 + "[us] per call, " + 1E9F * 10000 / (e - s) + " calls per second");
            });
        }
    }

    private void nest(int depth, Runnable r) {
        if (depth > 0) {
            nest(depth - 1, r);
        } else {
            r.run();
        }
    }
}
