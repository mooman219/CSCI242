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
                // Get source & target nodes
                GraphNode source = nodeMap.get(info[0]);
                GraphNode target = nodeMap.get(info[1]);
                // Create the nodes if they don't exist
                if (source == null) {
                    source = new GraphNode(info[0]);
                    nodeMap.put(info[0], source);
                }
                if (target == null) {
                    target = new GraphNode(info[1]);
                    nodeMap.put(info[1], target);
                }
                // Link the nodes
                if (!source.contains(target)) {
                    source.addNeighbor(target);
                }
                if (!target.contains(source)) {
                    target.addNeighbor(source);
                }
            }
            nodes.addAll(nodeMap.values());
        } catch (IOException ex) {
            System.out.println("Unable to read file.");
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

    /**
     * Method to generate a string representation of a Graph object.
     *
     * @return result: string representing the graph.
     */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("Graph:  \n");
        for (GraphNode node : nodes) {
            ret.append("\t").append(node.toString()).append("\n");
        }
        return ret.toString();
    }
}
