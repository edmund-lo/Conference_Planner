package Gateways;

import java.io.*;


public interface GatewayInterface<T> extends Serializable {
    /**
     * This is a gateway interface that interacts with all other gateway classes
     * Gateways.GatewayInterface has a static final variable directoryName and extends Serializable
     * There are two methods, serializeData which serializes the inputted parameter
     * using Serializable and returns none.
     * Method deserializeData unpacks a given serialized parameter producing
     * an object of List<T>
     */

    //group_0143/phase1/src

    String directoryName = "https://markus.teach.cs.toronto.edu/git/csc207-2020-09/group_0143/phase1/src";

    /**
     * All methods that implement GatewayInterface should implement method serializeData
     * @param a
     * In overridden implementations, object a of type T has its data serialized
     * and does not return anything
     */
    void serializeData (T a);

    /**
     * All methods that implement GatewayInterface should implement method deserializeData
     * @return object a of type T
     * Object a of type T is already serialized by the method serializeData
     */
    T deserializeData();

}


