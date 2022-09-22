package kirsurabaya.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveRetribusi {

    @SerializedName("vaBankJatim")
    @Expose
    private String vaBankJatim;
    @SerializedName("nik_pemohon")
    @Expose
    private String nikPemohon;
    @SerializedName("nama_pemohon")
    @Expose
    private String namaPemohon;
    @SerializedName("b_lulus_uji")
    @Expose
    private String bLulusUji;
    @SerializedName("b_tlt_uji")
    @Expose
    private String bTltUji;
    @SerializedName("b_buku_uji")
    @Expose
    private String bBukuUji;
    @SerializedName("total_bayar")
    @Expose
    private String totalBayar;
    @SerializedName("cekTerdaftar")
    @Expose
    private String cekTerdaftar;
    @SerializedName("statusResponse")
    @Expose
    private String statusResponse;
    @SerializedName("keteranganResponse")
    @Expose
    private String keteranganResponse;

    public String getVaBankJatim() {
        return vaBankJatim;
    }

    public void setVaBankJatim(String vaBankJatim) {
        this.vaBankJatim = vaBankJatim;
    }

    public String getNikPemohon() {
        return nikPemohon;
    }

    public void setNikPemohon(String nikPemohon) {
        this.nikPemohon = nikPemohon;
    }

    public String getNamaPemohon() {
        return namaPemohon;
    }

    public void setNamaPemohon(String namaPemohon) {
        this.namaPemohon = namaPemohon;
    }

    public String getBLulusUji() {
        return bLulusUji;
    }

    public void setBLulusUji(String bLulusUji) {
        this.bLulusUji = bLulusUji;
    }

    public String getBTltUji() {
        return bTltUji;
    }

    public void setBTltUji(String bTltUji) {
        this.bTltUji = bTltUji;
    }

    public String getBBukuUji() {
        return bBukuUji;
    }

    public void setBBukuUji(String bBukuUji) {
        this.bBukuUji = bBukuUji;
    }

    public String getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(String totalBayar) {
        this.totalBayar = totalBayar;
    }

    public String getCekTerdaftar() {
        return cekTerdaftar;
    }

    public void setCekTerdaftar(String cekTerdaftar) {
        this.cekTerdaftar = cekTerdaftar;
    }

    public String getStatusResponse() {
        return statusResponse;
    }

    public void setStatusResponse(String statusResponse) {
        this.statusResponse = statusResponse;
    }

    public String getKeteranganResponse() {
        return keteranganResponse;
    }

    public void setKeteranganResponse(String keteranganResponse) {
        this.keteranganResponse = keteranganResponse;
    }

}
