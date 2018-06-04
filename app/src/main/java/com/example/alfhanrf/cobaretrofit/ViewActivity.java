package com.example.alfhanrf.cobaretrofit;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.alfhanrf.cobaretrofit.adapter.RecycleViewAdapater;
import com.example.alfhanrf.cobaretrofit.api.RegisterAPI;
import com.example.alfhanrf.cobaretrofit.api.Retroserver;
import com.example.alfhanrf.cobaretrofit.model.Mahasiswa;
import com.example.alfhanrf.cobaretrofit.model.Value;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private List<Mahasiswa> mahasiswa = new ArrayList<>();
    private RecycleViewAdapater viewAdapter;

    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.proggres_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        ButterKnife.bind(this);
        viewAdapter = new RecycleViewAdapater(this, mahasiswa);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);

        loadDataMahsiswa();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataMahsiswa();
    }


    private void loadDataMahsiswa() {

        RegisterAPI api = Retroserver.getClient().create(RegisterAPI.class);
        Call<Value> call = api.view();

        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                progressBar.setVisibility(View.GONE);
                Log.d("RETRO", "response : " + response.body().toString());
                String value = response.body().getValue();

                if (value.equals("1")) {
                    mahasiswa = response.body().getResult();
                    viewAdapter = new RecycleViewAdapater(ViewActivity.this, mahasiswa);
                    recyclerView.setAdapter(viewAdapter);
                } else {

                }
            }


            @Override
            public void onFailure(Call<Value> call, Throwable t) {


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Cari Nama Mahasiswa");
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        RegisterAPI api = Retroserver.getClient().create(RegisterAPI.class);
        Call<Value> call = api.search(newText);

        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                recyclerView.setVisibility(View.VISIBLE);
                String value = response.body().getValue();
                progressBar.setVisibility(View.GONE);

                if (value.equals("1")) {
                    mahasiswa = response.body().getResult();
                    viewAdapter = new RecycleViewAdapater(ViewActivity.this, mahasiswa);
                    recyclerView.setAdapter(viewAdapter);
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
            progressBar.setVisibility(View.GONE);

            }

        });
        return true;
    }
}