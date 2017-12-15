package com.vershininds.mixture.sample.domain.services;

import com.vershininds.mixture.sample.data.SampleObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Single;
import rx.Observable;


public class DataService {

    @Inject
    public DataService(){

    }


    /**
     * rx2 method
     *
     * @return random data list as Observable
     */
    public Observable<List<SampleObject>> obtainData(){
        return Observable.just(randomData()).delay(10, TimeUnit.SECONDS);//delay emulate long load
    }

    /**
     * rx2 method
     *
     * @return random data list as Single the Reactive Pattern
     */
    public Single<List<SampleObject>> obtainDataRx2(){
        return Single.just(randomData()).delay(10, TimeUnit.SECONDS);//delay emulate long load
    }

    private List<SampleObject> randomData(){
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

        return data;
    }
}
