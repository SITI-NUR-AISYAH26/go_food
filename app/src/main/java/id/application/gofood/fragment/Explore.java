package id.application.gofood.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import id.application.gofood.*;

public class Explore extends Fragment {

    private DatabaseReference mDatabase;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explore_fragment, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        list_makanan(view);
        list_minuman(view);
        return view;
    }

    private void list_makanan(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_makanan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<DataSetCustom> array = new ArrayList<>();
        progressDialog.show();
        // Fetch data from Firebase for food
        mDatabase.child("foods").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                array.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DataSetCustom food = snapshot.getValue(DataSetCustom.class);
                    if (food != null) {
                        array.add(food);
                    }
                }
                // Set up the adapter after data is fetched
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), array);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }

    private void list_minuman(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_minuman);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        progressDialog.show();

        ArrayList<DataSetCustom> array = new ArrayList<>();
        mDatabase.child("drinks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                array.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DataSetCustom food = snapshot.getValue(DataSetCustom.class);
                    if (food != null) {
                        array.add(food);
                    }
                }
                // Set up the adapter after data is fetched
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), array);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }
}
