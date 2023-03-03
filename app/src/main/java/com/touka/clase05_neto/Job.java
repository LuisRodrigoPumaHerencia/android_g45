package com.touka.clase05_neto;

import android.app.job.JobParameters;
import android.util.Log;

public class Job extends android.app.job.JobService{

    @Override
    public boolean onStartJob(JobParameters params) {
        imprimirNumero(params);
        return false;
    }

    public void imprimirNumero(final JobParameters parameters){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 15; i++) {
                    Log.d("NUMEROS", String.valueOf(i));
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                jobFinished(parameters, false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
