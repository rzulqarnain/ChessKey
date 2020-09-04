package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Methods for reading and writing files.
 *
 * @author Stephen Monk
 * @version 13-Oct-2008
 * @version 25-Oct-2010
 * @version 09-Oct-2012
 */
public class FileUtils {

    private FileUtils() {
    } // no instantiation

    /**
     * Reads the lines of a file into a List of strings.
     *
     * @param fileName the name of the file
     * @return a list of strings, such that each element is one line from the
     * file. The list is empty in the case of an I/O error.
     */
    public static List<String> readIntoList(String fileName) {
        Scanner sc = null;
        ArrayList<String> res = new ArrayList<String>();

        try {
            File fileObject = new File(fileName);
            sc = new Scanner(fileObject);
            while (sc.hasNextLine()) {
                res.add(sc.nextLine());
            }
        } catch (FileNotFoundException fnfe) {
            res.clear();
        } catch (IOException ioe) {
            res.clear();
        } finally {
            if (sc != null) {
                sc.close();
            }
        }

        return res;
    } // end readIntoList

    /**
     * Reads the lines of a file into an array of strings.
     *
     * @param fileName the name of the file
     * @return an array such that each element contains one line from the file,
     * or a zero-length array in the case of an I/O error.
     */
    public static String[] readIntoArray(String fileName) {
        return readIntoList(fileName).toArray(new String[0]);
    } // end readIntoArray

    /**
     * Writes the contents of a List of strings to a file, one element per line.
     * If the specified file already exists, it will be over-written. If the
     * specified file does not exist, it will be created.
     *
     * @param lines the list of Strings to be written
     * @param fileName the file to write to
     */
    public static void writeListToFile(List<String> lines, String fileName) {
        writeArrayToFile(lines.toArray(new String[0]), fileName);
    } // end writeListToFile

    /**
     * Writes the contents of an array to a file, one element per line. If the
     * specified file already exists, it will be over-written. If the specified
     * file does not exist, it will be created.
     *
     * @param lines the array to be written
     * @param fileName the file to write to
     */
    public static void writeArrayToFile(String[] lines, String fileName) {
        File fileObject;
        PrintWriter writer = null;

        fileObject = new File(fileName);

        try {
            writer = new PrintWriter(fileObject);

            for (String s : lines) {
                writer.println(s);
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe);
        } catch (IOException ioe) {
            System.err.println(ioe);
        } finally {
            if (writer != null) {
                writer.close(); // finalise the file writing
            }
        }
    } // end writeToFile
}
