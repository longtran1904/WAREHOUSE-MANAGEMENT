/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic;

/**
 * p
 *
 * @author Student
 */
public class Item {
    private String product;
    private String amount;
    private String description;

    public Item(String fst, String snd, String des) {
        this.product = fst;
        this.amount = snd;
        if (des != null) this.description = des;
    }

    public Item() {
	}

    public void processString(String input) {
        if (input != null) {
            String[] words = input.split(" ");
            if (words.length >= 2) 
            {
                setProduct(words[0]);
                setAmount(words[1]);
                if (words.length == 3)
                    setDescription(words[2]);
            }
        }
    }

    public boolean notEmpty(){
        if (getProduct() != null && getAmount() != null) return true;
        return false;
    }

    //setters
    public void setProduct(String product) {
        this.product = product;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    // getters
    public String getProduct() {
        return product;
    }
    public String getAmount() {
        return amount;
    }
    public String getDescription() {
        return description;
    }

    public String toString() {
        return getProduct() + " " + getAmount() + (getDescription() == null ? "" : " " + getDescription());
    }
}
