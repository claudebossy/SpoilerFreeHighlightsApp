package com.example.spoilerfreehighlights;

/**
 * Created by claude on 14.12.16.
 */

public class GameResult {

    private String homeTeam;
    private Integer homeScore;
    private String awayTeam;
    private Integer awayScore;
    private String gameId;
    private String highlightsUrl;
    private String date;

    public GameResult(String homeTeam, Integer homeScore, String awayTeam, Integer awayScore,
                      String gameId, String highlightsUrl, String date) {

        this.homeTeam = homeTeam;
        this.homeScore = homeScore;
        this.awayTeam = awayTeam;
        this.awayScore = awayScore;
        this.gameId = gameId;
        this.highlightsUrl = highlightsUrl;
        this.date = date;
    }

    public static GameResult of(String homeTeam, Integer homeScore, String awayTeam,
                                Integer awayScore, String gameId, String highlightsUrl,
                                String date) {

        return new GameResult(homeTeam, homeScore, awayTeam, awayScore, gameId, highlightsUrl,
                date);
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Integer getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Integer getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(Integer awayScore) {
        this.awayScore = awayScore;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getHighlightsUrl() {
        return highlightsUrl;
    }

    public void setHighlightsUrl(String highlightsUrl) {
        this.highlightsUrl = highlightsUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
