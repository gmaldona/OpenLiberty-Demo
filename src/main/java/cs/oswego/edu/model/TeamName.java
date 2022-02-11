package cs.oswego.edu.model;
// import javax.persistence.Id;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data // getters, setters, toString, Equals, HashCode
public class TeamName {

    // @Id 
    // private int id;  // primary key
    @Id
    private String teamName;
    public String nameA;
    public String nameB;
    public String yearA;
    public String yearB;

    public TeamName(String nameA, String nameB, String yearA, String yearB) {
        this.nameA = nameA;
        this.nameB = nameB;
        this.yearA = yearA;
        this.yearB = yearB;
    }

    public TeamName()  {}

    public String generateTeamName() {
        this.teamName = nameA + yearA + nameB + yearB;
        return teamName;
    }

}
