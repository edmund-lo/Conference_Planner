package Gateways;

import UseCases.EventManager;

import java.io.*;
import java.io.Serializable;

/**
 * A Gateway class that serializes and deserializes the Event Manager class
 */
public class EventGateway implements GatewayInterface<EventManager>, Serializable {

    /**
     * Serial extension file egt_save which stores serialized and
     * deserialized data
     */
    //Name of our .ser file that will be used to save data in EventGateway methods below
    public String fileName = "egt_save.ser";

    /**
     * Serializes an inputted Event Manager's data
     *
     * @param em UseCases.EventManager object
     */

    public void serializeData(EventManager em){
        //try catch block to ensure FileNotFound and IO exceptions are caught
        try {
            //create new file using our initialized String variable
            File new_file = new File(fileName);
            //Use FileOutputStream and ObjectOutputStream to store the passed in EventManager em
            FileOutputStream store_file = new FileOutputStream(new_file);
            ObjectOutputStream conv_obj = new ObjectOutputStream(store_file);
            //Serialize object EventManager em
            conv_obj.writeObject((EventManager) em);
            //close files
            conv_obj.close();
            store_file.close();
        }
        //Catches FileNotFound exception
        catch (FileNotFoundException e) {
            System.out.println("File not Found!");
        }
        //silently catches IO exception
        catch (IOException ignored){}
    }

    /**
     * Deserializes an Event Manager's data
     *
     * @return the Event Manager class
     */
    public EventManager deserializeData() {
        //Instantiate New EventManager object
        EventManager em = new EventManager();
        try {
            //create new file using String fileName
            File new_file2 = new File(fileName);
            //Save data and assign read value object to EventManager object em
            FileInputStream file2 = new FileInputStream(new_file2);
            ObjectInputStream input = new ObjectInputStream(file2);
            em = (EventManager) input.readObject();
            //close files
            input.close();
            file2.close();
            //return EventManager object
            return em;
        }
        //catch FileNotFoundException
        catch (FileNotFoundException e) {
            System.out.println("File not Found!");
        }
        //silently catch IO exception
        catch (IOException ignored){}
        //catch ClassNotFoundException
        catch (ClassNotFoundException e) {
            System.out.println("Event Manager Class was not found");
        }
        //return EventManager object
        finally {
            return em;
        }
    }

}