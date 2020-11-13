import java.io.*;
import java.util.List;

public interface GatewayInterface<T> extends Serializable {
    /**
     * This is a gateway interface that interacts with all other gateway classes
     * GatewayInterface has a static final variable directoryName and extends Serializable
     * There are two methods, serializeData which serializes the inputted parameter
     * using Serializable and returns none.
     * Method deserializeData unpacks a given serialized parameter producing
     * an object of List<T>
     */

    //group_0143/phase1/src

    String directoryName = "https://markus.teach.cs.toronto.edu/git/csc207-2020-09/group_0143/phase1/src";
    void serializeData (Object T) throws FileNotFoundException;
    abstract T deserializeData();

}


