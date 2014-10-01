import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The document object node representing an HTML header construct. All levels
 * are supported.
 *
 * @author James Heliotis, Joseph Cumbo
 */
public class HeaderObject implements DocObject {

    // The header level (Bounds not checked; should be 1-5.)
    private int level;
    // The DocObject being wrapped.
    private DocObject dObj;

    /**
     * Create a HeaderObject of the given level
     *
     * @param level The header level (Bounds not checked; should be 1-5.)
     * @param dObj The underlying DocObject containing the header's text
     */
    public HeaderObject(int level, DocObject dObj) {
        this.level = level;
        this.dObj = dObj;
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
        System.out.println("Unable to add children to HeaderObject.");
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
        return dObj.characterCount();
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
        List<DocObject> ret = new ArrayList<DocObject>();
        ret.add(dObj);
        return Collections.unmodifiableList(ret);
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
        return dObj.contains(s);
    }

    /**
     * Convert this subtree of the document to plain-text HTML.
     *
     * @return a string containing legal XHTML that represents the doc tree
     * rooted at this node
     */
    @Override
    public String generateHTML() {
        return "<h" + level + ">"
                + dObj.generateHTML()
                + "</h" + level + ">";
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
        if (dObj == oldObj) {
            dObj = newObj;
        } else {
            dObj.replace(oldObj, newObj);
        }
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
        dObj.replace(oldS, newS);
    }
}
