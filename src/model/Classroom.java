package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Classroom {
    private static final String SEPARATE=";";

    private ArrayList<UserAccount> users;

    public Classroom() {
        users = new ArrayList<UserAccount>();

    }

    public void addUser(String username, String password, String photo, String gender, String careers, String birthday,
            String browser) {

        users.add(new UserAccount(username, password, photo, gender, careers, birthday, browser));
    }

    public ArrayList<UserAccount> getUsers() {
        return users;
    }

    public UserAccount getUser(String name, String password) {
        UserAccount user = null;
        for (UserAccount s : users) {
            if (s.getUsername().equals(name)) {
                if (s.getPassword().equals(password))
                    user = s;
            }
        }
        return user;
    }


    public void importData(String fileName) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        while(line!=null){
          String[] parts = line.split(SEPARATE);
          addUser(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5],parts[6]);
          line = br.readLine();
        }
        br.close();
      }
    
    
      public void exportData(String fileName) throws FileNotFoundException{
        PrintWriter pw = new PrintWriter(fileName);
        for(UserAccount contact : users){
          pw.println(contact.getUsername()+SEPARATE+contact.getPassword()+SEPARATE+contact.getPhoto() +SEPARATE+contact.getGender() +SEPARATE+contact.getCareers() +SEPARATE+contact.getBirthday() +SEPARATE+contact.getFavoriteBrowser());
        }
        pw.close();
      }


}
