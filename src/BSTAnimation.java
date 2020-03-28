import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class BSTAnimation extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        BST<Integer> tree = new BST<>(); // Create a tree

        tree.insert(30);
        tree.insert(20);
        tree.insert(25);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
        tree.insert(87);

        BorderPane pane = new BorderPane();
        BTView view = new BTView(tree); // Create a View
        pane.setCenter(view);

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");
        Button btSearch = new Button("Search");
        Button btInorder = new Button("Inorder");
        Button btPreorder = new Button("Preorder");
        Button btPostorder = new Button("Postorder");
        Button btBreadthFirst = new Button("Breadth-first");
        Button btHeight = new Button("Height");
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Enter a key: "),
                tfKey, btInsert, btDelete, btSearch, btInorder,
                btPreorder, btPostorder, btBreadthFirst, btHeight);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);

        //Insert button
        btInsert.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (tree.search(key)) { // key is in the tree already
                view.displayTree();   // Clears the old status message
                view.setStatus(key + " is already in the tree");
            }
            else {
                tree.insert(key); // Insert a new key
                view.displayTree();
                view.setStatus(key + " is inserted in the tree");
            }
        });

        //Delete button
        btDelete.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (!tree.search(key)) { // key is not in the tree
                view.displayTree();    // Clears the old status message
                view.setStatus(key + " is not in the tree");
            }
            else {
                tree.delete(key); // Delete a key
                view.displayTree();
                view.setStatus(key + " is deleted from the tree");
            }
        });

        //Search Button
        btSearch.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText()); //Gets the number in the text field
            ArrayList<BST.TreeNode<Integer>> path = tree.path(key);
            ArrayList<Integer> intPath = new ArrayList<>();
            for (int i=0; i < path.size(); i++) {
                intPath.add(path.get(i).element);
            }
            view.displayShadedTree(intPath);

            if (tree.search(key))
                view.setStatus("Found "+key+" in the tree");
            else
                view.setStatus(key+" is not in the tree");
        });

        //Inorder list
        btInorder.setOnAction(e -> {
            view.displayTree();
            List<Integer> list = tree.inorderList();
            view.setStatus("Inorder traversal: " + list.toString());
        });

        //Preorder list
        btPreorder.setOnAction(e -> {
           view.displayTree();
           List<Integer> list = tree.preorderList();
           view.setStatus("Preorder traversal: " + list.toString());
        });

        //Postorder list
        btPostorder.setOnAction(e -> {
            view.displayTree();
            List<Integer> list = tree.postorderList();
            view.setStatus("Postorder traversal: " + list.toString());
        });

        //Breadth First list
        btBreadthFirst.setOnAction(e -> {
            view.displayTree();
            List<Integer> list = tree.breadthFirstOrderList();
            view.setStatus("Breadth-first traversal: " + list.toString());
        });

        btHeight.setOnAction(e -> {
            view.displayTree();
            view.setStatus("Tree height is "+tree.height());
        });

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 650, 250);
        primaryStage.setTitle("BSTAnimation"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

