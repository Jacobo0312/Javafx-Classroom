package model;
import javafx.scene.image.Image;


public class UserAccount {
private String username;
private String password;
private Image photo;
private Gender gender;
private String careers;
private String birthday;
private String favoriteBrowser;

public UserAccount(String username, String password, Image photo, Gender gender,String careers, String birthday, String favoriteBrowser) {
    this.username = username;
    this.password = password;
    this.photo = photo;
    this.gender = gender;
    this.careers=careers;
    this.birthday = birthday;
    this.favoriteBrowser = favoriteBrowser;
}

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Image getPhoto() {
        return this.photo;
    }

    public Gender getGender() {
        return this.gender;
    }

    public String getCareers() {
        return this.careers;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public String getFavoriteBrowser() {
        return this.favoriteBrowser;
    }

    
}
    
