package model;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Classroom {

    private ArrayList<UserAccount> users;

    public Classroom() {
        users = new ArrayList<UserAccount>();

    }

    public void addUser(String username, String password, Image photo, String pGender, String careers, String birthday,
            String browsers) {

        Gender gender = Gender.valueOf(pGender);

        users.add(new UserAccount(username, password, photo, gender, careers, birthday, browsers));
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

}
