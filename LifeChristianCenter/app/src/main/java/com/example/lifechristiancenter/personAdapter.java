package com.example.lifechristiancenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class personAdapter extends FirebaseRecyclerAdapter<
        ss, personAdapter.personsViewholder> {


    public personAdapter(
            @NonNull FirebaseRecyclerOptions<ss> options)
    {
        super(options);

    }



    // Function to bind the view in Card view(here
    // "ss.xml") iwth data in
    // model class(here "ss.class")
    @Override
    protected void
    onBindViewHolder(@NonNull personsViewholder holder,
                     int position, @NonNull ss model) {

        // Add firstname from model class (here
        // "ss.class")to appropriate view in Card
        // view (here "ss.xml")
        holder.name.setText(model.getname());

        // Add lastname from model class (here
        // "ss.class")to appropriate view in Card
        // view (here "ss.xml")
        holder.lastname.setText(model.getLastname());

        // Add age from model class (here
        // "ss.class")to appropriate view in Card
        // view (here "ss.xml")
        holder.dob.setText(model.getdob());
      String ssss= model.getimage();

        Picasso.get().load(ssss).into(holder.image);
        String sss= model.getimage1();

        Picasso.get().load(sss).into(holder.image1);


    }
    // Function to tell the class about the Card view (here
    // "ss.xml")in
    // which the data will be shown

    @NonNull
    @Override
    public personsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ss, parent, false);
        return new personsViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "ss.xml")
    class personsViewholder
            extends RecyclerView.ViewHolder {
        TextView name, lastname, dob;
        ImageView image,image1;
        public personsViewholder(@NonNull View itemView)
        {
            super(itemView);

            name
                    = itemView.findViewById(R.id.name);
            lastname = itemView.findViewById(R.id.lastname);
            dob = itemView.findViewById(R.id.dob);
             image=itemView.findViewById(R.id.image);
             image1=itemView.findViewById(R.id.imageView4);
        }
    }
}
