package com.pritam.doctorprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements DoctorAdapter.OnItemClickListener {
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_HOSPITAL = "hospital";
    public static final String EXTRA_RATING = "0.0";
    public static final String EXTRA_VISIT = "0.0";

    private static final String DOCTOR_URL =  "http://codearistos.io/volley_test/myApi.php";
    RecyclerView recyclerView;
    //DoctorAdapter adapter;
    List<Doctor> doctorList;
    Toolbar toolbar;
    private DoctorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        doctorList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadDoctors();

    }

    private void loadDoctors() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, DOCTOR_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray doctors = new JSONArray(response);

                            for(int i=0; i<doctors.length(); i++) {
                                JSONObject doctorObject = doctors.getJSONObject(i);

                                int id = doctorObject.getInt("id");
                                String name = doctorObject.getString("name");
                                String hospital = doctorObject.getString("hospital");
                                double visit = doctorObject.getDouble("visit");
                                double rating = doctorObject.getDouble("rating");
                                String img = doctorObject.getString("image");
                                Doctor doctor = new Doctor(id, name, hospital, rating, visit, img);
                                doctorList.add(doctor);
                            }

                            adapter = new DoctorAdapter(ListActivity.this, doctorList);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(ListActivity.this);
                            /*adapter.setOnItemClickListener(new DoctorAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    Intent intent = new Intent(this, ProfileActivity.class);
                                    doctorList.get(position);
                                    adapter.notifyItemChanged(position);
                                }
                            });*/
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ProfileActivity.class);
        Doctor clickedItem = doctorList.get(position);
        intent.putExtra(EXTRA_URL,clickedItem.getImage());
        intent.putExtra(EXTRA_NAME,clickedItem.getName());
        intent.putExtra(EXTRA_HOSPITAL,clickedItem.getHospital());
        intent.putExtra(EXTRA_RATING,clickedItem.getRating());
        intent.putExtra(EXTRA_VISIT,clickedItem.getVisit());

        startActivity(intent);
    }
}
