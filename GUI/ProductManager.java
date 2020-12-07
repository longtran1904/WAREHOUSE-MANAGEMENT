package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.List;

import BusinessLogic.Item;
import BusinessLogic.MyList;
import BusinessLogic.SaveLocal;


public class ProductManager extends JPanel {
    JButton addButton, removeButton, searchButton;
    JLabel productName, amount, description, title;
    JTextField productField, amountField;
    JTextArea desField;
    MyList list;
    List<String> lines;
    String fileName = "BusinessLogic/data.txt";

    public ProductManager() throws IOException {

        addButton = new JButton("ADD");
        addButton.setBounds(300, 400, 75, 40);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String product = productField.getText().trim();
                product = Capitalize.set(product);
                String amount = amountField.getText().trim();
                String des = desField.getText().trim();

                productField.setText("");
                amountField.setText("");
                desField.setText("");

                if (product != "" && amount != "") {
                    int idx = list.isDuplicate(product);
                    if (idx < 0)
                        list.addItem(product, amount, des);
                    else
                        list.addItemAmount(product, amount, des, idx);
                    try {
                        SaveLocal.save(list.getModel());
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                
            }

        });

        removeButton = new JButton("REMOVE");
        removeButton.setBounds(400, 400, 75, 40);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String product = productField.getText().trim();
                product = Capitalize.set(product);
                String amount = amountField.getText().trim();
                if (amount.isEmpty()) amount = "1";
                String des = desField.getText().trim();

                productField.setText("");
                amountField.setText("");
                desField.setText("");

                if (product != "" && amount != ""){
                    int idx = list.isDuplicate(product);
                    if (idx >= 0)
                        list.decreaseItemAmount(product, amount, des, idx);
                }
                try {
                    SaveLocal.save(list.getModel());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

        });

        searchButton = new JButton("SEARCH");
        searchButton.setBounds(500, 400, 75, 40);
        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                SearchManager search = new SearchManager();
            }
        }); 

        productName = new JLabel("Product Name");
        productName.setBounds(20, 20, 100, 20);

        title = new JLabel("Product list");
        title.setBounds(500, 20, 200, 50);
        title.setFont(new Font("Serif", Font.PLAIN, 30));

        amount = new JLabel("Amount");
        amount.setBounds(20, 80, 100, 20);

        description = new JLabel("Description");
        description.setBounds(20, 140, 100, 20);

        productField = new JTextField();
        productField.setBounds(140, 20, 180, 30);

        amountField = new JTextField();
        amountField.setBounds(140, 80, 180, 30);
        amountField.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || (e.getKeyCode() == KeyEvent.VK_BACK_SPACE))
                    amountField.setEditable(true);
                else 
                    amountField.setEditable(false);
            }
        });

        desField = new JTextArea();
        desField.setBounds(140, 140, 180, 150);

        list = MyList.getInstance();
        JList<Item> jList = list.getList();
        jList.setBounds(470, 80, 200, 200);

        //Read Initial data for jlist
        lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        if (!lines.isEmpty()) 
            list.initiateData(lines);

        this.setLayout(null);

        this.add(addButton);
        this.add(removeButton);
        this.add(searchButton);
        this.add(productName);
        this.add(amount);
        this.add(description);
        this.add(title);

        this.add(productField);
        this.add(amountField);
        this.add(desField);

        this.add(jList);
    }


}
