package GUI;

import javax.swing.JPanel;

import BusinessLogic.MyList;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class SearchManager extends JFrame {
    JPanel panel;
    JLabel productLabel, amountLabel, amountValue, desLabel, desValue;
    JTextField productValue;
    JButton searchButton;
    public SearchManager() {    
        panel = new JPanel();

        productLabel = new JLabel("Product");
        amountLabel = new JLabel("Amount");
        desLabel = new JLabel("Description");
        productValue = new JTextField();
        amountValue = new JLabel();
        desValue = new JLabel();
        searchButton = new JButton("Search");

        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String product = productValue.getText().trim();
                product = Capitalize.set(product);
                if (!product.isEmpty()){
                    MyList list = MyList.getInstance();
                    List<String> items = list.getModel();
                    boolean foundItem = false;
                    for (String item: items){
                        String[] properties = item.split(" ");
                        String itemName = properties[0];
                        if (itemName.equals(product)){
                            amountValue.setText(properties[1]);
                            if (properties.length == 3) desValue.setText(properties[2]);
                            else desValue.setText("No description");
                            foundItem = true;
                        }
                    }
                    if (!foundItem) 
                    {
                        amountValue.setText("Cannot find the product");
                        desValue.setText("Cannot find the product");
                    }
                }
            }

        });

        productLabel.setBounds(20, 20, 100, 20);
        amountLabel.setBounds(20, 80, 100, 20);
        desLabel.setBounds(20, 140, 100, 20);
        productValue.setBounds(140, 20, 180, 30);
        amountValue.setBounds(140, 80, 180,30);
        desValue.setBounds(140, 140, 180, 30);
        searchButton.setBounds (360, 20, 70, 40);


        
        panel.setLayout(null);
        panel.add(productLabel);
        panel.add(amountLabel);
        panel.add(desLabel);
        panel.add(productValue);
        panel.add(amountValue);
        panel.add(desValue);
        panel.add(searchButton);

        this.add(panel);
        this.setSize(500, 300);
        this.setTitle("SEARCH BAR");
        this.setVisible(true);


    }
}
