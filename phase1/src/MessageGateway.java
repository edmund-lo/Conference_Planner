import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.HashMap;
import java.util.List;
public class MessageGateway implements GatewayInterface<MessageManager>, Serializable {
    /**
     * TODO: JAVADOC
     * @return
     */
    public String fileName = "mgt_save.ser";

    public MessageGateway() {

    }

    /**
     * This method serializes an inputted User Manager's data
     * @param mm MessageManager object
     * @catch FileNotFoundException
     * @catch IOException
     */
    public void serializeData(MessageManager mm) {

        try {
            File new_file = new File(fileName);
            FileOutputStream store_file = new FileOutputStream(new_file);
            ObjectOutputStream conv_obj = new ObjectOutputStream(store_file);
            conv_obj.writeObject((MessageManager) mm);
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
    public MessageManager deserializeData() {
        MessageManager mm = new MessageManager();
        try {
            File new_file2 = new File(fileName);
            //
            FileInputStream file2 = new FileInputStream(new_file2);
            ObjectInputStream input = new ObjectInputStream(file2);

            mm = (MessageManager) input.readObject();
            file2.close();
            input.close();
            return mm;
        }
        catch (FileNotFoundException e) {
            System.out.println("File not Found!!");
        }
        catch (IOException e){
            System.out.println("IO Exception Raised!!");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Message Manager Class was not found");
        }
        return mm;

    }
//
//    public static void main(String[] args) {
//        //TODO: CREATE TEST CASES
//        EventGateway eg = new EventGateway();
//        Event e = new Event();
//        EventManager em = new EventManager();
//        em.("RoomManage", r);
//        rg.serializeData(rm);
//        RoomManager rr = rg.deserializeData();
//
//    }


}