package kirsurabaya.myapplication.service;

import kirsurabaya.myapplication.model.CekKendaraan;
import kirsurabaya.myapplication.model.HasilUji;
import kirsurabaya.myapplication.model.JumlahKendaraanUji;
import kirsurabaya.myapplication.model.KendaraanUji;
import kirsurabaya.myapplication.model.ListRiwayatKendaraan;
import kirsurabaya.myapplication.model.Persyaratan;
import kirsurabaya.myapplication.model.RetribusiMendatang;
import kirsurabaya.myapplication.model.StatusRekom;
import kirsurabaya.myapplication.model.SaveRetribusi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by TIA.WICAKSONO on 5/4/2017.
 */

public interface APIService {
    @FormUrlEncoded
    @POST("DetailPersyaratan")
    Call<List<Persyaratan>> getPersyaratan(@Field("kategori") String kategori);

    @FormUrlEncoded
    @POST("DetailKendaraan")
    Call<CekKendaraan> getDataKendaraan(@Field("noUji") String noUji);

    @FormUrlEncoded
    @POST("HasilUJi")
    Call<HasilUji> getHasilUji(@Field("noUji") String noUji);

    @FormUrlEncoded
    @POST("StatusRekom")
    Call<StatusRekom> getStatusRekom(@Field("noUji") String noUji);

    @FormUrlEncoded
    @POST("ListKendaraan")
    Call<List<KendaraanUji>> getKendaraanUji(@Field("index") int index, @Field("noUji") String noUji);

    @POST("TotalListKendaraan")
    Call<JumlahKendaraanUji> getTotalListKendaraan();

    @FormUrlEncoded
    @POST("ListRiwayatKendaraan")
    Call<List<ListRiwayatKendaraan>> getRiwayatKendaraan(@Field("noUji") String noUji);

    @FormUrlEncoded
    @POST("RetribusiMendatang")
    Call<RetribusiMendatang> getRetribusiMendatang(@Field("noUji") String noUji);

    @FormUrlEncoded
    @POST("SaveRetribusi")
    Call<SaveRetribusi> setRetribusi(@Field("idKendaraan") String idKendaraan, @Field("tglUji") String tglUji, @Field("tujuan") String tujuan, @Field("nikPemohon") String nikPemohon, @Field("namaPemohon") String namaPemohon);
}
