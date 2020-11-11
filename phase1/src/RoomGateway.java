import java.io.*;
import java.util.HashMap;
import java.util.List;
public class RoomGateway implements GatewayInterface, Serializable {
    /**
     *
     */
    public String fileName = "rgt_save.txt";

    public RoomGateway() {
        //File new_file = new File("rgt_test.txt");
    }

    /**
     * This method serializes an inputted Room Manager's list of rooms
     * @param rm
     * @throws IOException
     */
    public void serializeData(RoomManager rm) throws IOException {
        //RoomGateway room_g = new RoomGateway();
        File new_file = new File(fileName);
        //saving the file
        FileOutputStream fos = new FileOutputStream(new_file);
        //converts file1 to binary object
        ObjectOutputStream serialized_f = new ObjectOutputStream(fos);
        //write to the object serializing
        serialized_f.writeObject(rm);

        serialized_f.close();
        fos.close();
        //confirm success
        System.out.println("successfully saved!");
        System.out.println(serialized_f);

    }

    public HashMap<String, Room> deserializeData() throws IOException, ClassNotFoundException {


        //File new_file = new File(fileName);
        FileInputStream file2 = new FileInputStream(fileName);
        ObjectInputStream input = new ObjectInputStream(file2);

        deserialized_rm = (RoomManager)input.readObject();
        input.close();
        file2.close();

        System.out.println("Successfully deserialized");
        System.out.println(deserialized_rm);



    }


}