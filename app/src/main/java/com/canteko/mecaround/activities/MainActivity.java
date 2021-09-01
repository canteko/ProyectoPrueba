package com.canteko.mecaround.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;

import com.canteko.mecaround.R;
import com.canteko.mecaround.databinding.ActivityMainBinding;
import com.canteko.mecaround.fragments.EditAveriaFragment;
import com.canteko.mecaround.fragments.NuevaAveriaDialogo;
import com.canteko.mecaround.interfaces.OnAveriaInteractionListener;
import com.canteko.mecaround.interfaces.OnNuevaAveriaListener;
import com.canteko.mecaround.models.AveriaDB;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements OnAveriaInteractionListener, OnNuevaAveriaListener {

    private ActivityMainBinding binding;
    private DialogFragment dialogNuevaAveria;
    private DialogFragment dialogEditAveria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogNuevaAveria = new NuevaAveriaDialogo();
                dialogNuevaAveria.show(getSupportFragmentManager(), "NuevaAveriaDialogo");
            }
        });
    }

    @Override
    public void onAveriaClick(AveriaDB averia) {

    }

    @Override
    public void onAveriaEdit(AveriaDB averiaDB) {
        this.dialogEditAveria = EditAveriaFragment.newInstance(averiaDB.getId(), averiaDB.getTitulo(), averiaDB.getDescripcion(), averiaDB.getModeloCoche());
        dialogEditAveria.show(getSupportFragmentManager(), "EditAveria");
    }

    @Override
    public void onAveriaGuardarListener(final String titulo, final String descripcion, final String modeloCoche) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                AveriaDB nuevaAveria = new AveriaDB();
                nuevaAveria.setTitulo(titulo);
                nuevaAveria.setModeloCoche(modeloCoche);
                nuevaAveria.setNumeroPresupuestos(0);
                nuevaAveria.setUrlFoto("");
                nuevaAveria.setDescripcion(descripcion);

                realm.copyToRealm(nuevaAveria);
            }
        });
    }

    @Override
    public void onAveriaEditarListener(final String titulo, final String descripcion, final String modeloCoche, final long idAveria) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                AveriaDB nuevaAveria = new AveriaDB();
                nuevaAveria.setId(idAveria);
                nuevaAveria.setTitulo(titulo);
                nuevaAveria.setModeloCoche(modeloCoche);
                nuevaAveria.setNumeroPresupuestos(0);
                nuevaAveria.setUrlFoto("");
                nuevaAveria.setDescripcion(descripcion);

                realm.copyToRealmOrUpdate(nuevaAveria);
            }
        });
    }
}