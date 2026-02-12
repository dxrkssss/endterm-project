package alnur.endtermprojectapi.dto;

public class MediaCreateRequest {
    private String name;
    private int duration;
    private String type;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
