package co.simplon.alt3.kisslulerback.entites;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.print.DocFlavor.STRING;
import co.simplon.alt3.kisslulerback.enums.Category;
import co.simplon.alt3.kisslulerback.enums.Status;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private STRING name;

    private STRING photo;

    @Column(columnDefinition = "TINYTEXT")
    private STRING description;

    @Column(nullable = false)
    private LocalDate dateInit;

    @Column(nullable = false)
    private LocalDate dateEnd;

    @Column(columnDefinition = "ENUM('INPROGRESS', 'CONCEPTION' , 'BLACKLISTED', 'PAUSED')", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(columnDefinition = "ENUM('TOURDATE','EP','CD','EVENT','INSTRUMENT','COMMUNICATION')", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "consideration", cascade = CascadeType.REMOVE)
    private List<Consideration> considerations = new ArrayList<>();

    public Project() {
    }

    public Project(int id, STRING name, STRING photo, STRING description, LocalDate dateInit, LocalDate dateEnd) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.description = description;
        this.dateInit = dateInit;
        this.dateEnd = dateEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public STRING getName() {
        return name;
    }

    public void setName(STRING name) {
        this.name = name;
    }

    public STRING getPhoto() {
        return photo;
    }

    public void setPhoto(STRING photo) {
        this.photo = photo;
    }

    public STRING getDescription() {
        return description;
    }

    public void setDescription(STRING description) {
        this.description = description;
    }

    public LocalDate getDateInit() {
        return dateInit;
    }

    public void setDateInit(LocalDate dateInit) {
        this.dateInit = dateInit;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Consideration> getConsiderations() {
        return considerations;
    }
}
