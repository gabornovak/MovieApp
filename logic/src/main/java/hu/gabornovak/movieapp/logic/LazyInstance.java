package hu.gabornovak.movieapp.logic;

public abstract class LazyInstance<T> {
    private T instance;

    public T getInstance() {
        if (instance == null) {
            synchronized (this) {
                if (instance == null) {
                    instance =  createInstance();
                }
            }
        }
        return instance;
    }

    protected abstract T createInstance();
}
