package co.simplon.alt3.kisslulerback.DTO;

import java.time.LocalDate;
import co.simplon.alt3.kisslulerback.entites.Consideration;
import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.enums.Category;

public class ProjectsDTO {


    private int id;
    private String title;
    private String userName;
    private String photo;
    private Category category;
    private int considerations;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getConsiderations() {
        return considerations;
    }

    public void setConsiderations(int considerations) {
        this.considerations = considerations;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // considerationsMax



    ;



}
