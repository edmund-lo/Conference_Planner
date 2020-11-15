package Gateways;

import UseCases.MessageManager;

import java.io.*;

public class MessageGateway implements GatewayInterface<MessageManager>, Serializable {
    /**
     * This gateway class implements abstract interface GatewayInterface with object MessageManager
     * Implements Serializable to serialize and deserialize a given object MessageManager in a file
     */
    public String fileName = "mgt_save.ser";

    /**
     * This method serializes an inputted UserManager's data
     * Saves this data, creates and stores in a file if there is none that exists and is specified
     * @param mm MessageManager object passed in to serialize and save in .ser file
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
     * Deserializes a MessageManager object
     * Saves the serialized version of MessageManager object, and then deserializes it to return
     * MessageManager object
     * @return MessageManager object
     */
    public MessageManager deserializeData() {
        MessageManager mm = new MessageManager();
        try {
            //Create new file
            File new_file2 = new File(fileName);
            //Store file and save data
            //read serialized object data and deserialize by casting the stored data
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