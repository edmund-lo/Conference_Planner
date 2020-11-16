package Gateways;

import Gateways.GatewayInterface;
import UseCases.MessageManager;

import java.io.*;
/**
 * A Gateway class that serializes and deserializes the Message Manager class
 */
public class MessageGateway implements GatewayInterface<MessageManager>, Serializable {

    public String fileName = "mgt_save.ser";
    /**
     * This method serializes an inputted Entities.User Manager's data
     * @param mm UseCases.MessageManager object
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
            System.out.println("File not Found!");
        }
        catch (IOException ignored){}

    }

    /**
     * Deserializes the given serialized file, and converts it to a Message Manager object
     *
     * @return MessageManager object
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
            System.out.println("File not Found!");
        }
        catch (IOException ignored) {}
        catch (ClassNotFoundException e) {
            System.out.println("Message Manager Class was not found!");
        }
        finally {
            return mm;
        }


    }
}