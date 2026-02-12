package alnur.endtermprojectapi.model;

public class Podcast extends Media {

    public Podcast(Integer id, String name, int duration) {
        super(id, name, duration);
    }

    @Override
    public String getType() {
        return "PODCAST";
    }
}
