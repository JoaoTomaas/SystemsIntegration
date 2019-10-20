package data;

import data.Player;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    int id;
    private String name;
    private String address;
    private String presidentname;
    @OneToMany(mappedBy="team")
    private List<Player> players;


    public Team() {
        super();
    }

    public Team(String name, String address, String presidentname) {
        super();
        this.name = name;
        this.address = address;
        this.presidentname = presidentname;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPresidentname() {
        return presidentname;
    }
    public void setPresidentname(String presidentname) {
        this.presidentname = presidentname;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}