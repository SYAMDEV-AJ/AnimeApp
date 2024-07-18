package com.example.testing.ui;

import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.testing.R;
import com.example.testing.adapter.AdapterCharacterList;
import com.example.testing.databinding.ActivityHomeScreenBinding;
import com.example.testing.modelclass.CharacterResponse;
import com.example.testing.network.viewmodel.Viewmodel;
import com.example.testing.usersession.BaseActivity;

import java.util.ArrayList;


public class HomeScreenActivity extends BaseActivity {
    ActivityHomeScreenBinding binding;

    Viewmodel viewmodel;

    int page = 0, limit = 0;

    AdapterCharacterList adapterCharacterList;
    ArrayList<CharacterResponse.Result> characterList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_screen);
        viewmodel = ViewModelProviders.of(this).get(Viewmodel.class);

        serviceloader();
        search();
        pullToRefresh();

        observer();
        scroll();
        logoutclick();
    }

    private void logoutclick() {

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.clear();
                editor.commit();
                HomeScreenActivity.this.finish();
            }
        });
    }

    private void serviceloader() {
        showProgress();
        viewmodel.pageload(String.valueOf(page));

    }

    private void observer() {

        viewmodel.getCharacterResponseMutableLiveData().observe(this, new Observer<CharacterResponse>() {
            @Override
            public void onChanged(CharacterResponse characterResponse) {

                hideProgress();

                binding.refresh.setRefreshing(false);

                characterResponse.getResults().get(0).getId();
                limit = characterResponse.getInfo().getPages();

                characterList.addAll(characterResponse.getResults());
                setupRecycler();
                Toast.makeText(HomeScreenActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
            }
        });

        viewmodel.getFailureResponseMutableLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(HomeScreenActivity.this, s, Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void setupRecycler() {

        binding.recylcericon.setHasFixedSize(true);
        binding.recylcericon.setLayoutManager(new GridLayoutManager(mActivity, 2));
        adapterCharacterList = new AdapterCharacterList(mActivity, characterList, new AdapterCharacterList.iconclick() {
            @Override
            public void iconclick(Integer itemid) {

                Intent intent = new Intent(HomeScreenActivity.this, CharacterDetailsActivity.class);
                intent.putExtra("characterid", itemid);
                startActivity(intent);

            }
        });
        binding.recylcericon.setAdapter(adapterCharacterList);
        adapterCharacterList.notifyDataSetChanged();

    }

    private void scroll() {
        binding.nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    page++;
                    if (page > limit) {

                        Toast.makeText(HomeScreenActivity.this, "That's all the data..", Toast.LENGTH_SHORT).show();

                    } else {
                        viewmodel.pageload(String.valueOf(page));


                    }
                }
            }
        });

    }

    private void pullToRefresh() {
        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showProgress();

                viewmodel.pageload(String.valueOf(page));


            }
        });
    }

    private void search() {
        binding.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.search.getText().toString().length() > 0) {
                    filter(binding.search.getText().toString());
                } else {

                    filter("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void filter(String toString) {
        ArrayList<CharacterResponse.Result> newList = new ArrayList<>();
        for (CharacterResponse.Result data : characterList) {
            if (data.getName().toLowerCase().contains(toString.toLowerCase())) {
                newList.add(data);
            }
        }
        adapterCharacterList.characterList = newList;
        adapterCharacterList.notifyDataSetChanged();
    }


}
