package com.example.afinal.ui.establishment;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.afinal.Singleton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class EstablishmentViewModel extends ViewModel{

    private MutableLiveData<String> mText;
    private ArrayList<String> restNames;

    Singleton singleton = Singleton.getSingleton();
    FirebaseFirestore firestore = singleton.getFirestore();

    public EstablishmentViewModel() {
        mText = new MutableLiveData<>();
        restNames = new ArrayList<>();
//        mText.setValue("Olea");
    }

//    public ArrayList sendRestaurantNames()
//    {
//        getRestaurantNames(this.restNames);
//        Log.i("Data3", this.restNames.toString());
//        return this.restNames;
//    }

    private void setRestaurantNames(ArrayList<String> tmp)
    {
        this.restNames = tmp;
        Log.i("Names", restNames.toString());
    }
    //Return restaurant names from Firestore
    public ArrayList getRestaurantNames()
    {
        CollectionReference mainRef = firestore.collection("Establishments");
        DocumentReference restNameRef = mainRef.document("Restaurant Names");

        restNameRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    EstablishmentViewModel estModel = new EstablishmentViewModel();
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(!documentSnapshot.exists()) {
                            Log.i("Data", "There is no data");
                        }
                        else {
                            ArrayList<String> tmp = (ArrayList<String>) documentSnapshot.get("Names");
                            restNames = tmp;

                        }
                    }
                });
        Log.i("Data", restNames.toString());
        return restNames;
    }

    public LiveData<String> getText() {
        return mText;
    }

    public ArrayList<String> getList()
    {
        return restNames;
    }
}