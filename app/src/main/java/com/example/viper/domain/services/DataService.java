package com.example.viper.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import com.example.viper.data.SampleObject;
import rx.Observable;

public class DataService {

    @Inject
    public DataService(){

    }

    public Observable<List<SampleObject>> obtainData(){
        List<SampleObject> data = new ArrayList<>();

        int min = 25;
        int max = 99;

        Random r = new Random();
        int i = r.nextInt(max - min + 1) + min;

        for (int j = 0; j < i; j++){
            SampleObject sampleObject = new SampleObject();
            sampleObject.setName("name_" + String.valueOf(j));
            sampleObject.setDescription("desc_" + UUID.randomUUID().toString());

            data.add(sampleObject);
        }

        return Observable.just(data).delay(10, TimeUnit.SECONDS);//delay emulate long load
    }
}
