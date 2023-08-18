package com.example.loginvalidation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword, edtAsalSekolah, edtNamaLengkap, edtEmail;
    Button btnSimpan;
    TextView textPassword;
    public static final String FILENAME = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("halaman depan");

        textPassword = findViewById(R.id. txtViewPassword);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtNamaLengkap = findViewById(R.id.edtNama);
        edtAsalSekolah = findViewById(R.id.edtSekolah);
        btnSimpan = findViewById(R.id.btnSimpan);

        btnSimpan.setVisibility(View.GONE);
        textPassword.setVisibility(View.GONE);
        edtPassword.setVisibility(View.GONE);

        edtUsername.setEnabled(false);
        edtEmail.setEnabled(false);
        edtNamaLengkap.setEnabled(false);
        edtAsalSekolah.setEnabled(false);

        readLoginFile();
    }
    private void readLoginFile(){
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);
        if (file.exists()){
           StringBuilder text = new StringBuilder();
           try {
               BufferedReader br = new BufferedReader(new FileReader(file));
               String line = br.readLine();
               while (line != null){
                   text.append(line);
                   line = br.readLine();
               }
               br.close();
           }catch (IOException e){
               System.out.println("eror" + e.getMessage());
           }
           String data = text.toString();
           String [] dataUser = data.split(";");
           readUserData(dataUser[0]);
        }
    }

    private void readUserData(String userName) {

        File sdcard = getFilesDir();
        File file = new File(sdcard, "user_data.txt");

        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();

//                    String[] userData = line.split(";");
//                    // Misalnya, data pengguna disimpan dalam format: username;email;nama_sekolah
//                    String storedUsername = userData[0];
//                    String email = userData[1];
//                    String schoolName = userData[2];
//
//                    // Lakukan sesuatu dengan data pengguna yang dibaca, misalnya tampilkan di EditText
//                    if (userName.equals(storedUsername)) {
//                        edtUsername.setText(storedUsername);
//                        edtEmail.setText(email);
//                        edtAsalSekolah.setText(schoolName);
//                        break;
//                    }
                }
                br.close();
            } catch (IOException e) {
//                e.printStackTrace();
                System.out.println("Error " + e.getMessage());
            }
            String data = text.toString();
            String[] dataUser = data.split(";");
            edtUsername.setText(dataUser[0]);
            edtEmail.setText(dataUser[2]);
            edtNamaLengkap.setText(dataUser[3]);
            edtAsalSekolah.setText(dataUser[4]);
        }else {
            Toast.makeText(this, "User Tidak Ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            tampilkanDialogKonfirmasiLogout();
            return true; // Mengembalikan true karena event sudah ditangani
        }
        return super.onOptionsItemSelected(item);
    }


    void hapusFile() {
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }

    void tampilkanDialogKonfirmasiLogout() {
        new AlertDialog.Builder(this).setTitle("Logout").setMessage("Apakah Anda yakin ingin Logout?").setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                hapusFile();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }).setNegativeButton(android.R.string.no, null).show();
    }


}