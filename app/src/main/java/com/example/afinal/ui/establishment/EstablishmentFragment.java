package com.example.afinal.ui.establishment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.afinal.R;

import com.example.afinal.Singleton;
import com.example.afinal.databinding.FragmentEstablishmentBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class EstablishmentFragment extends Fragment {

    private EstablishmentViewModel establishmentViewModel;
    private FragmentEstablishmentBinding binding;
    ArrayAdapter arrayAdapter;
    ArrayList<String> list;
    ArrayList<String> restNames;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
                establishmentViewModel =
                    new ViewModelProvider(this).get(EstablishmentViewModel.class);

        binding = FragmentEstablishmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        restNames = new ArrayList<>();

        list = new ArrayList<>();
        list.add("Olea");
        list.add("Grand nawab");
        list.add("adafafa");

        ListView listView = (ListView) root.findViewById(R.id.list_view);

        arrayAdapter = new ArrayAdapter(this.getContext() ,android.R.layout.simple_expandable_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        establishmentViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        establishmentViewModel.getRestaurantNames();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}