package by.grsu.entity;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employers")
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "centrix", length = 4)
    private String centrix;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "fio", length = 55)
    private String fio;

    @Column(name = "middlename", length = 40)
    private String middlename;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "other", length = 70)
    private String other;

    @Column(name = "surname", length = 50)
    private String surname;

    @Column(name = "teljob", length = 15)
    private String telJob;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "post_id")
    private Post post_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "zues_id")
    private Zues zues_id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "sector_id")
    private Sector sector_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCentrix() {
        return centrix;
    }

    public void setCentrix(String centrix) {
        this.centrix = centrix;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelJob() {
        return telJob;
    }

    public void setTelJob(String telJob) {
        this.telJob = telJob;
    }

    public Post getPost_id() {
        return post_id;
    }

    public void setPost_id(Post post_id) {
        this.post_id = post_id;
    }

    public Sector getSector_id() {
        return sector_id;
    }

    public void setSector_id(Sector sector_id) {
        this.sector_id = sector_id;
    }

    public Zues getZues_id() {
        return zues_id;
    }

    public void setZues_id(Zues zues_id) {
        this.zues_id = zues_id;
    }
}
