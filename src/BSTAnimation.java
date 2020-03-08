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

public class BSTAnimation extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        BST<Integer> tree = new BST<>(); // Create a tree

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
        Button btBreadthFirst = new Button("Breadth-fitst");
        Button btHeight = new Button("Height");
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Enter a key: "),
                tfKey, btInsert, btDelete, btSearch, btInorder,
                btPreorder, btPostorder, btBreadthFirst, btHeight);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);

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

        btSearch.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            ArrayList path = tree.path(key);

            System.out.println("searching for "+key);
        });

        btInorder.setOnAction(e -> { //left root right
            ArrayList = getInOrder();
        });

        private ArrayList<TreeNode<E>> getInOrder() {
            ArrayList list = new ArrayList();
            return list;
        }

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 450, 250);
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

