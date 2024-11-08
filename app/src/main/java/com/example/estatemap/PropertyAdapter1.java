package com.example.estatemap;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PropertyAdapter1 extends RecyclerView.Adapter<PropertyAdapter1.PropertyViewHolder> {
    private List<Apartment> propertyList;

    public PropertyAdapter1(List<Apartment> propertyList) {

        this.propertyList = propertyList;
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_item, parent, false);
        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {
        Apartment property = propertyList.get(position);

        String picUrl = property.getImageURL();
        if (picUrl != null && !picUrl.isEmpty()) {
            // Load image with Glide
            Glide.with(holder.itemView.getContext())
                    .load(picUrl)
                    .into(holder.pictureImageView);
        } else {
            // Log a warning if the URL is null
            Log.w("PropertyAdapter1", "Image URL is null or empty for property: " + property.getPrice());
            // Optionally set a placeholder or error image
            holder.pictureImageView.setImageResource(R.drawable.house4);
        }

    // Set OnClickListener to open a new Activity on click
    holder.pictureImageView.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick (View v){
        int currentPosition = holder.getAdapterPosition();
        if (currentPosition != RecyclerView.NO_POSITION) {
            Apartment currentProperty = propertyList.get(currentPosition);
            Intent intent = new Intent(holder.itemView.getContext(), PropertyDetails.class); // Replace with your target activity
            intent.putExtra("imageURL", currentProperty.getImageURL()); // Assume getId() gives property ID or unique identifier
            holder.itemView.getContext().startActivity(intent);
        }
    }
    });
}

    @Override
    public int getItemCount() {
        return propertyList.size();
    }

    public   static class PropertyViewHolder extends RecyclerView.ViewHolder {
        ImageView pictureImageView;

        public PropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            pictureImageView = itemView.findViewById(R.id.image_property);
        }
    }
}


