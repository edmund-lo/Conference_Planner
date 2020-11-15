package Gateways;

import UseCases.EventManager;

import java.io.*;
import java.io.Serializable;

/**
 * A Gateway class that serializes and deserializes the Event Manager class
 *
 */
public class EventGateway implements GatewayInterface<EventManager>, Serializable {

    /**
     * Serial extension file egt_save which stores serialized and
     * deserialized data
     */
    public String fileName = "egt_save.ser";

    /**
     * Serializes an inputted Event Manager's data
     *
     * @param em UseCases.EventManager object
     */

    public void serializeData(EventManager em){

        try {
            File new_file = new File(fileName);
            FileOutputStream store_file = new FileOutputStream(new_file);
            ObjectOutputStream conv_obj = new ObjectOutputStream(store_file);
            conv_obj.writeObject((EventManager) em);
            conv_obj.close();
            store_file.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not Found!!");
        }
        catch (IOException e){
            System.out.println("IO Exception Raised!!");
        }
    }

    /**
     * Deserializes an Event Manager's data
     *
     * @return the Event Manager class
     */
    public EventManager deserializeData() {
        EventManager em = new EventManager();
        try {

            File new_file2 = new File(fileName);
            //
            FileInputStream file2 = new FileInputStream(new_file2);
            ObjectInputStream input = new ObjectInputStream(file2);

            em = (EventManager) input.readObject();
            input.close();
            file2.close();
            return em;
        }
        catch (FileNotFoundException e) {
            System.out.println("File not Found!!");
        }
        catch (IOException e){}

        catch (ClassNotFoundException e) {
            System.out.println("Entities.Event Manager Class was not found");
        }
        finally {
            return em;
        }
    }

}