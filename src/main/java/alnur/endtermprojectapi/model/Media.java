package alnur.endtermprojectapi.model;

public abstract class Media {

    protected Integer id;
    protected String name;
    protected int duration;
    protected boolean isDeleted;

    public Media(Integer id, String name, int duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public int getDuration() { return duration; }
    public abstract String getType();
    public boolean isDeleted() { return isDeleted; }

    public void setId(Integer id) { this.id = id; }
}
