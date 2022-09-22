package kirsurabaya.myapplication.model;

/**
 * Created by TIA.WICAKSONO on 7/5/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KendaraanUji {

    @SerializedName("no_kendaraan")
    @Expose
    private String noKendaraan;
    @SerializedName("no_uji")
    @Expose
    private String noUji;
    @SerializedName("nama_pemilik")
    @Expose
    private String namaPemilik;
    @SerializedName("status_kelulusan")
    @Expose
    private String statusKelulusan;

    public String getNoKendaraan() {
        return noKendaraan;
    }

    public void setNoKendaraan(String noKendaraan) {
        this.noKendaraan = noKendaraan;
    }

    public String getNoUji() {
        return noUji;
    }

    public void setNoUji(String noUji) {
        this.noUji = noUji;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getStatusKelulusan() {
        return statusKelulusan;
    }

    public void setStatusKelulusan(String statusKelulusan) {
        this.statusKelulusan = statusKelulusan;
    }

}