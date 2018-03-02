package com.example.gustavo.lista;

import android.app.ListActivity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

//Gustavo De Leon 17085
//Push
//Cambios
public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private Adapter adapter;
    private ArrayList<ModeloLlenar> model = new ArrayList<>();;
    private FloatingActionButton fab;

    private CompositeDisposable compositeDisposable;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compositeDisposable = new CompositeDisposable();
      //  crearLista();
        buildListView();
        UserDataBase userDataBase = UserDataBase.getInstance(this);
        userRepository = UserRepository.getInstance(UserDataSource.getInstance(userDataBase.userDAO()));
        loadData();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Disposable disposable = io.reactivex.Observable.create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> e) throws Exception {
                        ModeloLlenar modeloLlenar = new ModeloLlenar("Animal-1:","Perro","Casa" );
                        userRepository.insertUser(modeloLlenar);
                        e.onComplete();
                    }
                })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer() {
                                       @Override
                                       public void accept(Object o) throws Exception {
                                           Toast.makeText(MainActivity.this, "Se agrego ususario", Toast.LENGTH_SHORT).show();
                                       }
                                   }, new Consumer<Throwable>() {
                                       @Override
                                       public void accept(Throwable throwable) throws Exception {
                                           Toast.makeText(MainActivity.this, "" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                       }
                                   },
                                new Action() {
                                    @Override
                                    public void run() throws Exception {
                                        loadData();
                                    }
                                }

                        );

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

   /* public void crearLista(){
        model = new ArrayList<>();
        model.add(new ModeloLlenar("Animal-1:",1,"Perro","Casa"));
        model.add(new ModeloLlenar("Animal-2:",2,"Gato","Casa"));
        model.add(new ModeloLlenar("Animal-3:",3,"Oveja","Granja"));
        model.add(new ModeloLlenar("Animal-4:",4,"Caballo","Granja"));
        model.add(new ModeloLlenar("Animal-5:",5,"Vaca","Granja"));

    }*/

    public void buildListView(){

        adapter = new Adapter(this,model);
        lista = (ListView)findViewById(R.id.listaU);
        fab =(FloatingActionButton) findViewById(R.id.fab);
        lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("ejemplo",model.get(i));
                startActivity(intent);

            }
        });
    }


}
