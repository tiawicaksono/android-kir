package kirsurabaya.myapplication;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import kirsurabaya.myapplication.model.CekKendaraan;
import kirsurabaya.myapplication.model.SaveRetribusi;
import kirsurabaya.myapplication.network.ApiClient;
import kirsurabaya.myapplication.service.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.itextpdf.text.Element.ALIGN_CENTER;
import static com.itextpdf.text.Element.ALIGN_LEFT;


/**
 * Created by TIA.WICAKSONO on 5/28/2018.
 */

public class RegisterFragment extends Fragment {

    private static final int STORAGE_CODE = 1000;
    private FloatingSearchView mSearchView;
    private ProgressDialog pDialog;
    private TextView tvDateResult;
    private Button btDatePicker;
    private Button buttonDaftar;
    private Button buttonCopy;
    private int mYear, mMonth, mDay;
    private String valIdKendaraan, valDateresult, valTujuan, valNikPemohon, valNamaPemohon;
    private EditText txtIdKendaraan, txtDateresult, txtTujuan, fieldNikPemohon, fieldNamaPemohon;
    private ImageView clickPdf;
    private ImageView clickSaveImage;
    private ImageView imgViewPdfNone;
    private ImageView imgViewPdf;
    private Button vButtonDaftarNonAktif;
    private Button vButtonDaftar, clickCoba;
    private ProgressDialog pd;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            view.setMinDate(System.currentTimeMillis() - 1000);
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.register_fragment, container, false);
        pDialog = new ProgressDialog(this.getActivity());
        pDialog.setCancelable(false);
        mSearchView = (FloatingSearchView) view.findViewById(R.id.noUjiKendaraanSearch);
        setupFloatingSearch();
        txtIdKendaraan = (EditText) view.findViewById(R.id.idKendaraan);
        txtDateresult = (EditText) view.findViewById(R.id.tv_dateresult);
        txtTujuan = (EditText) view.findViewById(R.id.tujuan);

        buttonCopy = (Button) view.findViewById(R.id.buttonCopyVa);
        buttonCopy.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                copyVirtualAccount();
            }
        });

        clickPdf = (ImageView) view.findViewById(R.id.downloadPdf);
        clickPdf.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                createPdf();
            }
        });


//        clickSaveImage = (ImageView) view.findViewById(R.id.downloadImage);
//        clickSaveImage.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//                SaveClick();
//            }
//        });
        /**
         * Kita menggunakan format tanggal dd-MM-yyyy
         * jadi nanti tanggal nya akan diformat menjadi
         * misalnya 01-12-2017
         */
//        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        fieldNikPemohon = (EditText) view.findViewById(R.id.txtNikPemohon);
        fieldNamaPemohon = (EditText) view.findViewById(R.id.txtNamaPemohon);
        tvDateResult = (TextView) view.findViewById(R.id.tv_dateresult);
        tanggal();
        btDatePicker = (Button) view.findViewById(R.id.bt_datepicker);
        buttonDaftar = (Button) view.findViewById(R.id.buttonDaftar);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        buttonDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valIdKendaraan = txtIdKendaraan.getText().toString();
                valNikPemohon = fieldNikPemohon.getText().toString();
                valNamaPemohon = fieldNamaPemohon.getText().toString();
                valDateresult = txtDateresult.getText().toString();
                valTujuan = txtTujuan.getText().toString();
