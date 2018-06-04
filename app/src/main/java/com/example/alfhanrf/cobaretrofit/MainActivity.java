package com.example.alfhanrf.cobaretrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.alfhanrf.cobaretrofit.api.RegisterAPI;
import com.example.alfhanrf.cobaretrofit.api.Retroserver;
import com.example.alfhanrf.cobaretrofit.model.Value;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String URL="";
    private RadioButton radioJKbutton;
    private ProgressDialog proggres;
    String nim,nama,jurusan;

    @BindView(R.id.radioJKbutton) RadioGroup radioGroup;
    @BindView(R.id.editTextNIM) EditText editTextNIM;
    @BindView(R.id.editTextNama) EditText editTextNama;
    @BindView(R.id.editTextJurusan) EditText editTExtJurusan;



    @OnClick (R.id.buttonDaftar) void daftar(){
        //biar keliatan ada proggres bar
        proggres = new ProgressDialog(this);
        proggres.setCancelable(false);
        proggres.setMessage("Loading ... ");
        proggres.show();

        nim = editTextNIM.getText().toString();
        nama= editTextNama.getText().toString();
        jurusan = editTExtJurusan.getText().toString();

        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioJKbutton = (RadioButton) findViewById(selectedId);
        String jk = radioJKbutton.getText().toString();

        RegisterAPI api = Retroserver.getClient().create(RegisterAPI.class);
        Call<Value> call = api.daftar(nim,nama,jurusan,jk);

        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                proggres.hide();
                Log.d("RETRO", "response : " + response.body().toString());
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")) {
                    Toast.makeText(MainActivity.this, "Data Berhasil Mantap", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,message, Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                proggres.hide();
                Log.d("RETRO", "failure : " + "Gagal Mengirimkan Pesan");
                Toast.makeText(MainActivity.this,"Jaringan ErRor",Toast.LENGTH_SHORT).show();


            }
        });
    }

   @OnClick(R.id.buttonLihat) void lihat(){
        Intent pindah = new Intent(MainActivity.this,ViewActivity.class);
        startActivity(pindah);
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

}
