package com.touka.clase05_neto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pidiendoPermisos();

        //OBTENIENDO COMPONENTES
        MaterialButton boton_job = findViewById(R.id.btn_start_job);
        MaterialButton btn_start_service = findViewById(R.id.btn_start_service);


        //GESTIONANDO EVENTO DEL BOTON
        boton_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComponentName componentName = new ComponentName(getApplicationContext(), Job.class);
                JobInfo info;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    info = new JobInfo.Builder(99, componentName)
                            .setPersisted(true)
                            .setMinimumLatency(5*1000)
                            .build();
                }else{
                    info = new JobInfo.Builder(99, componentName)
                            .setPersisted(true)
                            .setMinimumLatency(5*1000)
                            .build();
                }
                JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                int resultado = scheduler.schedule(info);
                if(resultado == JobScheduler.RESULT_SUCCESS){
                    Toast.makeText(MainActivity.this, "FUNCIONO", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "NO FUNCIONO", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getApplicationContext(), MiServicio.class));
            }
        });

    }
    public void pidiendoPermisos(){
        if(
                ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECEIVE_BOOT_COMPLETED)!= PackageManager.PERMISSION_GRANTED
        ){
            Toast.makeText(MainActivity.this, "LOS PERMISOS YA FUERON OTORGADOS", Toast.LENGTH_SHORT).show();
            if(
                    ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.RECEIVE_BOOT_COMPLETED)
            ){
                Toast.makeText(MainActivity.this, "LOS PERMISOS YA FUERON PEDIDOS ", Toast.LENGTH_SHORT).show();
            }else{
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                        Manifest.permission.RECEIVE_BOOT_COMPLETED,
                },23);
            }
        }else{
            Toast.makeText(MainActivity.this, "LOS PERMISOS YA FUERON OTORGADOS", Toast.LENGTH_SHORT).show();
        }
    }
}