package kirsurabaya.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TIA.WICAKSONO on 5/29/2017.
 */

public class StatusRekom {

    @SerializedName("no_uji")
    @Expose
    private String noUji;
    @SerializedName("no_kendaraan")
    @Expose
    private String noKendaraan;
    @SerializedName("tgl_rekom")
    @Expose
    private String tglRekom;
    @SerializedName("rekom")
    @Expose
    private String rekom;
    @SerializedName("kasubag")
    @Expose
    private Integer kasubag;
    @SerializedName("kaupt")
    @Expose
    private Integer kaupt;
    @SerializedName("kadis")
    @Expose
    private Integer kadis;
    @SerializedName("tglKasubag")
    @Expose
    private String tglKasubag;
    @SerializedName("tglKaupt")
    @Expose
    private String tglKaupt;
    @SerializedName("tglKadis")
    @Expose
    private String tglKadis;
    @SerializedName("lokasiRekom")
    @Expose
    private String lokasiRekom;
    private final static long serialVersionUID = -7573292359512502377L;

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

    public String getTglRekom() {
        return tglRekom;
    }

    public void setTglRekom(String tglRekom) {
        this.tglRekom = tglRekom;
    }

    public String getRekom() {
        return rekom;
    }

    public void setRekom(String rekom) {
        this.rekom = rekom;
    }

    public Integer getKasubag() {
        return kasubag;
    }

    public void setKasubag(Integer kasubag) {
        this.kasubag = kasubag;
    }

    public Integer getKaupt() {
        return kaupt;
    }

    public void setKaupt(Integer kaupt) {
        this.kaupt = kaupt;
    }

    public Integer getKadis() {
        return kadis;
    }

    public void setKadis(Integer kadis) {
        this.kadis = kadis;
    }

    public String getTglKasubag() {
        return tglKasubag;
    }

    public void setTglKasubag(String tglKasubag) {
        this.tglKasubag = tglKasubag;
    }

    public String getTglKaupt() {
        return tglKaupt;
    }

    public void setTglKaupt(String tglKaupt) {
        this.tglKaupt = tglKaupt;
    }

    public String getTglKadis() {
        return tglKadis;
    }

    public void setTglKadis(String tglKadis) {
        this.tglKadis = tglKadis;
    }

    public String getLokasiRekom() {
        return lokasiRekom;
    }

    public void setLokasiRekom(String lokasiRekom) {
        this.lokasiRekom = lokasiRekom;
    }

}
