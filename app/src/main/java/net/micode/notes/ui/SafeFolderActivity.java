package net.micode.notes.ui;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;



import androidx.annotation.NonNull;


import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.micode.notes.R;
import net.micode.notes.ui.adapter.MyAdapter;
import net.micode.notes.ui.bean.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SafeFolderActivity extends Activity {

    private RecyclerView mRecyclerView;
    private Button mBtnNew;
    private List<Note> mNotes;
    private MyAdapter mMyAdapter;

    private NoteDbOpenHelper mNoteDbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_folder);

        initView();
        initData();
        initEvent();


    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshDataFromDb();

    }

    private void refreshDataFromDb() {
        mNotes = getDataFromDB();
        mMyAdapter.refreshData(mNotes);
    }

    private void initEvent() {
        mMyAdapter = new MyAdapter(this,mNotes);
        mRecyclerView.setAdapter(mMyAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initData() {
        mNotes = new ArrayList<>();
        mNoteDbOpenHelper = new NoteDbOpenHelper(this);

    }

    private List<Note> getDataFromDB() {
        return mNoteDbOpenHelper.queryAllFromDb();
    }

    private String getCurrentTimeFormat(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }


    private void initView(){

        mRecyclerView = findViewById(R.id.sf_list);
    }

    public void NewNotes(View view) {
        Intent intent = new Intent(SafeFolderActivity.this,AddActivity.class);
        startActivity(intent);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        SearchView searchView = (SearchView) menu.findItem(R.id.sf_menu_search).getActionView();
//
//        
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                mNotes = mNoteDbOpenHelper.queryFromDbByContent(newText);
//                mMyAdapter.refreshData(mNotes);
//                return true;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.safe, menu);
            MenuItem x = menu.findItem(R.id.sf_menu_search);
            if (x.getActionView() != null) {
                Log.i("-------------","okk");
            }else{
                Log.i("-------------","false");
            }
            SearchView searchView = (SearchView) menu.findItem(R.id.sf_menu_search).getActionView();


            if (searchView != null) {
                Log.i("=====================","okk");

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        mNotes = mNoteDbOpenHelper.queryFromDbByContent(s);
                        mMyAdapter.refreshData(mNotes);
                        return true;
                    }
                });
            }else{
                Log.i("=====================","false");
            }
        return super.onCreateOptionsMenu(menu);
        }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}