package test;

import org.springframework.cache.annotation.Cacheable;

public interface Repository extends BaseRepository {

    @Cacheable(cacheNames = "default", key = "{#a0.type,#a0.id}", condition = "#root.target.cacheEnabled(#a0.type)")
    Entity find(CompositeKey e);

}
