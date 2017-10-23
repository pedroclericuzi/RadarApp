package pedroclericuzi.appradar.View;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import org.json.JSONException;
import org.json.JSONObject;

import pedroclericuzi.appradar.Controller.AsyncImagem;
import pedroclericuzi.appradar.Controller.HelperClass;
import pedroclericuzi.appradar.Controller.sdkFacebook;
import pedroclericuzi.appradar.R;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView nome_drawer,email_drawer;
    ImageView photo_drawer;
    HelperClass helperClass;
    sdkFacebook faceSdk;
    BottomBar bottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        faceSdk = new sdkFacebook();
        helperClass = new HelperClass(Main.this);

        photo_drawer = (ImageView) findViewById(R.id.photo_drawer);
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try {
                            if (faceSdk.estaLogado() && helperClass.isOnline()){
                                JSONObject picture = object.getJSONObject("picture");
                                JSONObject dataPic = picture.getJSONObject("data");
                                String url_picture = dataPic.getString("url");

                                String nome = object.getString("name");
                                String email = object.getString("email");

                                photo_drawer = (ImageView) findViewById(R.id.photo_drawer);
                                new AsyncImagem(photo_drawer).execute(url_picture); //Chamando uma classe assincrona para baixar a imagem e inserir no IV

                                nome_drawer = (TextView) findViewById(R.id.nome_drawer);
                                email_drawer = (TextView) findViewById(R.id.email_drawer);
                                //Log.d("Nome", nome);
                                nome_drawer.setText(nome);
                                email_drawer.setText(email);
                            } else {
                                Log.d("Profile - email", "nao ta logado");
                                helperClass.transicaoTela(Main.this, LoginUsuario.class);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,picture,email");
        request.setParameters(parameters);
        request.executeAsync();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //BottomBar
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                if (tabId == R.id.frag_radar) {
                    FragRadar fragRadar = new FragRadar();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragRadar).commit();
                } else if (tabId == R.id.frag_lista) {
                    FragLista fragLista = new FragLista();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragLista).commit();
                } else if (tabId == R.id.frag_perfil) {
                    FragPerfil fragPerfil = new FragPerfil();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragPerfil).commit();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_sair) {
            faceSdk.Sair();
            helperClass.transicaoTela(Main.this, LoginUsuario.class);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
