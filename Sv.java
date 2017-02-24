package com.example.zhouxuexuan.fbnl;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Sv extends AppCompatActivity {
    private ImageButton Free;
    private Animation Zoom;
    private ImageButton Busy;
    private ImageButton Meeting;
    private Context mContext;
    private Activity mActivity;
    private RelativeLayout mRelativeLayout;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p);
        Free = (ImageButton) findViewById(R.id.imageButton);
        Busy = (ImageButton) findViewById(R.id.imageButton2);
        Meeting = (ImageButton) findViewById(R.id.imageButton3);
        Zoom = AnimationUtils.loadAnimation(this, R.anim.zoom);
        mContext = getApplicationContext();
        mActivity = Sv.this;
        mRelativeLayout = (RelativeLayout) findViewById(R.id.kl);
        Meeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.popup_example3, null);

                mPopupWindow = new PopupWindow(customView,
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT
                );
                mPopupWindow.setFocusable(true);
                mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
                final CheckBox Set=(CheckBox) customView.findViewById(R.id.checkBox);
                final Button check=(Button) customView.findViewById(R.id.buttonk);
                final TextView s1=(TextView) customView.findViewById(R.id.textView6);
                final TextView s2=(TextView) customView.findViewById(R.id.textView11);
                final CheckBox checkBox = (CheckBox) customView.findViewById(R.id.checkBox);
                final Firebase ref = new Firebase("https://final-45c7e.firebaseio.com/");
                final Firebase tref = ref.child("Meetingboolean");
                tref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue().toString().equals("0")){
                            checkBox.setChecked(false);
                        }
                        if (dataSnapshot.getValue().toString().equals("1")){
                            checkBox.setChecked(true);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
                Set.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Set.isChecked()){
                            try {
                                Firebase ref = new Firebase("https://final-45c7e.firebaseio.com/");
                                ref.child("Meetingboolean").setValue(1);
                                Toast.makeText(Sv.this, "Meeting available", Toast.LENGTH_SHORT).show();
                            }catch (Exception e){
                                Toast.makeText(Sv.this, "Set failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            try {
                                Firebase ref = new Firebase("https://final-45c7e.firebaseio.com/");
                                ref.child("Meetingboolean").setValue(0);
                                Toast.makeText(Sv.this, "Meeting canceled successfully", Toast.LENGTH_SHORT).show();
                                ref.child("Meeting1-3").setValue(0);
                                ref.child("Meeting3-5").setValue(0);
                            }catch (Exception e){
                                Toast.makeText(Sv.this, "Set failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Firebase ref = new Firebase("https://final-45c7e.firebaseio.com/");
                        Firebase mref = ref.child("Meeting1-3");
                        Firebase mref2 = ref.child("Meeting3-5");
                        mref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                s1.setText(dataSnapshot.getChildrenCount()+" people are booked for slot 1-3pm");
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                        mref2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                s2.setText(dataSnapshot.getChildrenCount()+" people are booked for slot 3-5pm");
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                    }
                });

                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
                mPopupWindow.setFocusable(true);
            }
        });

        Busy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.popup_example, null);

                mPopupWindow = new PopupWindow(customView,
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT
                );
                mPopupWindow.setFocusable(true);
                mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
                Button t1 = (Button) customView.findViewById(R.id.buttont1);
                t1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            Firebase ref = new Firebase("https://final-45c7e.firebaseio.com/");
                            ref.child("Machine").setValue(0);
                            ref.child("Meetingboolean").setValue(0);
                            Toast.makeText(Sv.this, "Set successfully", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            Toast.makeText(Sv.this, "Set failed", Toast.LENGTH_SHORT).show();
                        }
                        mPopupWindow.dismiss();
                    }
                });
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
                mPopupWindow.setFocusable(true);
            }
        });
        Free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.popup_example2, null);

                mPopupWindow = new PopupWindow(customView,
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT
                );
                mPopupWindow.setFocusable(true);
                mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
                Button t2 = (Button) customView.findViewById(R.id.buttont2);
                t2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            Firebase ref = new Firebase("https://final-45c7e.firebaseio.com/");
                            ref.child("Machine").setValue(1);
                            Toast.makeText(Sv.this, "Set successfully", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            Toast.makeText(Sv.this, "Set failed", Toast.LENGTH_SHORT).show();
                        }
                        mPopupWindow.dismiss();
                    }
                });
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
                mPopupWindow.setFocusable(true);
            }
        });

    }

}
