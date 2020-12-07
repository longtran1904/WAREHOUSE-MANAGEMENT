package GUI;

public class Capitalize {
    public static String set(String st){
        st = st.substring(0,1).toUpperCase() + st.substring(1);
        return st;
    }
}
