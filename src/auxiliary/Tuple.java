package auxiliary;

/**
 * Created by mark on 18/02/17.
 */
public class Tuple<T, U> {
    private T t;
    private U u;
    
    public Tuple(T x1, U x2){
        this.t = x1;
        this.u = x2;
    }
    
    public T getX1() {
        return t;
    }
    
    public U getX2() {
        return u;
    }
}
