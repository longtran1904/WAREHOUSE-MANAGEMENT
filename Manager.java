import java.io.IOException;

import javax.swing.JFrame;

import GUI.ProductManager;

public class Manager extends JFrame {
    public static void main(String[] args) throws IOException {
        Manager window = new Manager();
    }

    public Manager() throws IOException {
        ProductManager firstPage = new ProductManager();
        this.add(firstPage);
        this.setTitle("WAREHOUSE MANAGER");
        this.setSize(800, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
