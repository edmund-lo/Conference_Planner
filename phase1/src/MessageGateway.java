import java.util.ArrayList;
import java.util.List;
import java.io.*;import java.io.*;
import java.util.HashMap;
import java.util.List;
public class MessageGateway implements GatewayInterface, Serializable {
    /**
     *
     */
    public String fileName = "mgt_save.txt";

    public MessageGateway() {

    }

    /**
     * This method serializes an inputted User Manager's data
     * @param mm MessageManager object
     * @throws IOException
     */
    public void serializeData(MessageManager mm) throws IOException {

        File new_file = new File(fileName);
        //saving the file
        FileOutputStream fos = new FileOutputStream(new_file);
        //converts file1 to binary object
        ObjectOutputStream serialized_f = new ObjectOutputStream(fos);
        //write to the object serializing
        serialized_f.writeObject(mm);

        serialized_f.close();
        fos.close();
        //confirm success
        System.out.println("successfully saved serialized UserManager!");
        System.out.println(serialized_f);

    }

    public HashMap<String, Message> deserializeData() throws IOException, ClassNotFoundException {


        //File new_file = new File(fileName);
        FileInputStream file2 = new FileInputStream(fileName);
        ObjectInputStream input = new ObjectInputStream(file2);

        deserialized_mm = (MessageManager)input.readObject();
        input.close();
        file2.close();

        System.out.println("Successfully deserialized");
        System.out.println(deserialized_mm);



    }


}