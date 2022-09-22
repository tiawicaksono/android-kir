package kirsurabaya.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TIA.WICAKSONO on 5/4/2017.
 */

public class Persyaratan {

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    public Persyaratan() {
    }

    public Persyaratan(String keterangan) {
        super();
        this.keterangan = keterangan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}
