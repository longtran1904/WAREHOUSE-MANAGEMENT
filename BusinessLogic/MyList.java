package BusinessLogic;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class MyList {
    private JList<Item> list;
    DefaultListModel<Item> model;
    private static MyList instance = null;

    public static MyList getInstance() {
        if (instance == null)
            instance = new MyList();
        return instance;

    }

    private MyList() {
        list = new JList<>();
        model = new DefaultListModel<>();

        list.setModel(model);
    }

    

    public JList<Item> getList(){
        return list;
    }
    public int isDuplicate(String product) {

        for (int i = 0; i < model.size(); i++) 
        {
            Item item = model.getElementAt(i);
            if (product.equals(item.getProduct())) 
                return i;
        }
        return -1;
    }
    public void addItem(String product, String amount, String des) {
        model.addElement(new Item(product, amount, des));
        list.setModel(model);
    }

    private void removeItem(int idx) {
        model.removeElementAt(idx);
        list.setModel(model);
    }

    public void decreaseItemAmount(String product, String amount, String des, int idx){
        int count = Integer.parseInt(amount);
        count = Integer.parseInt(model.elementAt(idx).getAmount()) - count; 
        if (des.isEmpty()) des = model.elementAt(idx).getDescription();
        if (count > 0) 
        {
            model.setElementAt(new Item(product, String.valueOf(count), des) , idx);
            list.setModel(model);
        }
        else removeItem(idx);
    }

    public void addItemAmount(String product, String amount, String des, int idx) {
        int sum = Integer.parseInt(amount);
        sum += Integer.parseInt(model.elementAt(idx).getAmount());
        if (des.isEmpty()) des = model.elementAt(idx).getDescription();
        model.setElementAt(new Item(product, String.valueOf(sum), des) , idx);
        list.setModel(model);
    }
    public List<String> getModel() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < model.size();i++)
            list.add(model.elementAt(i).toString());
        return list;
    }

    public void initiateData(List<String> input) {
        if (input.size()>0){
            for (String i : input) {
                Item newItem = new Item();
                newItem.processString(i);
                if (newItem.notEmpty())
                    model.addElement(newItem);
            }
        }
    }



}
