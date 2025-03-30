package UPA;

import java.util.Arrays;

public class UserPassAuth {
    private String username;
    private String password;
    UserPassAuth(){
    }

    private void encryptPassword(String password){
        char[] letters = password.toCharArray();
        char[] pass = new char[password.length()];
        for(char letter : letters){
            letter+=5;
            pass[password.length()-1]=letter;
        }
        System.out.println(Arrays.toString(pass));
    }





    public static void main(String[] args) {
        UserPassAuth auth = new UserPassAuth();
        auth.encryptPassword("password");
    }
}
