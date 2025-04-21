package UPA;

import java.util.*;

/**
 * This is a simple User Authentication console program
 * that uses simple randomness shuffle to make a secure password.
 * 
 * @author Andreas Zelele
 */
public class UserPassAuth {
    private String pwd;
    private final Map<String, List<String>>  storage = new HashMap<>();
    private final String store = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+[]{}|;:'\",.<>?/\\`";
    private String key;
    private final Random rand = new Random();
    UserPassAuth(){
    }

    /**
     * This is encrypting password method that will be used to make a secure password
     * using a random key generated each time it is used.
     * @param user the username
     * @param password the password
     * */
    private void encryptPassword(String user, String password){
        key = "";
        // append all the Characters that we have in list of characters
        List<Character> tempStore = new ArrayList<>();
        for(Character c : store.toCharArray()){
            tempStore.add(c);
        }
        // shuffle them and store them as a key
        Collections.shuffle(tempStore);
        for (Character c : tempStore){
            key += c;
        }
        pwd= "";
        // based on the generated key grab the corresponding letter and append. making a secure password
        for (char c : password.toCharArray()){
            int ind = store.indexOf(c);
            // if a character in the given password is not in the store of characters
            // let the uer know we don't have such character
            if (ind == -1){
                System.out.println("Invalid character in the given Password");
                return;
            }
            char let = key.charAt(ind);
            pwd += let;
        }
        // store the generated password and the key as a value and the username as a key in a Hashmap.
        storage.put(user, List.of(key, pwd));
    }
    /**
     * this is the decrypting method that takes the key and the password and gets the
     * correct stored password using the key and returns the decrypted password
     * @param key generated key
     * @param password given password
     * @return decrypted version of the stored password
     * */
    private String decryptPassword(String key, String password){
        // from a generated key from the user and a new password
        // get a password that matches what is given and return it.
        pwd= "";
        for (char c : password.toCharArray()) {
            int ind = key.indexOf(c);
            char let = store.charAt(ind);
            pwd += let;
        }
        return pwd;
    }
    /**
     * the check password grabs the store of the characters and the password
     * and uses the helper method decrypt password to check if the stored password and the given
     * password match
     * @param user the username
     * @param password the password that is being checked
     * */

    private boolean checkPassword(String user, String password){
        // grab the user's information from the Map
        List<String> shop = storage.get(user);
        if(shop == null){
            return false;
        }
        // get the decrypted version of the password
        String savedPass = decryptPassword(shop.getFirst(), shop.getLast());
        // check if the stored and the new passwords match and return a boolean
        return Objects.equals(savedPass, password);
    }
    /**
     * this runProgram method is used to simulate the whole program
     * asks the user for a username and password and crosschecks the password after encrypted
     * */
    private void runProgram(){
        boolean run = true;
        while(run){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter your username: ");
            String user = scanner.nextLine();
            if (storage.containsKey(user)){
                System.out.println("Please enter your password to check: ");
                String password = scanner.nextLine();
                if(checkPassword(user, password.strip())){
                    System.out.println("Your password is correct");
                }else{
                    System.out.println("Your password is incorrect");
                }
            } else{
                System.out.println("User not found, Please enter your password: ");
                String password = scanner.nextLine();
                encryptPassword(user, password);
            }
            System.out.println("To check the password press (a) or (q) to quit: ");
            String command = scanner.nextLine();
            if(command.equals("q")){
                run = false;
            }
        }
    }


    public static void main(String[] args) {
        UserPassAuth auth = new UserPassAuth();
        auth.runProgram();
    }
}
