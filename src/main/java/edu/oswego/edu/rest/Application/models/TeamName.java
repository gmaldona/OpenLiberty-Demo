package edu.oswego.edu.rest.Application.models;

// import javax.persistence.Id;

import lombok.Data;

@Data // getters, setters, toString, Equals, HashCode
public class TeamName {

    // @Id 
    // private int id;  // primary key

    private String nameA;
    private String nameB;
    private String yearA;
    private String yearB;

}
