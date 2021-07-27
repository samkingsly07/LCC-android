package com.example.lifechristiancenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.EventLogTags;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import com.example.lifechristiancenter.databinding.ActivityScrollingBinding;

import es.dmoral.toasty.Toasty;

public class ScrollingActivity<val> extends AppCompatActivity {
    public static final String PREF_NAME = "SS";
    personAdapter
            adapter; // Create Object of the Adapter class
    DatabaseReference mbase;
    DatabaseReference mbase1;// Create object of the


    long count ;
    long a=0;
    @SuppressLint("SetTextI18n")
    public ActivityScrollingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null){

            new AlertDialog.Builder(ScrollingActivity.this)
                    .setIcon(R.drawable.ic_error_outline_white_48dp)
                    .setTitle(getResources().getString(R.string.app_name))
                    .setMessage("No internet connection....")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();
            Toasty.warning(ScrollingActivity.this, "No Internel Available... ",
                    Toast.LENGTH_LONG, true).show();

        }
        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toasty.error(ScrollingActivity.this, "Available soon..... ",
                        Toast.LENGTH_LONG, true).show();
            }
        });
        SharedPreferences settings = getSharedPreferences(PREF_NAME,0);
        count=settings.getInt("count", (int) count);
        Calendar calendar = Calendar.getInstance();

        mbase1 = FirebaseDatabase.getInstance().getReference();

        TextView helloTextView = (TextView) findViewById(R.id.textView);
        TextView helloTextView1 = (TextView) findViewById(R.id.textView5);
        mbase1.child("2").removeValue();

        mbase1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = (int) snapshot.child("1").getChildrenCount();


                Calendar c1 = Calendar.getInstance();
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                String formattedDate = df.format(c);
                String today = formattedDate;
                String str1, str2, str3;
                String str4, str5, str6, str7, str8, str9;
                int index3;
                int index4, index5, index6;
                int index1, index2;
                index1 = today.indexOf('-');
                index2 = today.lastIndexOf('-');
                str1 = today.substring(0, index1);
                str2 = today.substring(index1 + 1, index2);
                str3 = today.substring(index2);
                int sss = count + 1;
                for (int i = 1; i <= 7; i++) {


                    for (int j = 1; j <= count; j++) {
                        String s1 = Objects.requireNonNull(snapshot.child("1").child(String.valueOf(j)).child("dob").getValue()).toString();
                        String s2 = Objects.requireNonNull(snapshot.child("1").child(String.valueOf(j)).child("wed").getValue()).toString();
                        index3 = s1.indexOf('/');
                        index4 = s1.lastIndexOf('/');
                        str4 = s1.substring(0, index3);
                        str5 = s1.substring(index3 + 1, index4);
                        str6 = s1.substring(index4 + 1);
                        index5 = s2.indexOf('/');
                        index6 = s2.lastIndexOf('/');
                        str7 = s2.substring(0, index5);
                        str8 = s2.substring(index5 + 1, index6);
                        str9 = s2.substring(index6 + 1);


                        if (str4.equals(str1) && (str5.equals(str2))) {
                            String s3 = Objects.requireNonNull(snapshot.child("1").child(String.valueOf(j)).child("name").getValue()).toString();
                            String s4 = Objects.requireNonNull(snapshot.child("1").child(String.valueOf(j)).child("lastname").getValue()).toString();
                            Calendar c2 = Calendar.getInstance();
                            String today1 = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

                            Calendar dob = Calendar.getInstance();
                            int s = Integer.parseInt(str6);
                            int ss1 = Integer.parseInt(str5);
                            int ss2 = Integer.parseInt(str4);
                            dob.set(s, ss1, ss2);
                            c2.add(Calendar.DATE, i - 1);
                            String future = DateFormat.getDateInstance(DateFormat.FULL).format(c2.getTime());
                            int age = c2.get(Calendar.YEAR) - dob.get(Calendar.YEAR);


                            if (c2.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
                                age--;
                            }

                            mbase1.child("2").child(String.valueOf(j)).child("dob").setValue("Age :" + age + "\nBirthday on :" + future);
                            mbase1.child("2").child(String.valueOf(j)).child("name").setValue(s3);
                            mbase1.child("2").child(String.valueOf(j)).child("lastname").setValue(s4);
                            a = a + 1;
                            mbase1.child("2").child(String.valueOf(j)).child("image1").setValue("https://firebasestorage.googleapis.com/v0/b/lccapp-90f46.appspot.com/o/sss.png?alt=media&token=a5c4e1cb-6f20-4c2a-83c2-21b17107f479");

                            String ss = String.valueOf(a);

                            helloTextView1.setText(ss);

                            if (Objects.requireNonNull(snapshot.child("1").child(String.valueOf(j)).child("gender").getValue()).toString().equals("male")) {
                                mbase1.child("2").child(String.valueOf(j)).child("image").setValue("https://firebasestorage.googleapis.com/v0/b/lccapp-90f46.appspot.com/o/iconfinder_205-man-in-tuxedo-2_3099528.png?alt=media&token=d8f73e89-4c98-4ce8-a68a-57889f1083df");
                            } else {
                                mbase1.child("2").child(String.valueOf(j)).child("image").setValue("https://firebasestorage.googleapis.com/v0/b/lccapp-90f46.appspot.com/o/iconfinder_156-woman-office-worker-2_3099409.png?alt=media&token=d2f2e99b-d8fb-4414-9169-48243446da24");

                            }

                        }

                        if (str7.equals(str1) && str8.equals(str2)) {
                            a = a + 1;
                            Calendar c2 = Calendar.getInstance();
                            String today1 = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
                            c2.add(Calendar.DATE, i - 1);
                            String s3 = Objects.requireNonNull(snapshot.child("1").child(String.valueOf(j)).child("name").getValue()).toString();
                            String s4 = Objects.requireNonNull(snapshot.child("1").child(String.valueOf(j)).child("lastname").getValue()).toString();
                            String future = DateFormat.getDateInstance(DateFormat.FULL).format(c2.getTime());
                            String ss = String.valueOf(a);

                            Calendar dob = Calendar.getInstance();
                            int s = Integer.parseInt(str9);
                            int ss1 = Integer.parseInt(str8);
                            int ss2 = Integer.parseInt(str7);
                            dob.set(s, ss1, ss2);

                            int age = c2.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

                            if (c2.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
                                age--;
                            }

                            helloTextView1.setText(ss);
                            mbase1.child("2").child(String.valueOf(sss)).child("name").setValue(s3);
                            mbase1.child("2").child(String.valueOf(sss)).child("lastname").setValue(s4);
                            mbase1.child("2").child(String.valueOf(sss)).child("dob").setValue(+age + " years old couple \nWedding day on :" + future);
                            mbase1.child("2").child(String.valueOf(sss)).child("image").setValue("https://firebasestorage.googleapis.com/v0/b/lccapp-90f46.appspot.com/o/%E2%80%94Pngtree%E2%80%94valentine%20couple%20wedding%20cartoon%20couple_3974603.png?alt=media&token=afec3d96-59eb-4068-ba63-89ef237ab724");
                            mbase1.child("2").child(String.valueOf(sss)).child("image1").setValue("https://firebasestorage.googleapis.com/v0/b/lccapp-90f46.appspot.com/o/new-wedding-png-fonts-41464-png-images-pngio-wedding-png-fonts-free-download-1600_873.png?alt=media&token=f7aaeebb-6658-4397-b7d1-1cf1b5e85cc4");

                            sss = sss + 1;

                        }

                    }
                    c1.add(Calendar.DATE, 1);
                    today = df.format(c1.getTime());


                    index1 = today.indexOf('-');
                    index2 = today.lastIndexOf('-');
                    str1 = today.substring(0, index1);
                    str2 = today.substring(index1 + 1, index2);
                    str3 = today.substring(index2);


                }

                if (a == 0) {
                    Toasty.Config.getInstance().setToastTypeface(Typeface.SANS_SERIF).setInfoColor(Color.parseColor("#8153FA")).apply();
                    for (int i = 0; i < 2; i++) {


                        Toasty.info(ScrollingActivity.this, "No Birthday Or Wedding Day Are Presented For This Week ",
                                Toast.LENGTH_LONG, true).show();
                    }
                } else {

                    Toasty.Config.getInstance().setToastTypeface(Typeface.SANS_SERIF).setInfoColor(Color.parseColor("#8153FA")).apply();
                    for (int i = 0; i < 2; i++) {
                        if (a==1)
                        {
                            Toasty.success(ScrollingActivity.this, +a + " Special Day Are Presented For This Week ",
                                    Toast.LENGTH_LONG, true).show();
                        }
                        else {
                            Toasty.success(ScrollingActivity.this, +a + " Special Days Are Presented For This Week ",
                                    Toast.LENGTH_LONG, true).show();
                        }
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        } );
        Calendar c = Calendar.getInstance();
        String today1 = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        c.add(Calendar.DATE, 6);

        String future = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        helloTextView.setText("    From: " + today1 + "\n" + "Till: " + future);

        RecyclerView recyclerView = findViewById(R.id.recycler1);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));





        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data


        mbase = FirebaseDatabase.getInstance().getReference().child(String.valueOf(2));


        FirebaseRecyclerOptions<ss> options
                = new FirebaseRecyclerOptions.Builder<ss>()
                .setQuery(mbase, ss.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new personAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}