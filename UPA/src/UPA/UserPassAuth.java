package UPA;

import java.util.*;

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
    private String pwd;
    private final Map<String, List<String>>  storage = new HashMap<>();
    private final String store = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*";
    private String key;
    private final Random rand = new Random();
    UserPassAuth(){
    }

    private void encryptPassword(String user, String password){
        key = "";
        for(int i = 0; i < store.length()+1; i++){
            char letter = store.charAt(rand.nextInt(store.length()));
            key += letter;
        }
        pwd= "";
        for (char c : password.toCharArray()){
            int ind = store.indexOf(c);
            char let = key.charAt(ind);
            pwd += let;
        }
        System.out.println(pwd);

        storage.put(user, List.of(key, pwd));
    }
    private String decryptPassword(String key, String password){
        pwd= "";
        for (char c : password.toCharArray()) {
            int ind = key.indexOf(c);
            char let = store.charAt(ind);
            pwd += let;
        }
        System.out.println(pwd);
        return pwd;
    }

    private boolean checkPassword(String user, String password){
        List<String> shop = storage.get(user);
        if(shop == null){
            return false;
        }
        String savedPass = decryptPassword(shop.getFirst(), shop.getLast());

        return Objects.equals(savedPass, password);
    }

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
