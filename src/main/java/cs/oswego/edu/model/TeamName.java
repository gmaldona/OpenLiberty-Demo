package cs.oswego.edu.model;
// import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data // getters, setters, toString, Equals, HashCode
@NoArgsConstructor
@AllArgsConstructor
public class TeamName {

    // @Id 
    // private int id;  // primary key
    @Id
    private String teamName;
    public String nameA;
    public String nameB;
    public String yearA;
    public String yearB;


    public String generateTeamName() {
        this.teamName = nameA + yearA + nameB + yearB;
        return teamName;
    }

}
