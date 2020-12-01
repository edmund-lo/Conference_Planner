package adapter;

import java.util.List;

public interface Adapter<T> {
    List<T> adaptResult(List<String[]> data);
}
