package kirsurabaya.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CekKendaraan {

    @SerializedName("id_kendaraan")
    @Expose
    private Integer idKendaraan;
    @SerializedName("no_uji")
    @Expose
    private String noUji;
    @SerializedName("no_kendaraan")
    @Expose
    private String noKendaraan;
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
    @SerializedName("pemilik")
    @Expose
    private String pemilik;
    @SerializedName("jns_kend")
    @Expose
    private String jnsKend;
    @SerializedName("mati_uji")
    @Expose
    private String matiUji;
    @SerializedName("nama_komersil")
    @Expose
    private String namaKomersil;
    @SerializedName("jenis_karoseri")
    @Expose
    private String jenisKaroseri;
    @SerializedName("bahan_utama")
    @Expose
    private String bahanUtama;
    @SerializedName("panjang")
    @Expose
    private String panjang;
    @SerializedName("lebar")
    @Expose
    private String lebar;
    @SerializedName("tinggi")
    @Expose
    private String tinggi;
    @SerializedName("dimpanjang")
    @Expose
    private String dimpanjang;
    @SerializedName("dimlebar")
    @Expose
    private String dimlebar;
    @SerializedName("dimtinggi")
    @Expose
    private String dimtinggi;
    @SerializedName("jbb")
    @Expose
    private String jbb;
    @SerializedName("orang")
    @Expose
    private String orang;
    @SerializedName("barang")
    @Expose
    private String barang;
    @SerializedName("sb1")
    @Expose
    private String sb1;
    @SerializedName("sb2")
    @Expose
    private String sb2;
    @SerializedName("sb3")
    @Expose
    private String sb3;
    @SerializedName("sb4")
    @Expose
    private String sb4;
    @SerializedName("sb5")
    @Expose
    private String sb5;
    @SerializedName("total_sb")
    @Expose
    private String totalSb;
    @SerializedName("jbi")
    @Expose
    private String jbi;
    @SerializedName("mst")
    @Expose
    private String mst;
    @SerializedName("kondisi")
    @Expose
    private String kondisi;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("tujuan")
    @Expose
    private String tujuan;
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

    public Integer getIdKendaraan() {
        return idKendaraan;
    }

    public void setIdKendaraan(Integer idKendaraan) {
        this.idKendaraan = idKendaraan;
    }

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

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public String getJnsKend() {
        return jnsKend;
    }

    public void setJnsKend(String jnsKend) {
        this.jnsKend = jnsKend;
    }

    public String getMatiUji() {
        return matiUji;
    }

    public void setMatiUji(String matiUji) {
        this.matiUji = matiUji;
    }

    public String getNamaKomersil() {
        return namaKomersil;
    }

    public void setNamaKomersil(String namaKomersil) {
        this.namaKomersil = namaKomersil;
    }

    public String getJenisKaroseri() {
        return jenisKaroseri;
    }

    public void setJenisKaroseri(String jenisKaroseri) {
        this.jenisKaroseri = jenisKaroseri;
    }

    public String getBahanUtama() {
        return bahanUtama;
    }

    public void setBahanUtama(String bahanUtama) {
        this.bahanUtama = bahanUtama;
    }

    public String getPanjang() {
        return panjang;
    }

    public void setPanjang(String panjang) {
        this.panjang = panjang;
    }

    public String getLebar() {
        return lebar;
    }

    public void setLebar(String lebar) {
        this.lebar = lebar;
    }

    public String getTinggi() {
        return tinggi;
    }

    public void setTinggi(String tinggi) {
        this.tinggi = tinggi;
    }

    public String getDimpanjang() {
        return dimpanjang;
    }

    public void setDimpanjang(String dimpanjang) {
        this.dimpanjang = dimpanjang;
    }

    public String getDimlebar() {
        return dimlebar;
    }

    public void setDimlebar(String dimlebar) {
        this.dimlebar = dimlebar;
    }

    public String getDimtinggi() {
        return dimtinggi;
    }

    public void setDimtinggi(String dimtinggi) {
        this.dimtinggi = dimtinggi;
    }

    public String getJbb() {
        return jbb;
    }

    public void setJbb(String jbb) {
        this.jbb = jbb;
    }

    public String getOrang() {
        return orang;
    }

    public void setOrang(String orang) {
        this.orang = orang;
    }

    public String getBarang() {
        return barang;
    }

    public void setBarang(String barang) {
        this.barang = barang;
    }

    public String getSb1() {
        return sb1;
    }

    public void setSb1(String sb1) {
        this.sb1 = sb1;
    }

    public String getSb2() {
        return sb2;
    }

    public void setSb2(String sb2) {
        this.sb2 = sb2;
    }

    public String getSb3() {
        return sb3;
    }

    public void setSb3(String sb3) {
        this.sb3 = sb3;
    }

    public String getSb4() {
        return sb4;
    }

    public void setSb4(String sb4) {
        this.sb4 = sb4;
    }

    public String getSb5() {
        return sb5;
    }

    public void setSb5(String sb5) {
        this.sb5 = sb5;
    }

    public String getTotalSb() {
        return totalSb;
    }

    public void setTotalSb(String totalSb) {
        this.totalSb = totalSb;
    }

    public String getJbi() {
        return jbi;
    }

    public void setJbi(String jbi) {
        this.jbi = jbi;
    }

    public String getMst() {
        return mst;
    }

    public void setMst(String mst) {
        this.mst = mst;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
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