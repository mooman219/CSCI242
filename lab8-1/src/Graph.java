import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Joseph Cumbo
 */
public class Graph {

    private ArrayList<GraphNode> nodes = new ArrayList<GraphNode>();

    public Graph(String filename) {
        File file = new File(filename);
        HashMap<String, GraphNode> nodeMap = new HashMap<String, GraphNode>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] info = line.split(" ");
                if (info.length < 2) { // Omit invalid lines
                    continue;
                }
                GraphNode source = nodeMap.get(info[0]);
                if (source == null) {
                    source = new GraphNode(info[0]);
                }
                GraphNode target = nodeMap.get(info[1]);
                if (target == null) {
                    target = new GraphNode(info[1]);
                }
                source.addNeighbor(target);
            }
        } catch (IOException ex) {
            System.out.println("Unable to read file.");
            return;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    System.out.println("Unable to close file reader.");
                }
            }
        }
    }
}
