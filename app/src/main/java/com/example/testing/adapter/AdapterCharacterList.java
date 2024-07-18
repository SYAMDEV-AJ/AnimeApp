package com.example.testing.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.R;
import com.example.testing.databinding.ListCharactorBinding;
import com.example.testing.modelclass.CharacterResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterCharacterList extends RecyclerView.Adapter<AdapterCharacterList.ViewHolder> {

    Context context;
    public List<CharacterResponse.Result> characterList;


    public iconclick itemListener;

    public interface iconclick {

        void iconclick(Integer itemid);
    }


    public AdapterCharacterList(Context context, List<CharacterResponse.Result> characterList, iconclick itemListener) {

        this.context = context;
        this.characterList = characterList;
        this.itemListener = itemListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListCharactorBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_charactor, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.binding.labelname.setText(characterList.get(position).getName());

        Picasso.get().load(characterList.get(position).getImage()).into(holder.binding.imageicone);

        holder.binding.cardviewnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.iconclick(characterList.get(position).getId());
            }
        });


    }

    @Override
    public int getItemCount() {

        if (characterList != null)
            return characterList.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ListCharactorBinding binding;

        public ViewHolder(@NonNull ListCharactorBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

}
