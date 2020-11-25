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
        //try catch block with catch IO and FileNotFound exceptions
        try {
            //create new file
            File new_file = new File(fileName);
            //save data objects in designated store file
            //Serialize object by writing UserManager um as value to variable conv_obj
            FileOutputStream store_file = new FileOutputStream(new_file);
            ObjectOutputStream conv_obj = new ObjectOutputStream(store_file);
            conv_obj.writeObject(um);
            //close files
            conv_obj.close();
            store_file.close();
        }
        //catch FileNotFoundException
        catch (FileNotFoundException e) {
            System.out.println("File not Found!");
        }
        //Silently catch IO exception
        catch (IOException ignored){}
    }

    /**
     * This method deserializes an User Manager's data
     *
     * @return the UserManager class
     */
    public UserManager deserializeData() {
        //Instantiate UserManager object um
        UserManager um = new UserManager();
        //try catch block ensures FileNotFound, ClassNotFound and IO Exception are caught
        try {
            //create new file
            File new_file2 = new File(fileName);
            //assign to file, save and unpack the serialized data to UserManager object
            //input variable has value file input stream assigned
            //Assign and deserialize our data to UserManager um
            FileInputStream file2 = new FileInputStream(new_file2);
            ObjectInputStream input = new ObjectInputStream(file2);
            um = (UserManager) input.readObject();
            //close files
            input.close();
            file2.close();
        }
        //catch FileNotFoundException
        catch (FileNotFoundException e) {
            System.out.println("Generating new file: " + fileName);
        }
        //Silently catch IO exception
        catch (IOException ignored){}
        //Catch ClassNotFoundException
        catch (ClassNotFoundException e) {
            System.out.println("UserManager Class was not found");
        }
        return um;
    }
}