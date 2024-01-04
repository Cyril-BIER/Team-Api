package com.telecom.apiTeams.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private int jerseyNumber;

    private String role;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Team team;

    @Transient
    private String teamName;

    public String getTeamName() {
        return (team != null) ? team.getName() : null;
    }
}
