package kirsurabaya.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListRiwayatKendaraan {

    @SerializedName("no_uji")
    @Expose
    private String noUji;
    @SerializedName("no_kendaraan")
    @Expose
    private String noKendaraan;
    @SerializedName("tgl_uji")
    @Expose
    private String tglUji;
    @SerializedName("tglmati")
    @Expose
    private String tglmati;
    @SerializedName("merk")
    @Expose
    private String merk;
    @SerializedName("tipe")
    @Expose
    private String tipe;
    @SerializedName("no_chasis")
    @Expose
    private String noChasis;
    @SerializedName("no_mesin")
    @Expose
    private String noMesin;
    @SerializedName("nm_penguji")
    @Expose
    private Object nmPenguji;
    @SerializedName("nrp")
    @Expose
    private Object nrp;
    @SerializedName("no_seri")
    @Expose
    private Object noSeri;
    @SerializedName("nm_uji")
    @Expose
    private Object nmUji;
    @SerializedName("catatan")
    @Expose
    private Object catatan;

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

    public String getTglmati() {
        return tglmati;
    }

    public void setTglmati(String tglmati) {
        this.tglmati = tglmati;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getNoChasis() {
        return noChasis;
    }

    public void setNoChasis(String noChasis) {
        this.noChasis = noChasis;
    }

    public String getNoMesin() {
        return noMesin;
    }

    public void setNoMesin(String noMesin) {
        this.noMesin = noMesin;
    }

    public Object getNmPenguji() {
        return nmPenguji;
    }

    public void setNmPenguji(Object nmPenguji) {
        this.nmPenguji = nmPenguji;
    }

    public Object getNrp() {
        return nrp;
    }

    public void setNrp(Object nrp) {
        this.nrp = nrp;
    }

    public Object getNoSeri() {
        return noSeri;
    }

    public void setNoSeri(Object noSeri) {
        this.noSeri = noSeri;
    }

    public Object getNmUji() {
        return nmUji;
    }

    public void setNmUji(Object nmUji) {
        this.nmUji = nmUji;
    }

    public Object getCatatan() {
        return catatan;
    }

    public void setCatatan(Object catatan) {
        this.catatan = catatan;
    }

}