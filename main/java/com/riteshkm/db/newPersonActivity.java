package com.riteshkm.db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class newPersonActivity extends AppCompatActivity {

    EditText name,mob;
    Button add;

    public static final String Name_Extra = "My Extra Name";
    public static final String Mob_Extra = "My Extra Mobile_no";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);

        name = findViewById(R.id.et1);
        mob = findViewById(R.id.et2);
        add = findViewById(R.id.btn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(name.getText().toString())) {
                    name.setError("Can't be empty");
                    return;
                }
                if(TextUtils.isEmpty(mob.getText().toString())) {
                    mob.setError("Can't be empty");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(Name_Extra,name.getText().toString());
                intent.putExtra(Mob_Extra,mob.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}
