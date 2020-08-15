package com.thecodevillage.remindme;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TodoItemDao todoItemDao;
    List<TodoItem> todoItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DaoSession daoSession= ((MainApplication) getApplication()).getDaoSession();
        todoItemDao = daoSession.getTodoItemDao();


        if (todoItemDao.queryBuilder().count() == 0){
            TodoItem todoItem=new TodoItem();
            todoItem.setName("Test Item");
            todoItem.setStatus(true);

            todoItemDao.save(todoItem);

        }


        final RecyclerView rvItems= findViewById(R.id.rvItems);
        todoItemList = todoItemDao.queryBuilder().list();


        TodoItemAdapter todoItemAdapter=new TodoItemAdapter(todoItemList);
        rvItems.setAdapter(todoItemAdapter);


        rvItems.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);

                final View customLayout= getLayoutInflater().inflate(R.layout.dialog_layout,null);
                builder.setView(customLayout);
                builder.setTitle("Add Item")
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                EditText txtName=customLayout.findViewById(R.id.edtItemName);

                                String name= txtName.getText().toString();

                                TodoItem todoItem=new TodoItem();
                                todoItem.setName(name);
                                todoItem.setStatus(false);
                                todoItemDao.save(todoItem);


                                finish();
                                overridePendingTransition(0,0);
                                startActivity(getIntent());

                                Toast.makeText(MainActivity.this,"Item added",Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create()
                        .show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
