package com.players.repository.moel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Supposed to work with PlayersRepository (see comment there)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "player")
public class Player {
    @Id
    @Column(name = "playerID")
    private String playerID;
    @Column
    private String birthYear;
    @Column
    private String birthMonth;
    @Column
    private String birthDay;
    @Column
    private String birthCountry;
    @Column
    private String birthState;
    @Column
    private String birthCity;
    @Column
    private String deathYear;
    @Column
    private String deathMonth;
    @Column
    private String deathDay;
    @Column
    private String deathCountry;
    @Column
    private String deathState;
    @Column
    private String deathCity;
    @Column
    private String nameFirst;
    @Column
    private String nameLast;
    @Column
    private String nameGiven;
    @Column
    private String weight;
    @Column
    private String height;
    @Column
    private String bats;
    @Column(name = "throws")
    private String throwz;
    @Column
    private String debut;
    @Column
    private String finalGame;
    @Column
    private String retroID;
    @Column
    private String bbrefID;
}
