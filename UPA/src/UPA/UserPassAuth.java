package UPA;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * the way I am going to implement the methods is not that complicated
 * I am going to make the encryptPassword method with an encryption algorithm.
 * Algorithm:
 * 1, from a string of letters and symbols make a random iteration and form a key
 * 2, from the key go through the letter and find corresponding value of that letter in the
 * string of letters that was created and get the index, then look for the element in the
 * key and append them as a string.(making the password"
 * 3. store the username and the key in a hashmap
 *
 */
public class UserPassAuth {
    private String username;
    private String pwd;
    private final Map<String,String>  storage = new HashMap<>();
    private final String store = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{}|;:',.<>?/`~";
    private String key;
    private Random rand = new Random();
    UserPassAuth(){
        loadUsers();
    }

    private void loadUsers() {
    }

    private void encryptPassword(String username, String password){
        key = "";
        for(int i = 0; i <= store.length(); i++){
            char letter = store.charAt(rand.nextInt(store.length()));
            key += letter;
        }
        pwd= "";
        for (char c : password.toCharArray()){
            int ind = store.indexOf(c);
            char let = key.charAt(ind);
            pwd += let;
        }
        storage.put(username, key);
    }





    public static void main(String[] args) {
        UserPassAuth auth = new UserPassAuth();
        auth.encryptPassword("ninja","password");
    }
}
