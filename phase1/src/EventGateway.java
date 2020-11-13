import java.util.ArrayList;
import java.util.List;
import java.io.*;import java.io.*;
import java.util.HashMap;
import java.util.List;
public class EventGateway implements GatewayInterface, Serializable {
    /**
     *
     */
    public String fileName = "egt_save.txt";

    public EventGateway() {

    }

    /**
     * This method serializes an inputted User Manager's data
     * @param em EventManager object
     * @throws IOException
     */
    public void serializeData(EventManager em) throws IOException {

        File new_file = new File(fileName);
        //saving the file
        FileOutputStream fos = new FileOutputStream(new_file);
        //converts file1 to binary object
        ObjectOutputStream serialized_f = new ObjectOutputStream(fos);
        //write to the object serializing
        serialized_f.writeObject(em);

        serialized_f.close();
        fos.close();
        //confirm success
        System.out.println("successfully saved serialized UserManager!");
        System.out.println(serialized_f);

    }

    public HashMap<String, Event> deserializeData() throws IOException, ClassNotFoundException {


        //File new_file = new File(fileName);
        FileInputStream file2 = new FileInputStream(fileName);
        ObjectInputStream input = new ObjectInputStream(file2);

        deserialized_um = (EventManager)input.readObject();
        input.close();
        file2.close();

        System.out.println("Successfully deserialized");
        System.out.println(deserialized_em);



    }


}