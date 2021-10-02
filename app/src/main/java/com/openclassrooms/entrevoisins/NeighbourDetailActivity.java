package com.openclassrooms.entrevoisins;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.EventLogTags;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.FavoriteFragment;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NeighbourDetailActivity extends AppCompatActivity {

    // declaration variables
    private ImageView imageAvatar;
    private TextView textAvatar;
    private ImageButton imageReturn;
    private ImageButton imageStar;
    private TextView textName;
    private TextView textadress;
    private TextView textPhone;
    private TextView textMail;
    private TextView description;
    private Neighbour neighbourName;
    private NeighbourApiService mApiService;
    private Neighbour favNeighbour;
    private int position;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neighbour_detail_activity);
        mApiService = DI.getNeighbourApiService();

        // recupérer l'Intent et lui passer les infos liées à la Clé
        neighbourName= (Neighbour) getIntent().getSerializableExtra("Neighbour");
        position = getIntent().getIntExtra("Position", 0);



        // référencement éléments graphiques dans code Java
        imageAvatar = (ImageView) findViewById(R.id.imageAvatar);
        textAvatar = (TextView) findViewById(R.id.textAvatar);
        imageReturn = (ImageButton) findViewById(R.id.imageReturn);
        imageStar = (ImageButton) findViewById(R.id.imageStar);
        textName = (TextView) findViewById(R.id.textName);
        textadress = (TextView) findViewById(R.id.textAdress);
        textPhone = (TextView) findViewById(R.id.textPhone);
        textMail = (TextView) findViewById(R.id.textMail);
        description = (TextView) findViewById(R.id.Description);

if (mApiService.getFavoritesNeighbours().contains(neighbourName)){
    imageStar.setImageResource(R.drawable.ic_star);

}else{
    imageStar.setImageResource(R.drawable.ic_star_border_white_24dp);
}


    imageStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mApiService.getFavoritesNeighbours().contains(favNeighbour)){
                mApiService.createFavoritesNeighbours(favNeighbour);
                imageStar.setImageResource(R.drawable.ic_star);
                Toast.makeText(getApplicationContext(),favNeighbour.getName() + " " + "a été ajouté en favori",Toast.LENGTH_LONG).show();
            }else {
                    imageStar.setImageResource(R.drawable.ic_star_border_white_24dp);
                }
            }
        });


        //création méthodes qui recupèrent infos
      validateProfile();
        linkProfile();
              }

    public void validateProfile() {
//message qui affiche la récupération du nom
        Toast.makeText(getApplicationContext(), neighbourName.getName(), Toast.LENGTH_LONG).show();
    }

    public void linkProfile() {
// permet d'associer le layout avec les informations de la classe "model"
        favNeighbour = mApiService.getPositionOfNeighbour(position);
        Glide.with(this).load(neighbourName.getAvatarUrl()).into(imageAvatar);
        textAvatar.setText(neighbourName.getName());
        textName.setText(neighbourName.getName());
        textadress.setText(neighbourName.getAddress());
        textPhone.setText(neighbourName.getPhoneNumber());
        textMail.setText(String.format("%s%s", getString(R.string.AdressUrl), neighbourName.getName().toLowerCase()));
        description.setText(neighbourName.getAboutMe());
    }

    public void buttonReturn(View view){
        Intent intent= new Intent(this,ListNeighbourActivity.class);
        startActivity(intent);
        finish();
    }

}