//                Toast.makeText(getActivity(), valDateresult, Toast.LENGTH_SHORT).show();
                savePendaftaran(valIdKendaraan, valDateresult, valTujuan, valNikPemohon, valNamaPemohon);
            }

        });

        /**
         * NIK dan NAMA Pemohon harus diisi
         */
        fieldNikPemohon.addTextChangedListener(cekHarusAda);
        fieldNamaPemohon.addTextChangedListener(cekHarusAda);
        pd = new ProgressDialog(this.getActivity());
        return view;
    }

    private TextWatcher cekHarusAda = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nikPemohon = fieldNikPemohon.getText().toString();
            String namaPemohon = fieldNamaPemohon.getText().toString();
            vButtonDaftarNonAktif = (Button) getActivity().findViewById(R.id.buttonDaftarNonAktif);
            vButtonDaftar = (Button) getActivity().findViewById(R.id.buttonDaftar);
            if (!nikPemohon.isEmpty() && !namaPemohon.isEmpty()) {
                vButtonDaftar.setVisibility(View.VISIBLE);
                vButtonDaftarNonAktif.setVisibility(View.GONE);
            } else {
                vButtonDaftar.setVisibility(View.GONE);
                vButtonDaftarNonAktif.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void savePendaftaran(final String valIdKendaraan, final String valDateresult, final String valTujuan, final String valNikPemohon, final String valNamaPemohon) {
        try {
            pDialog.setMessage("Please wait...");
            showDialog();

            APIService services = ApiClient.getClient().create(APIService.class);
            Call<SaveRetribusi> calls = services.setRetribusi(valIdKendaraan, valDateresult, valTujuan, valNikPemohon, valNamaPemohon);
            calls.enqueue(new Callback<SaveRetribusi>() {
                @Override
                public void onResponse(Call<SaveRetribusi> call, Response<SaveRetribusi> response) {
                    try {
                        hideDialog();
                        String getCek = response.body().getCekTerdaftar();
                        if (getCek.equals("1")) {
                            Toast.makeText(getActivity(), "Sudah terdaftar", Toast.LENGTH_SHORT).show();
                            getDataPembayaran(valIdKendaraan, valDateresult, valTujuan, valNikPemohon, valNamaPemohon);
                        } else {
                            String getStatusResponse = response.body().getStatusResponse();
                            if (getStatusResponse.equals("1")) {
                                Toast.makeText(getActivity(), response.body().getKeteranganResponse(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getActivity(), "Berhasil Daftar", Toast.LENGTH_SHORT).show();
                            }
                            getDataPembayaran(valIdKendaraan, valDateresult, valTujuan, valNikPemohon, valNamaPemohon);
                        }
                        imgViewPdf = (ImageView) getActivity().findViewById(R.id.downloadPdf);
                        imgViewPdfNone = (ImageView) getActivity().findViewById(R.id.downloadPdfNone);
                        imgViewPdf.setVisibility(View.VISIBLE);
                        imgViewPdfNone.setVisibility(View.GONE);
                    } catch (Exception e) {
                        e.printStackTrace();

                        imgViewPdf = (ImageView) getActivity().findViewById(R.id.downloadPdf);
                        imgViewPdfNone = (ImageView) getActivity().findViewById(R.id.downloadPdfNone);
                        imgViewPdf.setVisibility(View.GONE);
                        imgViewPdfNone.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<SaveRetribusi> call, Throwable t) {
                    hideDialog();
                    Log.d("onFailure", t.toString());
                    imgViewPdf = (ImageView) getActivity().findViewById(R.id.downloadPdf);
                    imgViewPdfNone = (ImageView) getActivity().findViewById(R.id.downloadPdfNone);
                    imgViewPdf.setVisibility(View.GONE);
                    imgViewPdfNone.setVisibility(View.VISIBLE);
                }

            });
        } catch (Exception e) {
            hideDialog();
        }
    }

    private void tanggal() {
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH) + 1;
        mDay = c.get(Calendar.DAY_OF_MONTH);
        String bulan, tanggal;
        if ((mMonth) < 10) {
            bulan = "0" + String.valueOf(mMonth);
        } else {
            bulan = String.valueOf(mMonth);
        }

        if ((mDay) < 10) {
            tanggal = "0" + String.valueOf(mDay);
        } else {
            tanggal = String.valueOf(mDay);
        }
        tvDateResult.setText(tanggal + "/" + bulan + "/" + mYear);
    }

    private void showDateDialog() {

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
//        Calendar newCalendar = Calendar.getInstance();
        final Calendar newCalendar = Calendar.getInstance();
        mYear = newCalendar.get(Calendar.YEAR);
        mMonth = newCalendar.get(Calendar.MONTH);
        mDay = newCalendar.get(Calendar.DAY_OF_MONTH);

        /**
         * Initiate DatePicker dialog
         */
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String bulan, tanggal;
                        if ((monthOfYear + 1) < 10) {
                            bulan = "0" + String.valueOf(monthOfYear + 1);
                        } else {
                            bulan = String.valueOf(monthOfYear + 1);
                        }

                        if ((dayOfMonth) < 10) {
                            tanggal = "0" + String.valueOf(dayOfMonth);
                        } else {
                            tanggal = String.valueOf(dayOfMonth);
                        }
                        tvDateResult.setText(tanggal + "/" + bulan + "/" + year);
                        view.setMinDate(System.currentTimeMillis() - 1000);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void setupFloatingSearch() {
        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {
                getData(currentQuery);
            }
        });
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void getData(String noUji) {
        try {
            pDialog.setMessage("Please wait...");
            showDialog();

            APIService services = ApiClient.getClient().create(APIService.class);
            Call<CekKendaraan> calls = services.getDataKendaraan(noUji);
            calls.enqueue(new Callback<CekKendaraan>() {

                @Override
                public void onResponse(Call<CekKendaraan> call, Response<CekKendaraan> response) {
                    hideDialog();
                    Integer getIdKendaraan = response.body().getIdKendaraan();
                    String getNoUji = response.body().getNoUji();
                    String getTujuan = response.body().getTujuan();
                    String getNoKendaraan = response.body().getNoKendaraan();
                    String getMerk = response.body().getMerk();
                    String getTipe = response.body().getTipe();
                    String getNoChasis = response.body().getNoChasis();
                    String getNoMesin = response.body().getNoMesin();

                    TextView txtTujuan = (TextView) getActivity().findViewById(R.id.tujuan);
                    TextView txtIdKendaraan = (TextView) getActivity().findViewById(R.id.idKendaraan);
                    TextView txtNoKend = (TextView) getActivity().findViewById(R.id.txtNoKend);
                    TextView txtNoUji = (TextView) getActivity().findViewById(R.id.txtNoUji);
                    TextView txtMerk = (TextView) getActivity().findViewById(R.id.txtMerk);
                    TextView txtTipe = (TextView) getActivity().findViewById(R.id.txtTipe);
                    TextView txtNoChasis = (TextView) getActivity().findViewById(R.id.txtNoChasis);
                    TextView txtNoMesin = (TextView) getActivity().findViewById(R.id.txtNoMesin);
                    TextView txtUptd = (TextView) getActivity().findViewById(R.id.txtUptd);

                    txtIdKendaraan.setText(String.valueOf(getIdKendaraan));
                    txtTujuan.setText(getTujuan);
                    txtNoUji.setText(getNoUji);
                    txtNoKend.setText(getNoKendaraan);
                    txtMerk.setText(getMerk);
                    txtTipe.setText(getTipe);
                    txtNoChasis.setText(getNoChasis);
                    txtNoMesin.setText(getNoMesin);
                    txtUptd.setText("Pengujian Kendaraan Bermotor " + getTujuan);

//                    =================================================

                    TextView txtVa = (TextView) getActivity().findViewById(R.id.txtVaBankJatim);
                    TextView txtBLulusUji = (TextView) getActivity().findViewById(R.id.txtBLulusUji);
                    TextView txtBTelatUji = (TextView) getActivity().findViewById(R.id.txtBTelatUji);
                    TextView txtBBukuUJi = (TextView) getActivity().findViewById(R.id.txtBBukuUji);
                    TextView txtJmlBayar = (TextView) getActivity().findViewById(R.id.txtJmlBayar);

                    txtVa.setText("-");
                    txtBLulusUji.setText("-");
                    txtBTelatUji.setText("-");
                    txtBBukuUJi.setText("-");
                    txtJmlBayar.setText("-");
                }

                @Override
                public void onFailure(Call<CekKendaraan> call, Throwable t) {
                    hideDialog();
                    Log.d("onFailure", t.toString());

                    imgViewPdf = (ImageView) getActivity().findViewById(R.id.downloadPdf);
                    imgViewPdfNone = (ImageView) getActivity().findViewById(R.id.downloadPdfNone);
                    vButtonDaftarNonAktif = (Button) getActivity().findViewById(R.id.buttonDaftarNonAktif);
                    vButtonDaftar = (Button) getActivity().findViewById(R.id.buttonDaftar);
                    imgViewPdf.setVisibility(View.GONE);
                    imgViewPdfNone.setVisibility(View.VISIBLE);
                    vButtonDaftar.setVisibility(View.GONE);
                    vButtonDaftarNonAktif.setVisibility(View.VISIBLE);
                }
            });


        } catch (Exception e) {
            imgViewPdf = (ImageView) getActivity().findViewById(R.id.downloadPdf);
            imgViewPdfNone = (ImageView) getActivity().findViewById(R.id.downloadPdfNone);
            imgViewPdf.setVisibility(View.GONE);
            imgViewPdfNone.setVisibility(View.VISIBLE);
            hideDialog();
        }
    }

    private void getDataPembayaran(String valIdKendaraan, String valDateresult, String valTujuan, String valNikPemohon, String valNamaPemohon) {
        APIService services = ApiClient.getClient().create(APIService.class);
        Call<SaveRetribusi> calls = services.setRetribusi(valIdKendaraan, valDateresult, valTujuan, valNikPemohon, valNamaPemohon);
        calls.enqueue(new Callback<SaveRetribusi>() {

            @Override
            public void onResponse(Call<SaveRetribusi> call, Response<SaveRetribusi> response) {
                String getVaBankJatim = response.body().getVaBankJatim();
                String getNikPemohon = response.body().getNikPemohon();
                String getNamaPemohon = response.body().getNamaPemohon();
                String getBLulusUji = response.body().getBLulusUji();
                String getBTltUji = response.body().getBTltUji();
                String getBBukuUji = response.body().getBBukuUji();
                String getTotalBayar = response.body().getTotalBayar();

                TextView txtVaBankJatim = (TextView) getActivity().findViewById(R.id.txtVaBankJatim);
                TextView txtNikPemohon = (TextView) getActivity().findViewById(R.id.txtPemohonNik);
                TextView txtNamaPemohon = (TextView) getActivity().findViewById(R.id.txtPemohonNama);
                TextView txtBLulusUji = (TextView) getActivity().findViewById(R.id.txtBLulusUji);
                TextView txtBTelatUji = (TextView) getActivity().findViewById(R.id.txtBTelatUji);
                TextView txtBBukuUji = (TextView) getActivity().findViewById(R.id.txtBBukuUji);
                TextView txtJmlBayar = (TextView) getActivity().findViewById(R.id.txtJmlBayar);

                txtVaBankJatim.setText(getVaBankJatim);
                txtNikPemohon.setText(getNikPemohon);
                txtNamaPemohon.setText(getNamaPemohon);
                txtBLulusUji.setText(getBLulusUji);
                txtBTelatUji.setText(getBTltUji);
                txtBBukuUji.setText(getBBukuUji);
                txtJmlBayar.setText(getTotalBayar);
            }

            @Override
            public void onFailure(Call<SaveRetribusi> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }

    private void createPdf() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
//            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, STORAGE_CODE);
            } else {
                savePdf();
            }
        }
    }

    private void savePdf() {
        Document mDoc = new Document();
        String mFileNameDate = new SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(System.currentTimeMillis());
        String mFileName = "KirDishubSurabaya_" + mFileNameDate;
        String mFilePath = Environment.getExternalStorageDirectory() + "/" + mFileName + ".pdf";
        try {
            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));
            mDoc.open();
            String mText = "tata";
            mDoc.setPageSize(PageSize.A4);
            mDoc.setMargins(0, 0, 0, 0);
            mDoc.addCreationDate();
            mDoc.addAuthor("Tia Wicaksono");
            mDoc.addCreator("Helios Adikara Informatika");

            //adding image
            //===================================
            Drawable drawable = getResources().getDrawable(R.drawable.header_pdf);
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] bitmapData = stream.toByteArray();
            //===================================
            Paragraph p = new Paragraph();
            Image i = Image.getInstance(bitmapData);
            i.scaleToFit(500, 500);
            Chunk mOrderDetailsTitleChunk = new Chunk(i, 0, -90);
            p.add(mOrderDetailsTitleChunk);

            Paragraph mOrderDetailsTitleParagraph = new Paragraph(mOrderDetailsTitleChunk);// Setting Alignment for Heading
            mOrderDetailsTitleParagraph.setAlignment(ALIGN_CENTER);// Finally Adding that Chunk
            mDoc.add(mOrderDetailsTitleChunk);

            // Fields of Order Details...
            // Adding Chunks for Title and value
            Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 18.0f, Font.BOLD, BaseColor.BLACK);
            Chunk c1 = new Chunk("PENGUJIAN KENDARAAN BERMOTOR", f1);
            Paragraph p1 = new Paragraph(c1);
            p1.setAlignment(ALIGN_CENTER);
            mDoc.add(p1);

            Font f2 = new Font(Font.FontFamily.TIMES_ROMAN, 22.0f, Font.BOLD, BaseColor.BLACK);
            Chunk c2 = new Chunk("DINAS PERHUBUNGAN SURABAYA", f2);
            Paragraph p2 = new Paragraph(c2);
            p2.setAlignment(ALIGN_CENTER);
            mDoc.add(p2);

            Paragraph paragraph = new Paragraph();
            paragraph.setSpacingBefore(20.0f);
            mDoc.add(paragraph);
            mDoc.add(new LineSeparator(1f, 100, null, ALIGN_CENTER, -5));
            paragraph.setSpacingAfter(20.0f);
            mDoc.add(paragraph);

            PdfPTable table = new PdfPTable(2);
            table.addCell(getCellContent());
            table.addCell(getCellValue());
            mDoc.add(table);

            String satu = "1. Membawa buku uji asli";
            Paragraph paragraph_persyaratan1 = new Paragraph(satu);
            paragraph_persyaratan1.setSpacingBefore(20.0f);
            paragraph_persyaratan1.setSpacingAfter(5.0f);
            paragraph_persyaratan1.setAlignment(ALIGN_LEFT);
            mDoc.add(paragraph_persyaratan1);

            String dua = "2. Membawa STNK asli dan pajak masih hidup";
            Paragraph paragraph_persyaratan2 = new Paragraph(dua);
            paragraph_persyaratan2.setSpacingAfter(5.0f);
            paragraph_persyaratan2.setAlignment(ALIGN_LEFT);
            mDoc.add(paragraph_persyaratan2);

            String tiga = "3. Membawa KTP asli, pemilik kendaraan dan pemohon (sopir)";
            Paragraph paragraph_persyaratan3 = new Paragraph(tiga);
            paragraph_persyaratan3.setSpacingAfter(5.0f);
            paragraph_persyaratan3.setAlignment(ALIGN_LEFT);
            mDoc.add(paragraph_persyaratan3);

            String empat = "4. Surat keterangan domisili bagi kendaraan perusahaan";
            Paragraph paragraph_persyaratan4 = new Paragraph(empat);
            paragraph_persyaratan4.setSpacingAfter(5.0f);
            paragraph_persyaratan4.setAlignment(ALIGN_LEFT);
            mDoc.add(paragraph_persyaratan4);

            String lima = "5. Surat Kuasa bermaterai jika dikuasakan";
            Paragraph paragraph_persyaratan5 = new Paragraph(lima);
            paragraph_persyaratan5.setSpacingAfter(5.0f);
            paragraph_persyaratan5.setAlignment(ALIGN_LEFT);
            mDoc.add(paragraph_persyaratan5);

            String enam = "6. Gesekan nomor rangka, nomor mesin, dan nomor uji";
            Paragraph paragraph_persyaratan6 = new Paragraph(enam);
            paragraph_persyaratan6.setSpacingAfter(5.0f);
            paragraph_persyaratan6.setAlignment(ALIGN_LEFT);
            mDoc.add(paragraph_persyaratan6);

            String tuju = "7. Buku uji yang hilang, wajib melaporkan ke kepolisian, dishub provinsi dan diiklankan media";
            Paragraph paragraph_persyaratan7 = new Paragraph(tuju);
            paragraph_persyaratan7.setSpacingAfter(20.0f);
            paragraph_persyaratan7.setAlignment(ALIGN_LEFT);
            mDoc.add(paragraph_persyaratan7);

            //Step-5: Create QR Code by using BarcodeQRCode Class
            TextView txtVa = (TextView) getActivity().findViewById(R.id.txtVaBankJatim);
            BarcodeQRCode barcodeQRCode = new BarcodeQRCode(txtVa.getText().toString(), 1000, 1000, null);
            Image codeQrImage = barcodeQRCode.getImage();
            codeQrImage.scaleAbsolute(100, 100);
            codeQrImage.setAlignment(ALIGN_CENTER);
            mDoc.add(codeQrImage);

            mDoc.add(new LineSeparator(1f, 100, null, ALIGN_CENTER, -5));

            Font font_footer = new Font(Font.FontFamily.TIMES_ROMAN, 12.0f, Font.NORMAL, BaseColor.BLACK);
            Chunk chunk_footer = new Chunk("www.dishub.surabaya.go.id", font_footer);
            Paragraph paragraf_footer = new Paragraph(chunk_footer);
            paragraf_footer.setAlignment(ALIGN_CENTER);
            mDoc.add(paragraf_footer);

            mDoc.close();
            Toast.makeText(getActivity(), mFileName + ".pdf \n is saved to \n" + mFilePath, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void copyVirtualAccount() {
        ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        TextView txtVa = (TextView) getActivity().findViewById(R.id.txtVaBankJatim);
        ClipData clip = ClipData.newPlainText("EditText", txtVa.getText().toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getActivity(), "Berhasil Copy VA", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case STORAGE_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    savePdf();
                } else {
                    Toast.makeText(getActivity(), "Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public PdfPCell getCellContent() {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        Paragraph p1 = new Paragraph("Virtual Account");
        p1.setSpacingBefore(5);
        p1.setSpacingAfter(5);
        cell.addElement(p1);
        LineSeparator ls = new LineSeparator();
        ls.setOffset(-10);
        cell.addElement(ls);

        Paragraph p2 = new Paragraph("NIK Pemohon");
        p2.setLeading(25);
        p2.setSpacingBefore(5);
        p2.setSpacingAfter(5);
        cell.addElement(p2);

        Paragraph p8 = new Paragraph("Nama Pemohon");
        ls.setOffset(-10);
        cell.addElement(ls);
        p8.setLeading(25);
        p8.setSpacingBefore(5);
        p8.setSpacingAfter(5);
        cell.addElement(p8);

        Paragraph p9 = new Paragraph("No Uji");
        ls.setOffset(-10);
        cell.addElement(ls);
        p9.setLeading(25);
        p9.setSpacingBefore(5);
        p9.setSpacingAfter(5);
        cell.addElement(p9);

        Paragraph p3 = new Paragraph("No Kendaraan");
        ls.setOffset(-10);
        cell.addElement(ls);
        p3.setLeading(25);
        p3.setSpacingBefore(5);
        p3.setSpacingAfter(5);
        cell.addElement(p3);

        Paragraph p4 = new Paragraph("Biaya Retribusi");
        ls.setOffset(-10);
        cell.addElement(ls);
        p4.setLeading(25);
        p4.setSpacingBefore(5);
        p4.setSpacingAfter(5);
        cell.addElement(p4);

        Paragraph p5 = new Paragraph("Biaya Telat Uji");
        ls.setOffset(-10);
        cell.addElement(ls);
        p5.setLeading(25);
        p5.setSpacingBefore(5);
        p5.setSpacingAfter(5);
        cell.addElement(p5);

        Paragraph p6 = new Paragraph("Biaya Buku Uji");
        ls.setOffset(-10);
        cell.addElement(ls);
        p6.setLeading(25);
        p6.setSpacingBefore(5);
        p6.setSpacingAfter(5);
        cell.addElement(p6);

        Paragraph p7 = new Paragraph("Total Biaya Uji");
        ls.setOffset(-10);
        cell.addElement(ls);
        p7.setLeading(35);
        p7.setSpacingAfter(10);
        cell.addElement(p7);

        return cell;
    }

    public PdfPCell getCellValue() {
        TextView txtVaBankJatim = (TextView) getActivity().findViewById(R.id.txtVaBankJatim);
        TextView txtNikPemohon = (TextView) getActivity().findViewById(R.id.txtPemohonNik);
        TextView txtNamaPemohon = (TextView) getActivity().findViewById(R.id.txtPemohonNama);
        TextView txtBLulusUji = (TextView) getActivity().findViewById(R.id.txtBLulusUji);
        TextView txtBTelatUji = (TextView) getActivity().findViewById(R.id.txtBTelatUji);
        TextView txtBBukuUji = (TextView) getActivity().findViewById(R.id.txtBBukuUji);
        TextView txtJmlBayar = (TextView) getActivity().findViewById(R.id.txtJmlBayar);
        TextView txtNoKend = (TextView) getActivity().findViewById(R.id.txtNoKend);
        TextView txtNoUji = (TextView) getActivity().findViewById(R.id.txtNoUji);

        String VaBankJatim = txtVaBankJatim.getText().toString();
        String nikPemohon = txtNikPemohon.getText().toString();
        String namaPemohon = txtNamaPemohon.getText().toString();
        String BLulusUji = txtBLulusUji.getText().toString();
        String BTelatUji = txtBTelatUji.getText().toString();
        String BBukuUji = txtBBukuUji.getText().toString();
        String JmlBayar = txtJmlBayar.getText().toString();
        String NoKend = txtNoKend.getText().toString();
        String NoUji = txtNoUji.getText().toString();

        PdfPCell cell = new PdfPCell();
        cell.setPaddingLeft(10);
        cell.setBorder(Rectangle.NO_BORDER);
        Paragraph p1 = new Paragraph(VaBankJatim);
        p1.setSpacingBefore(5);
        p1.setSpacingAfter(5);
        cell.addElement(p1);
        LineSeparator ls = new LineSeparator();
        ls.setOffset(-10);
        cell.addElement(ls);

        Paragraph p2 = new Paragraph(nikPemohon);
        p2.setLeading(25);
        p2.setSpacingBefore(5);
        p2.setSpacingAfter(5);
        cell.addElement(p2);

        Paragraph p8 = new Paragraph(namaPemohon);
        ls.setOffset(-10);
        cell.addElement(ls);
        p8.setLeading(25);
        p8.setSpacingBefore(5);
        p8.setSpacingAfter(5);
        cell.addElement(p8);


        Paragraph p9 = new Paragraph(NoUji);
        ls.setOffset(-10);
        cell.addElement(ls);
        p9.setLeading(25);
        p9.setSpacingBefore(5);
        p9.setSpacingAfter(5);
        cell.addElement(p9);

        Paragraph p3 = new Paragraph(NoKend);
        ls.setOffset(-10);
        cell.addElement(ls);
        p3.setLeading(25);
        p3.setSpacingBefore(5);
        p3.setSpacingAfter(5);
        cell.addElement(p3);

        Paragraph p4 = new Paragraph(BLulusUji);
        ls.setOffset(-10);
        cell.addElement(ls);
        p4.setLeading(25);
        p4.setSpacingBefore(5);
        p4.setSpacingAfter(5);
        cell.addElement(p4);

        Paragraph p5 = new Paragraph(BTelatUji);
        ls.setOffset(-10);
        cell.addElement(ls);
        p5.setLeading(25);
        p5.setSpacingBefore(5);
        p5.setSpacingAfter(5);
        cell.addElement(p5);

        Paragraph p6 = new Paragraph(BBukuUji);
        ls.setOffset(-10);
        cell.addElement(ls);
        p6.setLeading(25);
        p6.setSpacingBefore(5);
        p6.setSpacingAfter(5);
        cell.addElement(p6);

        Paragraph p7 = new Paragraph(JmlBayar);
        ls.setOffset(-10);
        cell.addElement(ls);
        p7.setLeading(35);
        p7.setSpacingAfter(10);
        cell.addElement(p7);

        return cell;
    }


    /**
     * SAVE IMAGE
     */
    public void SaveClick() {
//        RelativeLayout savingLayout = (RelativeLayout) getActivity().findViewById(R.id.idForSaving);
//        SaveImage(setViewToBitmapImage(savingLayout));


//        pd.setMessage("saving your image");
//        pd.show();
//        RelativeLayout savingLayout = (RelativeLayout) getActivity().findViewById(R.id.idForSaving);
//        File file = saveBitMap(getActivity(), savingLayout);
//        if (file != null) {
//            pd.cancel();
//            Log.i("TAG", "Drawing saved to the gallery!");
//        } else {
//            pd.cancel();
//            Log.i("TAG", "Oops! Image could not be saved.");
//        }
    }

    /**
     * CARA 1
     */

    public static Bitmap setViewToBitmapImage(View view) {
//        Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    public void SaveImage(Bitmap finalBitmap) {
        FileOutputStream outputStream = null;
        Toast.makeText(getActivity(), "Sudah terdaftar", Toast.LENGTH_SHORT).show();
        String mFileNameDate = new SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(System.currentTimeMillis());
        String mFileName = "KirDishubSurabaya_" + mFileNameDate;
        String mFilePath = Environment.getExternalStorageDirectory() + "/" + mFileName + ".jpg";

        File sdCard = Environment.getExternalStorageDirectory();
        File myDir = new File(sdCard.getAbsolutePath() + "/" + mFileName + ".jpg");
//        myDir.mkdirs();
//        Random generator = new Random();
//        int n = 10000;
//        n = generator.nextInt(n);
//        String fname = "Image-" + n + ".jpg";
//        String fname = String.format("%d.jpg", System.currentTimeMillis());
//        File outFile = new File(myDir, fname);
//        if (outFile.exists()) outFile.delete();
//        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            try {
                outputStream = new FileOutputStream(mFilePath);
                finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.flush();
                outputStream.close();

//            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//            intent.setData(Uri.fromFile(outFile));
//            sendBroadcast();
            } catch (Exception e) {
                e.printStackTrace();
            }
//        }
    }

    /**
     * CARA 2
     */

    private File saveBitMap(Context context, View drawView) {
        File pictureFileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Logicchip");
        if (!pictureFileDir.exists()) {
            boolean isDirectoryCreated = pictureFileDir.mkdirs();
            if (!isDirectoryCreated)
                Log.i("TAG", "Can't create directory to save the image");
            return null;
        }
        String filename = pictureFileDir.getPath() + File.separator + System.currentTimeMillis() + ".jpg";
        File pictureFile = new File(filename);
        Bitmap bitmap = getBitmapFromView(drawView);
        try {
            pictureFile.createNewFile();
            FileOutputStream oStream = new FileOutputStream(pictureFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, oStream);
            oStream.flush();
            oStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TAG", "There was an issue saving the image.");
        }
        scanGallery(context, pictureFile.getAbsolutePath());
        return pictureFile;
    }

    //create bitmap from view and returns it
    private Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        } else {
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }


    // used for scanning gallery
    private void scanGallery(Context cntx, String path) {
        try {
            MediaScannerConnection.scanFile(cntx, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                public void onScanCompleted(String path, Uri uri) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("TAG", "There was an issue scanning gallery.");
        }
    }
}
