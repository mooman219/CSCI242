import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents a Graph of interconnected GraphNodes.
 *
 * @author Joseph Cumbo
 */
public class Graph {

    // The list of GraphNodes contained within this Graph.
    private List<GraphNode> nodes = new ArrayList<GraphNode>();

    /**
     * Constructs a new Graph instance with a node structure based on the data
     * in the file with the name 'filename.
     *
     * @param filename the name of the file that the graph data will be
     * retrieved from.
     */
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
     * Checks if a node of given 'name' is contained in this Graph.
     *
     * @param name the 'name' who's presence is being tested.
     * @return true if a node by the given 'name' is contained in this Graph.
     */
    public boolean isInGraph(String name) {
        for (GraphNode node : nodes) {
            if (node.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Visits every node that can be reached from 'start'.
     *
     * @param start the root node.
     * @param visited all visited nodes are added to this the 'visited' set.
     */
    private void visitDFS(GraphNode start, Set<GraphNode> visited) {
        for (GraphNode node : start.getNeighbors()) {
            if (!visited.contains(node)) {
                visited.add(node);
                visitDFS(node, visited);
            }
        }
    }

    /**
     * Checks if there's a valid path from 'start' to 'finish'.
     *
     * @param start the starting node.
     * @param finish the ending node.
     * @return true if there's a valid path from 'start' to 'finish', false
     * otherwise.
     */
    public boolean canReachDFS(GraphNode start, GraphNode finish) {
        HashSet<GraphNode> visited = new HashSet<GraphNode>();
        visited.add(start);
        visitDFS(start, visited);
        return visited.contains(finish);
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
