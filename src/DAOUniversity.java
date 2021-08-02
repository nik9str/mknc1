import java.io.*;

/**
 * Class responsible for storing university groups and students saving and restoration
 */
public class DAOUniversity {

    /**
     * Serializes whole university to binary file.
     */
    public void serializeUniversity(University university){
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(university.getName() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(university);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializes whole university to binary file.
     * @return University
     * @see University
     */
    public University deSerializeUniversity(String name){
        FileInputStream fileIn;
        try {
            fileIn = new FileInputStream(name+".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            return (University) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
