package com.example.scholarshelf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_BOOK_REQUEST = 1;
    private List<Textbook> textbookList = new ArrayList<>();
    private TextbookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        SearchView searchView = findViewById(R.id.searchView);
        Button addBookBtn = findViewById(R.id.addBookBtn);

        adapter = new TextbookAdapter(textbookList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        addBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddBook.class);
                startActivityForResult(intent, ADD_BOOK_REQUEST);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_BOOK_REQUEST && resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("title");
            String seller = data.getStringExtra("seller");
            int copies = data.getIntExtra("copies", 1);
            double price = data.getDoubleExtra("price", 0.0);
            String bankInfo = data.getStringExtra("bankInfo");

            // Check for duplicates
            for (Textbook textbook : textbookList) {
                if (textbook.getTitle().equalsIgnoreCase(title) &&
                        textbook.getSellerName().equalsIgnoreCase(seller)) {
                    Toast.makeText(this, "Book already exists!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            // Add new book
            Textbook newBook = new Textbook(title, seller, copies, price, bankInfo);
            textbookList.add(newBook);
            adapter.updateFullList(new ArrayList<>(textbookList));
            adapter.notifyDataSetChanged();
        }
    }
}