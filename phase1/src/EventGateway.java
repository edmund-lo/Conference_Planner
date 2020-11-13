import java.util.ArrayList;
import java.util.List;
import java.io.*;import java.io.*;
import java.util.HashMap;
import java.util.List;
public class EventGateway implements GatewayInterface, Serializable {
    /**
     * TODO: JAVADOC
     * @return
     */
    public String fileName = "egt_save.ser";

    /**
     * This method serializes an inputted Event Manager's data
     * @param em EventManager object
     * @catch FileNotFoundException
     * @catch IOException
     */
    public void serializeData(EventManager rm) {

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
     * TODO: JAVADOC
     * @return
     */
    public EventManager deserializeData() {

        try {
            EventManager em = null;
            File new_file2 = new File(fileName);
            //
            FileInputStream file2 = new FileInputStream(new_file2);
            ObjectInputStream input = new ObjectInputStream(file2);

            em = (EventManager) input.readObject();
            file2.close();
            input.close();
            return em;
        }
        catch (FileNotFoundException e) {
            System.out.println("File not Found!!");
        }
        catch (IOException e){
            System.out.println("IO Exception Raised!!");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Event Manager Class was not found");
        }
        return null;
    }

    public static void main(String[] args) {
        //TODO: CREATE TEST CASES
        EventGateway eg = new EventGateway();
        Event e = new Event();
        EventManager em = new EventManager();
        em.("", r);
        rg.serializeData(rm);
        RoomManager rr = rg.deserializeData();

    }


}