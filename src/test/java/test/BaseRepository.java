package test;

public interface BaseRepository {

    Entity find(CompositeKey compositeKey);
}
