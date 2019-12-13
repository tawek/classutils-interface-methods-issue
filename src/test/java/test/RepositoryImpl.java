package test;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

public class RepositoryImpl implements Repository, E, F {

    Map<CompositeKey, Entity> store = new HashMap<CompositeKey, Entity>();

    @SneakyThrows
    public Entity find(CompositeKey key) {
        return store.get(key);
    }

    public boolean cacheEnabled(String type) {
        return true;
    }
}
