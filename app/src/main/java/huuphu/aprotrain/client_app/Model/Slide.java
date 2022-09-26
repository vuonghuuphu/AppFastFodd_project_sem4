package huuphu.aprotrain.client_app.Model;

public class Slide {
    private float id;
    private String thumbnail;
    private String note;
    private String status;


    // Getter Methods

    public float getId() {
        return id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getNote() {
        return note;
    }

    public String getStatus() {
        return status;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Slide(float id, String thumbnail, String note, String status) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.note = note;
        this.status = status;
    }
}