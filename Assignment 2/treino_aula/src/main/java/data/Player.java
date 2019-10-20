package data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: data.Player
 *
 */
@Entity
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name;
    @Temporal(TemporalType.DATE)
    private Date birth;
    private float height;
    @ManyToOne
    private Team team;

    public Player() {
        super();
    }

    public Player(String name, Date birth, float height, Team team) {
        super();
        this.name = name;
        this.birth = birth;
        this.height = height;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return this.name + " id = " + this.id + ", " + this.height /*+ " plays for " + this.team.getName() */+ ". Born on " + this.birth;
        //return getName() + getHeight() + getTeam() + getBirth();
    }

}