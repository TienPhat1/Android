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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity
{

    private EditText inputPhoneNumber, inputPassword,inputConfirmPassword, inputEmail,inputName;
    private ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button createAccountBtn = (Button) findViewById(R.id.create_btn);
        inputPhoneNumber = (EditText) findViewById(R.id.reg_num_phone);
        inputPassword = (EditText) findViewById(R.id.reg_password);
        inputConfirmPassword = (EditText) findViewById(R.id.reg_confirm_password);
        inputEmail = (EditText) findViewById(R.id.reg_email);
        inputName = (EditText) findViewById(R.id.reg_name);

        loadingBar = new ProgressDialog(this);

        createAccountBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });


    }

    private void createAccount()
    {
        String phone = inputPhoneNumber.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmpassword = inputConfirmPassword.getText().toString();
        String email = inputEmail.getText().toString();
        String name = inputName.getText().toString();
        if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(this,"Please write phone number...",Toast.LENGTH_SHORT).show();
        }
        else if(password.length() < 6)
        {
            Toast.makeText(this,"Please write password greater than or equal to 6 ...",Toast.LENGTH_SHORT).show();
        }
        else if(!(password.equals(confirmpassword)))
        {
            Toast.makeText(this,"Password no match ...",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please write email ...",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(name))
        {
            Toast.makeText(this,"Please write name ...",Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Create account");
            loadingBar.setMessage("We are checking..");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validtedAccount(phone,password,email,name);
        }

    }

    private void validtedAccount(final String phone, final String password, final String email, final String name)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference RootRef = database.getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!(dataSnapshot.child("Users").child(phone).exists()))
                {
                    HashMap<String,Object> myData = new HashMap<>();
                    myData.put("phone",phone);
                    myData.put("password",password);
                    myData.put("email",email);
                    myData.put("name",name);

                    RootRef.child("Users").child(phone).updateChildren(myData).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(RegisterActivity.this,"Account has been created",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this,"Network error : Please try again",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Phone number has already exits, please try again",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}
