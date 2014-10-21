import java.util.ArrayList;
import java.util.List;

/**
 * @author Joseph Cumbo
 */
public class GraphNode {

    private String name;
    private List<GraphNode> neighbors;

    /**
     * Constructor. Initialized with an empty list of neighbors.
     *
     * @param name: string representing the name associated with the node.
     */
    public GraphNode(String name) {
        this.name = name;
        this.neighbors = new ArrayList<GraphNode>();
    }

    public String getName() {
        return name;
    }

    public List<GraphNode> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(GraphNode neighbor) {
        neighbors.add(neighbor);
    }

    /**
     * Method to generate a string representation of a GraphNode object.
     *
     * @return result: string representing the node.
     */
    @Override
    public String toString() {
        String result;
        result = name + ":  ";

        for (GraphNode nbr : neighbors) {
            result = result + nbr.getName() + ", ";
        }
        // remove last comma and space (just spaces if no nbrs)
        return (result.substring(0, result.length() - 2));
    }
}
