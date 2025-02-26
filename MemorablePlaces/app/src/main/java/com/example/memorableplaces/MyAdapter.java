package com.example.memorableplaces;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    Context context;

    public MyAdapter(Context ct)
    {
        context = ct;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =  inflater.inflate(R.layout.my_row, parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position)
    {
        holder.favPlaceTextView.setText(MainActivity.places.get(position));

        holder.mainLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra("placeNumber", position);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return MainActivity.places.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView favPlaceTextView;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            favPlaceTextView = itemView.findViewById(R.id.favPlaceTextView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
