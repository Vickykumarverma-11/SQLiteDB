package com.riteshkm.db;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaRouter;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    _Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    PersonViewModel personViewModel;

    public static final int requestcode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.RecycleView);
        floatingActionButton = findViewById(R.id.fb);
        adapter = new _Adapter(this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        personViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);
        personViewModel.getAllPerson().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(List<Person> people) {
                adapter.setPersonList(people);
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,newPersonActivity.class);
                startActivityForResult(intent,requestcode);
                //finish();
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

//                final int fromPos = viewHolder.getAdapterPosition();
//                final int toPos = target.getAdapterPosition();
//                // move item in `fromPos` to `toPos` in adapter.
                return false;// true if moved, false otherwise
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int touchposition = viewHolder.getAdapterPosition();
                personViewModel.delete(adapter.getPersonAt(touchposition));
                Toast.makeText(getApplicationContext(),"Entry Deleted Sucessfully",Toast.LENGTH_SHORT).show();

            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == requestcode) {
            if(resultCode == RESULT_OK) {

                String name = data.getStringExtra(newPersonActivity.Name_Extra);
                String mob = data.getStringExtra(newPersonActivity.Mob_Extra);

                Person person = new Person(name,Long.parseLong(mob));
                personViewModel.insert(person);
                Toast.makeText(getApplicationContext(),"New Person Added",Toast.LENGTH_SHORT).show();

            }
        }
    }
}
