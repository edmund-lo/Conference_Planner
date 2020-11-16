package Gateways;

import UseCases.UserManager;

import java.io.*;
/**
 * A Gateway class that serializes and deserializes the User Manager class
 */
public class UserGateway implements GatewayInterface<UserManager>, Serializable {

    /**
     * Serial extension file ugt_save.ser which stores serialized and
     * deserialized data
     */

    public String fileName = "ugt_save.ser";

    /**
     * This method serializes an inputted Entities.User Manager's data
     *
     * @param um UseCases.UserManager object
     *
     */
    public void serializeData(UserManager um) {
        try {
            File new_file = new File(fileName);
            FileOutputStream store_file = new FileOutputStream(new_file);
            ObjectOutputStream conv_obj = new ObjectOutputStream(store_file);
            conv_obj.writeObject(um);
            conv_obj.close();
            store_file.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not Found!");
        }
        catch (IOException ignored){}
    }

    /**
     * This method deserializes an User Manager's data
     *
     * @return the UserManager class
     */
    public UserManager deserializeData() {
        UserManager um = new UserManager();
        try {
            File new_file2 = new File(fileName);
            //
            FileInputStream file2 = new FileInputStream(new_file2);
            ObjectInputStream input = new ObjectInputStream(file2);

            um = (UserManager) input.readObject();
            input.close();
            file2.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not Found!!");
        }
        catch (IOException ignored){}
        catch (ClassNotFoundException e) {
            System.out.println("UserManager Class was not found");
        }

        return um;
    }
}