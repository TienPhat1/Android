package com.example.myshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myshop.Model.Admin;
import com.example.myshop.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText phoneNumb,passWrd;
    private Button loginButton;
    private ProgressDialog loadingBar;
    private TextView admin_link;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.login_btn);
        phoneNumb = (EditText) findViewById(R.id.login_phone_number);
        passWrd = (EditText) findViewById(R.id.login_password);
        admin_link  = (TextView) findViewById(R.id.admin_link_panel);
        loadingBar = new ProgressDialog(this);

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loginUser();
            }
        });

        admin_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginButton.setText("Admin Login");
                admin_link.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void loginUser()
    {
        String phone = phoneNumb.getText().toString();
        String password = passWrd.getText().toString();

        if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(this,"Please write phone number...",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please write password greater than or equal to 6 ...",Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Login account");
            loadingBar.setMessage("We are checking..");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            allowAccessToAccount(phone,password);
        }

    }

    private void allowAccessToAccount(final String phone, final String password)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference RootRef = database.getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.child("Users").child(phone).exists())
                {
                    Users dataUsers = dataSnapshot.child("Users").child(phone).getValue(Users.class);
                    if(dataUsers.getPhone().equals(phone))
                    {
                        if(dataUsers.getPassword().equals(password))
                        {
                            Toast.makeText(LoginActivity.this, "Logged in successfully ",Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(intent);
                        }
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Account with this "+ phone +" number do not exist",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }
                if(dataSnapshot.child("Admin").child(phone).exists())
                {
                    Admin adminData = dataSnapshot.child("Admin").child(phone).getValue(Admin.class);
                    if(adminData.getPhone().equals(phone))
                    {
                        if(adminData.getPassword().equals(password))
                        {
                            Toast.makeText(LoginActivity.this, "Welcome admin... ",Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent intent = new Intent(LoginActivity.this,AdminAddCategoryActivity.class);
                            startActivity(intent);
                        }
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Admin with this "+ phone +" number do not exist",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
