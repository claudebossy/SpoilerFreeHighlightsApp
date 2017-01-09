package com.example.spoilerfreehighlights;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by claude on 14.12.16.
 */

public class GameResult {

    private String homeTeam;
    private Integer awayPoints;
    private String date;
    private String gameId;
    private Integer homePoints;
    private String awayTeam;
    private String highlights;

    @JsonCreator
    public GameResult(@JsonProperty("home_team") String homeTeam,
                      @JsonProperty("away_points") Integer awayPoints,
                      @JsonProperty("date") String date,
                      @JsonProperty("game_id") String gameId,
                      @JsonProperty("home_points") Integer homePoints,
                      @JsonProperty("away_team") String awayTeam,
                      @JsonProperty("highlights") String highlights) {

        this.awayTeam = awayTeam;
        this.awayPoints = awayPoints;
        this.homePoints = homePoints;
        this.gameId = gameId;
        this.highlights = highlights;
        this.homeTeam = homeTeam;
        this.date = date;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Integer getAwayPoints() {
        return awayPoints;
    }

    public void setAwayPoints(Integer awayPoints) {
        this.awayPoints = awayPoints;
    }

    public Integer getHomePoints() {
        return homePoints;
    }

    public void setHomePoints(Integer homePoints) {
        this.homePoints = homePoints;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
