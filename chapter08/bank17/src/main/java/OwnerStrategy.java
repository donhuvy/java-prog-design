import java.io.Serializable;

public interface OwnerStrategy extends Serializable {
    boolean isForeign();

    String location();

    int fee();
}
