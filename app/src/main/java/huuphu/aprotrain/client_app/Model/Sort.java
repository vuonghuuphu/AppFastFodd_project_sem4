package huuphu.aprotrain.client_app.Model;

public class Sort {
    private boolean sorted;
    private boolean empty;
    private boolean unsorted;


    // Getter Methods

    public boolean getSorted() {
        return sorted;
    }

    public boolean getUnsorted() {
        return unsorted;
    }

    public boolean getEmpty() {
        return empty;
    }

    // Setter Methods

    public void setSorted(boolean sorted) {
        this.sorted = sorted;
    }

    public void setUnsorted(boolean unsorted) {
        this.unsorted = unsorted;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

}
