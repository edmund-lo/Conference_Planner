package Gateways;

import UseCases.MessageManager;

import java.io.*;

/**
 * A Gateway class that serializes and deserializes the Message Manager class
 *
 */
public class MessageGateway implements GatewayInterface<MessageManager>, Serializable {
    /**
     * Serial extension file mgt_save which stores serialized and deserialized data
     */
    public String fileName = "mgt_save.ser";

    /**
     * This method serializes an inputted Message Manager's data
     *
     * @param mm MessageManager object
     */
    public void serializeData(MessageManager mm) {

        try {
            File new_file = new File(fileName);
            FileOutputStream store_file = new FileOutputStream(new_file);
            ObjectOutputStream conv_obj = new ObjectOutputStream(store_file);
            conv_obj.writeObject(mm);
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
     * @return Message Manager object
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
            System.out.println("Generating new file: " + fileName);
        }
        catch (IOException ignored) {}
        catch (ClassNotFoundException e) {
            System.out.println("MessageManager Class was not found!");
        }

        return mm;
    }
}