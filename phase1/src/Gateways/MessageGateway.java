package Gateways;

import Gateways.GatewayInterface;
import UseCases.MessageManager;

import java.io.*;

public class MessageGateway implements GatewayInterface<MessageManager>, Serializable {
    /**
     * TODO: JAVADOC
     * @return
     */
    public String fileName = "mgt_save.ser";

    /**
     * This method serializes an inputted Entities.User Manager's data
     * @param mm UseCases.MessageManager object
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
            input.close();
            file2.close();
            return mm;
        }
        catch (FileNotFoundException e) {
            System.out.println("File not Found!!");
        }
        catch (IOException e) {}

        catch (ClassNotFoundException e) {
            System.out.println("Entities.Message Manager Class was not found");
        }
        finally {
            return mm;
        }


    }
}