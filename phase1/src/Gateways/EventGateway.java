package Gateways;

import UseCases.EventManager;

import java.io.*;
import java.io.Serializable;

public class EventGateway implements GatewayInterface<EventManager>, Serializable {
    /**
     * TODO: JAVADOC
     * @return
     */
    public String fileName = "egt_save.ser";

    /**
     * This method serializes an inputted Entities.Event Manager's data
     * @param em UseCases.EventManager object
     * @catch FileNotFoundException
     * @catch IOException
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
            System.out.println("serial");
        }
        catch (IOException e){
            System.out.println("IO Exception Raised!!");
        }
    }
    /**
     * TODO: JAVADOC
     * @return
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