package alnur.endtermprojectapi.dto;

public class MediaResponse {
    private int id;
    private String name;
    private int duration;
    private String type;

    public MediaResponse(int id, String name, int duration, String type) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.type = type;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getDuration() { return duration; }
    public String getType() { return type; }
}
