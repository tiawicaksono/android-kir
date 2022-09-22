package kirsurabaya.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HasilUji {

    @SerializedName("no_uji")
    @Expose
    private String noUji;
    @SerializedName("no_kendaraan")
    @Expose
    private String noKendaraan;
    @SerializedName("pemilik")
    @Expose
    private String pemilik;
    @SerializedName("hasil_tgl_terakhir_uji")
    @Expose
    private String hasilTglTerakhirUji;
    @SerializedName("hasil_tgl_mati_uji")
    @Expose
    private String hasilTglMatiUji;
    @SerializedName("hasil_prauji")
    @Expose
    private String hasilPrauji;
    @SerializedName("hasil_emisi")
    @Expose
    private String hasilEmisi;
    @SerializedName("hasil_pitlift")
    @Expose
    private String hasilPitlift;
    @SerializedName("hasil_lampu")
    @Expose
    private String hasilLampu;
    @SerializedName("hasil_break")
    @Expose
    private String hasilBreak;
    @SerializedName("ltl")
    @Expose
    private String ltl;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("kondisi")
    @Expose
    private String kondisi;
    @SerializedName("img_depan")
    @Expose
    private String imgDepan;
    @SerializedName("img_belakang")
    @Expose
    private String imgBelakang;
    @SerializedName("img_kanan")
    @Expose
    private String imgKanan;
    @SerializedName("img_kiri")
    @Expose
    private String imgKiri;

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

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public String getHasilTglTerakhirUji() {
        return hasilTglTerakhirUji;
    }

    public void setHasilTglTerakhirUji(String hasilTglTerakhirUji) {
        this.hasilTglTerakhirUji = hasilTglTerakhirUji;
    }

    public String getHasilTglMatiUji() {
        return hasilTglMatiUji;
    }

    public void setHasilTglMatiUji(String hasilTglMatiUji) {
        this.hasilTglMatiUji = hasilTglMatiUji;
    }

    public String getHasilPrauji() {
        return hasilPrauji;
    }

    public void setHasilPrauji(String hasilPrauji) {
        this.hasilPrauji = hasilPrauji;
    }

    public String getHasilEmisi() {
        return hasilEmisi;
    }

    public void setHasilEmisi(String hasilEmisi) {
        this.hasilEmisi = hasilEmisi;
    }

    public String getHasilPitlift() {
        return hasilPitlift;
    }

    public void setHasilPitlift(String hasilPitlift) {
        this.hasilPitlift = hasilPitlift;
    }

    public String getHasilLampu() {
        return hasilLampu;
    }

    public void setHasilLampu(String hasilLampu) {
        this.hasilLampu = hasilLampu;
    }

    public String getHasilBreak() {
        return hasilBreak;
    }

    public void setHasilBreak(String hasilBreak) {
        this.hasilBreak = hasilBreak;
    }

    public String getLtl() {
        return ltl;
    }

    public void setLtl(String ltl) {
        this.ltl = ltl;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getImgDepan() {
        return imgDepan;
    }

    public void setImgDepan(String imgDepan) {
        this.imgDepan = imgDepan;
    }

    public String getImgBelakang() {
        return imgBelakang;
    }

    public void setImgBelakang(String imgBelakang) {
        this.imgBelakang = imgBelakang;
    }

    public String getImgKanan() {
        return imgKanan;
    }

    public void setImgKanan(String imgKanan) {
        this.imgKanan = imgKanan;
    }

    public String getImgKiri() {
        return imgKiri;
    }

    public void setImgKiri(String imgKiri) {
        this.imgKiri = imgKiri;
    }

}