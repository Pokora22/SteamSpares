package sample;

@SuppressWarnings("WeakerAccess")
public class GameEntry {
    private String name, key, note;
    private boolean used;

    public GameEntry(String name, String key) throws IllegalArgumentException{
        if (name == "" || name==null) throw new IllegalArgumentException("Name can't be empty");
        else this.name = name;
        if (key == "" || key==null) throw new IllegalArgumentException("Name can't be empty");
        else this.key = key;
        this.note = "";
        this.used = false;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
