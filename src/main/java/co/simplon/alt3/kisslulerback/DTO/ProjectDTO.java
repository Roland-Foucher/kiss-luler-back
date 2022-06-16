package co.simplon.alt3.kisslulerback.DTO;

import java.time.LocalDate;
import java.time.Period;

import co.simplon.alt3.kisslulerback.enums.Category;

public class ProjectDTO {
    
    private int id;
    private String title;
    private String userName;
    private String photo;
    private Category category;
    private Double considerations;
    private String date; //date end - date start

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

    public Double getConsiderations() {
        return considerations;
    }

    public void setConsiderations(Double considerations) {
        this.considerations = considerations;
    }

    public String getDate() {
        return date;
    }

    public void setDate(LocalDate date) {

        this.date =  "J - " + Period.between(LocalDate.now(), date).getDays();

       

    }
}
