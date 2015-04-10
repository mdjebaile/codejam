package flatten;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Full code hosted at https://github.com/mdjebaile/atlassian.git
 * 
 * This implementation is part of Atlassian interview screenings.
 * 
 * @author miguel.djebaile
 *
 * @param <T> generic type T
 */
public class MyFlattenTree<T> implements FlattenTree<T> {

    // We don't need to create a new List for each leaf, let's clear the auxiliar single item List and use it
    private List<T> singleLeafList = new ArrayList<T>(1);

    private static final Logger logger = Logger.getLogger(MyFlattenTree.class.getName());

    public List<T> flattenInOrder(Tree<T> tree) {
        // Business logic says we don't support null trees
        if (tree == null) {
            throw new IllegalArgumentException("Tree param cannot be null.");
        }
        // Is it LEAF or NODE?
        if (tree.get().isLeft()) {
            singleLeafList.clear();
            singleLeafList.add(tree.get().ifLeft(new Function<T, T>() {
                public T apply(T p) {
                    logger.log(Level.INFO, "Visiting a leaft = {0}", p);
                    return p;
                }
            }));
            return singleLeafList;

        } else {
            // This is a Triple
            return tree.get().ifRight(new Function<Triple<Tree<T>>, List<T>>() {

                public List<T> apply(Triple<Tree<T>> p) {
                    logger.log(Level.FINE, "Visiting a node (Triple). Going recursive");
                    List<T> nodes = new LinkedList<T>();
                    nodes.addAll(flattenInOrder(p.left()));
                    nodes.addAll(flattenInOrder(p.middle()));
                    nodes.addAll(flattenInOrder(p.right()));

                    return nodes;
                }
            });
        }
    }

    public static void main(String[] args) {
        Tree<Integer> nodes = Tree.Node.tree(3, 6, 8);
        Tree<Integer> subtree1 = new Tree.Node<Integer>(Tree.Leaf.leaf(7), nodes, Tree.Leaf.leaf(9));
        Tree<Integer> subtree2 = new Tree.Node<Integer>(Tree.Leaf.leaf(2), Tree.Leaf.leaf(5), subtree1);

        Tree<Integer> root = new Tree.Node<Integer>(Tree.Leaf.leaf(1), subtree2, Tree.Leaf.leaf(4));
        MyFlattenTree<Integer> myFlattenTree = new MyFlattenTree<Integer>();

        System.out.println("Flattened tree: " + myFlattenTree.flattenInOrder(root));
    }
}
