package alnur.endtermprojectapi.model;

public class Song extends Media {

    public Song(Integer id, String name, int duration) {
        super(id, name, duration);
    }

    @Override
    public String getType() {
        return "SONG";
    }
}
