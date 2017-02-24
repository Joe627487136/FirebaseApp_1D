package com.example.zhouxuexuan.fbnl;


        import android.content.Intent;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextAddress;
    private Button login;
    public  String name;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        login = (Button) findViewById(R.id.loginbutton);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.go);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.6, 20);
        anim.setInterpolator(interpolator);
        login.startAnimation(anim);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextName.getText().toString().equals("Prof")&&editTextAddress.getText().toString().equals("iamprof")){
                    Intent intent1 = new Intent(getApplicationContext(), Sv.class);
                    startActivity(intent1);
                }
                else if (editTextName.getText().toString().equals("Student")&&editTextAddress.getText().toString().equals("iamstudent")){
                    Intent intent2 = new Intent(getApplicationContext(), Cl.class);
                    startActivity(intent2);
                }
                else{
                    Toast.makeText(MainActivity.this, "User not exist or password incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



}