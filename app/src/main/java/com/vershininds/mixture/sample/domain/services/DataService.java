package com.vershininds.mixture.sample.domain.services;

import com.vershininds.mixture.sample.data.SampleObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Single;


public class DataService {

    @Inject
    public DataService(){

    }

    public enum LoadType {
        NORMAL, EMPTY_DATA, ERROR_DATA
    }

    /**
     * rx method
     *
     * @return random data list as Single the Reactive Pattern
     */
    public Single<List<SampleObject>> obtainDataRx(LoadType type){
        Single<List<SampleObject>> observable;

        switch (type){
            case EMPTY_DATA:
                observable = Single.just(Collections.<SampleObject>emptyList());
                break;
            case ERROR_DATA:
                observable = Single.error(new Throwable("Test error: Some error while loading data"));
                break;
            default:
                observable = Single.fromCallable(this::randomData);
        }
        return observable.delay(10, TimeUnit.SECONDS);//delay emulate long load
    }

    private List<SampleObject> randomData(){
        List<SampleObject> data = new ArrayList<>();

        int min = 25;
        int max = 99;

        Random r = new Random();
        int i = r.nextInt(max - min + 1) + min;

        for (int j = 0; j < i; j++){
            SampleObject sampleObject = new SampleObject(
                    "name_" + j,
                    "desc_" + UUID.randomUUID().toString()
            );

            data.add(sampleObject);
        }

        return data;
    }
}
