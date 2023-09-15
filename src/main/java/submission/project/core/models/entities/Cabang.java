package submission.project.core.models.entities;

public class Cabang {

    private String id;
    private String namaCabang;

    public Cabang() {
    }

    public Cabang(String id, String namaCabang) {
        this.id = id;
        this.namaCabang = namaCabang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaCabang() {
        return namaCabang;
    }

    public void setNamaCabang(String namaCabang) {
        this.namaCabang = namaCabang;
    }
}
