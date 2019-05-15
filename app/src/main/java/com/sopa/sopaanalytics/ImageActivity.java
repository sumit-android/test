package com.sopa.sopaanalytics;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.sopa.sopaanalytics.fragments.CrausolFragment;
import com.sopa.sopaanalytics.fragments.FullFragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageActivity extends AppCompatActivity implements CrausolFragment.OnFragmentInteractionListener {


    ImageView ad_image_view;

    private FullFragment fullFragment;
    private CrausolFragment crausolFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);


        fullFragment = FullFragment.newInstance();
        crausolFragment = CrausolFragment.newInstance();

        //ad_image_view = findViewById(R.id.ad_image_view);
        // FloatingActionButton fab = findViewById(R.id.fab);


       /* fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageGallery();
            }
        });*/


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.crouselContainer, crausolFragment, "2");
        if (fullFragment.isAdded()) {
            fragmentTransaction.hide(fullFragment);
        }
        fragmentTransaction.commit();

    }

    private void openImageGallery() {

        Intent imagePicker = new Intent(Intent.ACTION_GET_CONTENT);
        imagePicker.setType("image/*");
        startActivityForResult(Intent.createChooser(imagePicker, "select picture"), 1);
    }


    void FullScreenFragment(byte[] bytes) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(crausolFragment);
        Bundle b = new Bundle();
        b.putByteArray("imageByte", bytes);
        fullFragment.setArguments(b);
        fragmentTransaction.add(R.id.editorFragment, fullFragment, "1");
        fragmentTransaction.commit();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            Uri uri = data.getData();

            Bitmap imageBitmap = null;
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] bytes = stream.toByteArray();

            FullScreenFragment(bytes);
            // ad_image_view.setImageBitmap(imageBitmap);
        }

    }

    @Override
    public void onFragmentInteraction() {
        openImageGallery();
    }
}
