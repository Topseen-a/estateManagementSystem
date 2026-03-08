package data.models;


public class GatePass {
    private int id;
    private int residentId;
    private int visitorId;

    public GatePass(int residentId, int visitorId) {
        this.residentId = residentId;
        this.visitorId = visitorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResidentId() {
        return residentId;
    }

    public int getVisitorId() {
        return visitorId;
    }
}