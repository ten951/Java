package com.wyt.headfirst.lambda;

import java.util.List;

/**
 * @author Darcy
 *         Created by Administrator on 2016/9/8.
 */
public class Album {
    private String name;
    private List<Track> tracks;
    private String musicians;

    public Artist getMainMusician() {
        return new Artist();
    }
    public String getMusicians() {
        return musicians;
    }

    public void setMusicians(String musicians) {
        this.musicians = musicians;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
