package com.example.zhouxuexuan.fbnl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Cl extends AppCompatActivity {
    private ImageButton Check;
    private Animation Zoom;
    private ImageButton Meeting;
    private Context mContext;
    private Activity mActivity;
    private RelativeLayout mRelativeLayout;
    private PopupWindow mPopupWindow;
    private RadioGroup radioTGroup;
    private RadioButton radioSexButton;
    private ImageView wel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s);
        Check = (ImageButton) findViewById(R.id.imageButton);
        Meeting = (ImageButton) findViewById(R.id.imageButton3);
        Zoom = AnimationUtils.loadAnimation(this, R.anim.zoom);
        mContext = getApplicationContext();
        mActivity = Cl.this;
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl);
        radioTGroup = (RadioGroup) findViewById(R.id.radioT);

        //begin
        Meeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                final View customView = inflater.inflate(R.layout.popup_example5, null);
                mPopupWindow = new PopupWindow(customView,
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT
                );
                mPopupWindow.setFocusable(true);
                mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
                Button t5 = (Button) customView.findViewById(R.id.buttont5);
                t5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RadioGroup KGroup = (RadioGroup) mPopupWindow.getContentView().findViewById(R.id.radioT);
                        int selectedId = KGroup.getCheckedRadioButtonId();
                        radioSexButton = (RadioButton) mPopupWindow.getContentView().findViewById(selectedId);
                        EditText nameinput = (EditText) mPopupWindow.getContentView().findViewById(R.id.nameinput);
                        final String name = nameinput.getText().toString();
                        final Firebase ref = new Firebase("https://final-45c7e.firebaseio.com/");
                        if (radioSexButton.getText().toString().equals("1-3pm")) {
                            Firebase mref = ref.child("Meetingboolean");
                            mref.addValueEventListener(new ValueEventListener() {
                                                           @Override
                                                           public void onDataChange(DataSnapshot dataSnapshot) {
                                                               int check = dataSnapshot.getValue(int.class);
                                                               if(check==1){
                                                                   ref.child("Meeting1-3").child(name).setValue("booked");
                                                                   Toast.makeText(Cl.this, "Book successfully", Toast.LENGTH_SHORT).show();
                                                               }
                                                               if(check==0){
                                                                   Toast.makeText(Cl.this, "Meeting disabled", Toast.LENGTH_SHORT).show();
                                                               }
                                                           }

                                                           @Override
                                                           public void onCancelled(FirebaseError firebaseError) {

                                                           }
                                                       });
                        }
                        else if (radioSexButton.getText().toString().equals("3-5pm")) {
                            Firebase mref = ref.child("Meetingboolean");
                            mref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    int check = dataSnapshot.getValue(int.class);
                                    if(check==1){
                                        ref.child("Meeting3-5").child(name).setValue("booked");
                                        Toast.makeText(Cl.this, "Book successfully", Toast.LENGTH_SHORT).show();
                                    }
                                    if(check==0){
                                        Toast.makeText(Cl.this, "Meeting disabled", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });
                        }
                        mPopupWindow.dismiss();

                    }
                });
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
                mPopupWindow.setFocusable(true);
            }
        });
        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                final View customView = inflater.inflate(R.layout.popup_example4, null);
                mPopupWindow = new PopupWindow(customView,
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT
                );
                mPopupWindow.setFocusable(true);
                mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
//                TextView status = (TextView) mPopupWindow.getContentView().findViewById(R.id.sd4);
//                status.setText("ohm");
                Firebase ref = new Firebase("https://final-45c7e.firebaseio.com/");
                        final TextView status = (TextView) mPopupWindow.getContentView().findViewById(R.id.sd4);
                        Firebase mref = ref.child("Machine");
                        mref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                int check = dataSnapshot.getValue(int.class);
                                if (check==0) {
                                    status.setText("Prof available");
                                    //prof click physical available button or click on app
                                }
                                if (check==1) {
                                    status.setText("Prof Busy");
                                    //prof click busy button on app
                                }
                                if (check==2) {
                                    status.setText("People inside");
                                    //prof has turned on the device but didn't check busy or available button
                                    //device ongoing detection
                                    //motion sensor priority
                                }
                                if (check==4) {
                                    status.setText("Empty, light on");
                                    //motion sensor off show empty
                                    //ldr detect starts
                                    //case for ldr==true;
                                }
                                if (check==5) {
                                    status.setText("Empty, light off");
                                    //motion sensor off show empty
                                    //ldr detect starts
                                    //case for ldr==false;
                                }
                                if (check==6) {
                                    status.setText("Device not in use");
                                    //prof doesn't turn on the device
                                    //no self detection start
                                }

                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
                mPopupWindow.setFocusable(true);
            }
        });

    }

}
