package com.example.gustavo.lista;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    private ListView lista;

    private ArrayList<ModeloLlenar> model = new ArrayList<>();
    private ArrayAdapter adapter;



    private CompositeDisposable compositeDisposable;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        compositeDisposable = new CompositeDisposable();

        lista = (ListView) findViewById(R.id.Lista);

        adapter = new  ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, model);
        registerForContextMenu(lista);
        lista.setAdapter(adapter);

        UserDataBase userDatabase = UserDataBase.getInstance(this);
        userRepository = UserRepository.getInstance(UserDataSource.getInstance(userDatabase.userDAO()));

        loadData();





        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id){
                Toast.makeText(getApplicationContext(), "Position"+pos, Toast.LENGTH_SHORT).show();


                /*modelString allItems = new modelString(pos, (Country) ListView.getItemAtPosition(pos), new ArrayList<Country>());
                intent.putExtra(RecieverActivity.SELECTED_ITEM, allItems);

                startActivity(intent);*/
                ModeloLlenar envio = (ModeloLlenar) lista.getItemAtPosition(pos);
                String nombre = envio.getNombre();
                String descripcion = envio.getInfo().getDescripcion();
                String lugar = envio.getInfo().getLugar();

                Intent intent = new Intent(MainActivity.this, Activity2.class);
                modelString allItems = new modelString(nombre, descripcion, lugar, new ArrayList<String>());
                intent.putExtra(Activity2.SELECTED_ITEM, allItems);

                startActivity(intent);

            }


        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Disposable disposable = io.reactivex.Observable.create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> e) throws Exception{
                        ModeloLlenar ani1 = new ModeloLlenar("Perro", new Informacion("peludo", "casa"));
                        userRepository.insertUser(ani1);

                        ModeloLlenar ani2 = new ModeloLlenar("Gato", new Informacion("peludo", "casa"));
                        userRepository.insertUser(ani2);

                        ModeloLlenar ani3 = new ModeloLlenar("Vaca", new Informacion("manchada", "casa"));
                        userRepository.insertUser(ani3);

                        e.onComplete();
                    }
                })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer() {
                                       @Override
                                       public void accept(Object o) throws Exception{
                                           Toast.makeText(MainActivity.this, "User agregado", Toast.LENGTH_SHORT).show();
                                       }
                                   }, new Consumer<Throwable>(){
                                       @Override
                                       public void accept (Throwable throwable) throws Exception {
                                           Toast.makeText(MainActivity.this, ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                       }
                                   },
                                new Action() {
                                    @Override
                                    public void run() throws Exception {
                                        loadData();
                                    }
                                });

            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteAllUsers();
            }
        });
    }

    private void loadData(){
        Disposable disposable = userRepository.getAllUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<ModeloLlenar>>() {
                    @Override
                    public void accept(List<ModeloLlenar> users) throws Exception {
                        onGetAllUsersSucces(users);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void onGetAllUsersSucces(List<ModeloLlenar> users) {
        model.clear();
        model.addAll(users);
        adapter.notifyDataSetChanged();

    }

    private void deleteAllUsers(){
        Disposable disposable = io.reactivex.Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception{
                userRepository.deleteAllUsers();
                e.onComplete();
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer() {
                               @Override
                               public void accept(Object o) throws Exception{

                               }
                           }, new Consumer<Throwable>(){
                               @Override
                               public void accept (Throwable throwable) throws Exception {
                                   Toast.makeText(MainActivity.this, ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                               }
                           },
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                loadData();
                            }
                        });

    }
/*
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
*/




}


