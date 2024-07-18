package com.example.testing.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.testing.R;
import com.example.testing.adapter.AdapterCharacterList;
import com.example.testing.databinding.ActivityCharacterDetailsBinding;
import com.example.testing.modelclass.CharacterDetailsResponse;
import com.example.testing.network.viewmodel.Viewmodel;
import com.example.testing.usersession.BaseActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CharacterDetailsActivity extends BaseActivity {
    ActivityCharacterDetailsBinding binding;

    Viewmodel viewmodel;
    AdapterCharacterList adapterCharacterList;
    ArrayList<CharacterDetailsResponse.results> characterList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_details);
        viewmodel = ViewModelProviders.of(this).get(Viewmodel.class);
        String id = getIntent().getStringExtra("characterid");
        detailsloading(id);
        observer();
        binding.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharacterDetailsActivity.this.finish();
            }
        });

    }

    private void detailsloading(String id) {
        showProgress();
        viewmodel.details(id);

    }

    private void observer() {

        viewmodel.getCharacterDetailsResponseMutableLiveData().observe(this, new Observer<CharacterDetailsResponse>() {
            @Override
            public void onChanged(CharacterDetailsResponse characterDetailsResponse) {
                hideProgress();
                binding.name.setText(characterDetailsResponse.getResults().get(0).getName());
                Picasso.get().load(characterDetailsResponse.getResults().get(0).getImage()).into(binding.characterimage);
                binding.status.setText(characterDetailsResponse.getResults().get(0).getStatus());
                binding.species.setText(characterDetailsResponse.getResults().get(0).getSpecies());
                binding.gender.setText(characterDetailsResponse.getResults().get(0).getGender());
                binding.type.setText(characterDetailsResponse.getResults().get(0).getType());
                binding.location.setText(characterDetailsResponse.getResults().get(0).getLocation().getName());

                Toast.makeText(CharacterDetailsActivity.this, "Loading...", Toast.LENGTH_SHORT).show();


            }
        });

        viewmodel.getFailureResponseMutableLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(CharacterDetailsActivity.this, s, Toast.LENGTH_SHORT).show();

            }
        });
    }


}
