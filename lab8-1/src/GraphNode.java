import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a node with a given name in a Graph. It is linked to a
 * variable number of other GraphNodes.
 *
 * @author Joseph Cumbo
 */
public class GraphNode {

    // The name of this GraphNode.
    private final String name;
    // List of neighbors this GraphNode is connected to.
    private final List<GraphNode> neighbors;

    /**
     * Constructor. Initialized with an empty list of neighbors.
     *
     * @param name: string representing the name associated with the node.
     */
    public GraphNode(String name) {
        this.name = name;
        this.neighbors = new ArrayList<GraphNode>();
    }

    /**
     * Gets the name this GaphNode is representing.
     *
     * @return the name of this GraphNode.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the neighbors this GraphNode is connected to.
     *
     * @return a List of GraphNodes connection to this GraphNode.
     */
    public List<GraphNode> getNeighbors() {
        return neighbors;
    }

    /**
     * Adds the given 'neighbor' to the list of neighbors contained in this
     * GraphNode.
     *
     * @param neighbor the GraphNode to be added.
     */
    public void addNeighbor(GraphNode neighbor) {
        neighbors.add(neighbor);
    }

    /**
     * Checks if 'neighbor' is a neighbor of this GraphNode.
     *
     * @param neighbor the GraphNode who's presence is being tested.
     * @return true if 'neighbor' is contained in this GraphNode.
     */
    public boolean contains(GraphNode neighbor) {
        for (GraphNode node : neighbors) {
            if (node.equals(neighbor)) {
                return true;
            }
        }
        return false;
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
