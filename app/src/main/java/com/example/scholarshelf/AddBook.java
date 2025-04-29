package com.example.scholarshelf;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddBook extends AppCompatActivity {

    private EditText editTitle, editSeller, editCopies, editPrice, editBankInfo;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book);

        // Initialize UI elements
        editTitle = findViewById(R.id.editTitle);
        editSeller = findViewById(R.id.editSeller);
        editCopies = findViewById(R.id.editCopies);
        editPrice = findViewById(R.id.editPrice);
        editBankInfo = findViewById(R.id.editBankInfo);
        submitBtn = findViewById(R.id.submitBtn);

        // Set button click listener
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Get input values
                    String title = editTitle.getText().toString().trim();
                    String seller = editSeller.getText().toString().trim();
                    int copies = Integer.parseInt(editCopies.getText().toString().trim());
                    double price = Double.parseDouble(editPrice.getText().toString().trim());
                    String bankInfo = editBankInfo.getText().toString().trim();

                    // Validate inputs
                    if (title.isEmpty() || seller.isEmpty() || bankInfo.isEmpty()) {
                        Toast.makeText(AddBook.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Send the data back to MainActivity
                    Intent result = new Intent();
                    result.putExtra("title", title);
                    result.putExtra("seller", seller);
                    result.putExtra("copies", copies);
                    result.putExtra("price", price);
                    result.putExtra("bankInfo", bankInfo);
                    setResult(RESULT_OK, result);
                    finish();

                } catch (NumberFormatException e) {
                    Toast.makeText(AddBook.this, "Please enter valid numbers for copies and price", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}