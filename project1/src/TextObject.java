import java.util.Collections;
import java.util.List;

/**
 * The document object node for an HTML paragraph
 *
 * @author James Heliotis, Joseph Cumbo
 */
public class TextObject implements DocObject {

    // The string to be contained in this node
    private String text;

    /**
     * Create a text object.
     *
     * @param text The string to be contained in this node
     */
    public TextObject(String text) {
        this.text = text;
    }

    /**
     * Insert a new document object node into the list of children of this node.
     * If this node's type is one that does not have children, or that has a
     * fixed number of children when created, a BadChildException (a descendant
     * of RuntimeException) will occur.
     *
     * @param before The index that the new document object will have, i.e., its
     * ordinal position in the child list. All children formally at that or
     * greater position will have their position numbers increased by one.
     * @param dObj The new document object to be inserted
     */
    @Override
    public void addChild(int before, DocObject dObj) {
        System.out.println("Unable to add children to TextObject.");
        throw new BadChildException();
    }

    /**
     * How many printable characters are in this subtree of the document?
     *
     * @return the number of non-whitespace characters below this node in the
     * tree
     */
    @Override
    public long characterCount() {
        return text.length();
    }

    /**
     * What are the subtrees of this document node?
     *
     * @postcondition returned list is unmodifiable.
     * @return a list of the direct descendant document object nodes of this
     * node, preserving the order in which they were inserted. If this node's
     * type is one that does not have children, an empty list is returned. If
     * this node's type has a single subordinate DocObject, that single node is
     * returned in a singleton list.
     */
    @Override
    public List<DocObject> children() {
        return Collections.unmodifiableList(Collections.emptyList());
    }

    /**
     * Search for a given character sequence in this subtree of the document
     *
     * @param s The search string
     * @return true iff the search string is found in a _single_ child node of
     * this document node
     */
    @Override
    public boolean contains(String s) {
        return text.contains(s);
    }

    /**
     * A string of plain text
     *
     * @return a string containing legal XHTML that represents the doc tree
     * rooted at this node
     */
    @Override
    public String generateHTML() {
        return getText();
    }

    /**
     * A string of plain text.
     *
     * @return the TextObject's internal string from the constructor.
     */
    public String getText() {
        return text;
    }

    /**
     * Is this the root of the document tree?
     *
     * @return true iff this is an instance of RootObject.
     */
    @Override
    public boolean isRoot() {
        return false;
    }

    /**
     * Replace all occurrences of the search object with a new object. The
     * equality operator "==" (not the equals method) is used to identify the
     * search object in the document tree. The assignment operator (not a
     * copying operation) is used to insert the new object. If this node's type
     * is one that does not have children, this method is a no-op.
     *
     * @param oldObj The search object
     * @param newObj The object that replaces each occurrence of the search
     * object
     */
    @Override
    public void replace(DocObject oldObj, DocObject newObj) {
        return;
    }

    /**
     * Replace all occurrences of the search string with a new string. As in the
     * contains method, the search string must exist completely within one
     * document object node. If this node's type is one that does not have
     * children, this method is a no-op.
     *
     * @param oldS The search string
     * @param newS The string that replaces each occurrence of the search string
     */
    @Override
    public void replace(String oldS, String newS) {
        text = text.replace(oldS, newS);
    }
}
