import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
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
        return get(name) != null;
    }

    /**
     * Gets a GraphNode with the given 'name'.
     *
     * @param name the name of the desired node.
     * @return the GraphNode with the given 'name', null if there is no such
     * node.
     */
    public GraphNode get(String name) {
        for (GraphNode node : nodes) {
            if (node.getName().equals(name)) {
                return node;
            }
        }
        return null;
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
     * Checks if there's a valid path from 'start' to 'finish' using a depth
     * first search.
     *
     * @param start the starting node.
     * @param finish the ending node.
     * @return true if there's a valid path from 'start' to 'finish', false
     * otherwise.
     */
    public boolean canReachDFS(String startName, String finishName) {
        //
        // Sanity check
        //
        GraphNode start = this.get(startName);
        if (start == null) {
            System.out.println("Unable to find node for name '" + startName + "'.");
            return false;
        }
        GraphNode finish = this.get(finishName);
        if (finish == null) {
            System.out.println("Unable to find node for name '" + finishName + "'.");
            return false;
        }
        //
        // Actual DFS
        //
        HashSet<GraphNode> visited = new HashSet<GraphNode>();
        visited.add(start);
        visitDFS(start, visited);
        return visited.contains(finish);
    }

    /**
     * Attempts to reach the 'finish' node from the 'start' node. If it reaches
     * the finish node, this method will print out in reverse order the path
     * taken.
     *
     * @param start the root node.
     * @param visited a set of the previously visited nodes.
     * @param finish the final destination node.
     * @return true if the 'finish' node can be reached, false otherwise.
     */
    private boolean buildPathDFS(GraphNode start, Set<GraphNode> visited, GraphNode finish) {
        for (GraphNode node : start.getNeighbors()) {
            if (!visited.contains(node)) {
                visited.add(node);
                if (node.equals(finish)) {
                    System.out.print("Path from finish to start: (Finish) " + finish.getName());
                    return true;
                } else if (buildPathDFS(node, visited, finish)) {
                    System.out.print(" < " + node.getName());
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * Prints the path from 'start' to 'finish' using a depth first search.
     *
     * @param start the starting node.
     * @param finish the final destination node.
     */
    public void printPathDFS(String startName, String finishName) {
        //
        // Sanity check
        //
        GraphNode start = this.get(startName);
        if (start == null) {
            System.out.println("Unable to find node for name " + startName + ".");
            return;
        }
        GraphNode finish = this.get(finishName);
        if (finish == null) {
            System.out.println("Unable to find node for name " + finishName + ".");
            return;
        }
        //
        // Actual DFS
        //
        HashSet<GraphNode> visited = new HashSet<GraphNode>();
        visited.add(start);
        if (!buildPathDFS(start, visited, finish)) {
            System.out.println("Unable to find a path from "
                    + start.getName() + " to " + finish.getName());
        } else {
            System.out.print(" < " + start.getName() + " (Start)\n");
        }
    }

    /**
     * Checks if there's a valid path from 'start' to 'finish' using a depth
     * first search.
     *
     * @param start the starting node.
     * @param finish the ending node.
     * @return true if there's a valid path from 'start' to 'finish', false
     * otherwise.
     */
    public boolean canReachBFS(String startName, String finishName) {
        //
        // Sanity check
        //
        GraphNode start = this.get(startName);
        if (start == null) {
            System.out.println("Unable to find node for name '" + startName + "'.");
            return false;
        }
        GraphNode finish = this.get(finishName);
        if (finish == null) {
            System.out.println("Unable to find node for name '" + finishName + "'.");
            return false;
        }
        //
        // Actual BFS
        //
        HashSet<GraphNode> visited = new HashSet<GraphNode>();
        LinkedList<GraphNode> queue = new LinkedList<GraphNode>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            GraphNode current = queue.remove(0);
            if (current.equals(finish)) {
                return true;
            }
            for (GraphNode neighbor : current.getNeighbors()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }

    /**
     * Prints the path from 'start' to 'finish' using a breath first search.
     *
     * @param start the starting node.
     * @param finish the final destination node.
     */
    public void printPathBFS(String startName, String finishName) {
        //
        // Sanity check
        //
        GraphNode start = this.get(startName);
        if (start == null) {
            System.out.println("Unable to find node for name '" + startName + "'.");
            return;
        }
        GraphNode finish = this.get(finishName);
        if (finish == null) {
            System.out.println("Unable to find node for name '" + finishName + "'.");
            return;
        }
        //
        // Actual BFS
        //
        HashMap<GraphNode, GraphNode> visited = new HashMap<GraphNode, GraphNode>();
        LinkedList<GraphNode> queue = new LinkedList<GraphNode>();
        queue.add(start);
        visited.put(start, start);

        while (!queue.isEmpty()) {
            GraphNode current = queue.remove(0);
            if (current.equals(finish)) {
                break;
            }
            for (GraphNode neighbor : current.getNeighbors()) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
        //
        // Print the path
        //
        if (!visited.containsKey(finish)) {
            System.out.println("Unable to find a path from "
                    + startName + " to " + finishName);
        } else {
            GraphNode current = visited.get(finish);
            System.out.print("Path from finish to start: (Finish) " + finish.getName());
            while (!current.equals(start)) {
                System.out.print(" < " + current.getName());
                current = visited.get(current);
            }
            System.out.print(" < " + start.getName() + " (Start)\n");
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
