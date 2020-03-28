import java.util.ArrayList;
import java.util.LinkedList;

public class BST<E extends Comparable<E>> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;

    /** Create a default binary tree */
    public BST() {
    }

    /** Create a binary tree from an array of objects */
    public BST(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    //Gets the height of the tree
    public int height() {
        int height = -1;
        int height2 = -1;
        TreeNode<E> current = root;
        if (current != null) {
            height++;
            height2 = height;
            if (current.left != null)
                height += heightHelper(current.left);
            if (current.right != null)
                height2 += heightHelper(current.right);
        }
        return (height > height2 ? height : height2);
    }

    //Recursion used to check all the branches of the tree
    public int heightHelper(TreeNode<E> node) {
        int height = 1;
        int height2 = 1;
        if (node.left != null)
            height += heightHelper(node.left);
        if (node.right != null)
            height2 += heightHelper(node.right);
        return (height > height2 ? height : height2);
    }

    //Gets the inorderlist using the InorderIterator class already created
    public java.util.List<E> inorderList() {
        if (root == null)
            return new ArrayList();
        InorderIterator iter = new InorderIterator();
        return iter.list;
    }

    //Recursively creates preOrder list: ordered root left right
    public java.util.List<E> preorderList() {
        if (root == null)
            return new ArrayList();
        TreeNode<E> current = root;
        ArrayList<E> list = new ArrayList();
        list.add(current.element);
        if (current.left != null)
            preorderList(current.left, list);
        if (current.right != null)
            preorderList(current.right, list);
        return list;
    }

    //preorderList helper method
    public void preorderList(TreeNode<E> node, ArrayList<E> list) {
        list.add(node.element);
        if (node.left != null)
            preorderList(node.left, list);
        if (node.right != null)
            preorderList(node.right, list);
    }

    //Recursively creates postorder list: ordered left right root
    public java.util.List<E> postorderList() {
        if (root == null) //If tree is empty returns empty list
            return new ArrayList();
        TreeNode<E> current = root;
        ArrayList<E> list = new ArrayList();
        if (current.left != null)
            postorderList(current.left, list);
        if (current.right != null)
            postorderList(current.right, list);
        list.add(current.element);
        return list;
    }

    //postorderList helper method
    public void postorderList(TreeNode<E> node, ArrayList<E> list) {
        if (node.left != null)
            postorderList(node.left, list);
        if (node.right != null)
            postorderList(node.right, list);
        list.add(node.element);
    }

    public java.util.List<E> breadthFirstOrderList() {
        if (root == null) //If tree is empty returns empty list
            return new ArrayList();
        LinkedList<TreeNode<E>> q = new LinkedList();
        ArrayList<E> list = new ArrayList();
        q.add(root);
        while (q.size() > 0) {
            TreeNode<E> node = q.pollFirst();
            list.add(node.element);
            if (node.left != null)
                q.add(node.left);
            if (node.right != null)
                q.add(node.right);
        }
        return list;
    }

    @Override /** Returns true if the element is in the tree */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else
                return true; // e is found
        }

        return false;
    }

    @Override /** Insert element o into the binary tree
     * Return true if the element is inserted successfully */
    public boolean insert(E e) {
        if (root == null)
            root = createNewNode(e); // Create a new root
            //root = new TreeNode<>(e);	// Question:  Why not do it this way?  It would work.
        else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null)
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                }
                else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                }
                else
                    return false; // Duplicate node not inserted

            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0)
                parent.left = createNewNode(e);
                //parent.left = new TreeNode<>(e);
            else
                parent.right = createNewNode(e);
        }

        size++;
        return true; // Element inserted successfully
    }

    protected TreeNode<E> createNewNode(E e) {  // Factory method design pattern
        return new TreeNode<>(e);
    }

    @Override /** Inorder traversal from the root */
    public void inorder() {
        inorder(root);
    }

    /** Inorder traversal from a subtree */
    protected void inorder(TreeNode<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    @Override /** Postorder traversal from the root */
    public void postorder() {
        postorder(root);
    }

    /** Postorder traversal from a subtree */
    protected void postorder(TreeNode<E> root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    @Override /** Preorder traversal from the root */
    public void preorder() {
        preorder(root);
    }

    /** Preorder traversal from a subtree */
    protected void preorder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /** This inner class is static, because it does not access
     any instance members defined in its outer class */
    public static class TreeNode<E> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    @Override /** Get the number of nodes in the tree */
    public int getSize() {
        return size;
    }

    /** Returns the root of the tree */
    public TreeNode<E> getRoot() {
        return root;
    }

    /** Returns a path from the root leading to the specified element */
    public java.util.ArrayList<TreeNode<E>> path(E e) {
        java.util.ArrayList<TreeNode<E>> list =
                new java.util.ArrayList<>();
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            list.add(current); // Add the node to the list
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else
                break;
        }

        return list; // Return an array list of nodes
    }

    @Override /** Delete an element from the binary tree.
     * Return true if the element is deleted successfully
     * Return false if the element is not in the tree */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            }
            else
                break; // Element is in the tree pointed at by current
        }

        if (current == null)
            return false; // Element is not in the tree

        // Case 1: current has no left child
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            }
            else {
                if (e.compareTo(parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        }
        else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                // Special case: parentOfRightMost.left == rightMost, happens if parentOfRightMost is current
                parentOfRightMost.left = rightMost.left;
        }

        size--;
        return true; // Element deleted successfully
    }

    @Override /** Obtain an iterator. Use inorder. */
    public java.util.Iterator<E> iterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    private class InorderIterator implements java.util.Iterator<E> {
        // Store the elements in a list
        private java.util.ArrayList<E> list =
                new java.util.ArrayList<>();
        private int current = 0; // Index of next element in the iteration
        private boolean canRemove = false;

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /** Inorder traversal from the root*/
        private void inorder() {
            inorder(root);
        }

        /** Inorder traversal from a subtree */
        private void inorder(TreeNode<E> root) {
            if (root == null) return;
            inorder(root.left);
            this.list.add(root.element);
            inorder(root.right);
        }

        @Override /** More elements for traversing? */
        public boolean hasNext() {
            return current < list.size();
        }

        @Override /** Get the current element and move to the next */
        public E next() {
            if (hasNext())
                canRemove = true;
            else
                throw new java.util.NoSuchElementException();
            return list.get(current++);
        }

        @Override /** Remove the element most recently returned */
        public void remove() {
            if (!canRemove)
                throw new IllegalStateException();
            BST.this.delete(list.get(--current));
            InorderIterator.this.list.remove(current);
            canRemove = false;
        }
    }

    @Override /** Remove all elements from the tree */
    public void clear() {
        root = null;
        size = 0;
    }
}
