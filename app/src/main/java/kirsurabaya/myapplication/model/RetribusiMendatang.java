package kirsurabaya.myapplication.model;

/**
 * Created by TIA.WICAKSONO on 5/25/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetribusiMendatang {

    @SerializedName("no_uji")
    @Expose
    private String noUji;
    @SerializedName("no_kendaraan")
    @Expose
    private String noKendaraan;
    @SerializedName("tgl_uji")
    @Expose
    private String tglUji;
    @SerializedName("retribusi")
    @Expose
    private String retribusi;
    @SerializedName("buku")
    @Expose
    private String buku;
    @SerializedName("denda")
    @Expose
    private String denda;
    @SerializedName("total")
    @Expose
    private String total;

    public String getNoUji() {
        return noUji;
    }

    public void setNoUji(String noUji) {
        this.noUji = noUji;
    }

    public String getNoKendaraan() {
        return noKendaraan;
    }

    public void setNoKendaraan(String noKendaraan) {
        this.noKendaraan = noKendaraan;
    }

    public String getTglUji() {
        return tglUji;
    }

    public void setTglUji(String tglUji) {
        this.tglUji = tglUji;
    }

    public String getRetribusi() {
        return retribusi;
    }

    public void setRetribusi(String retribusi) {
        this.retribusi = retribusi;
    }

    public String getBuku() {
        return buku;
    }

    public void setBuku(String buku) {
        this.buku = buku;
    }

    public String getDenda() {
        return denda;
    }

    public void setDenda(String denda) {
        this.denda = denda;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}
