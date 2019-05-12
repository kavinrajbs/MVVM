package com.kavinraj.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.kavinraj.model.NicePlace;
import com.kavinraj.repositries.NicePlaceRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<NicePlace>> mNicePlaces;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();
    private NicePlaceRepository mnicePlaceRepository;
    public void init()
    {
        if(mNicePlaces!=null)
        {
            return;
        }
        mnicePlaceRepository = NicePlaceRepository.getInstance();
        mNicePlaces=mnicePlaceRepository.getNicePlaces();
    }
    public void addValue(final NicePlace nicePlace)
    {
        mIsUpdating.setValue(true);
        new AsyncTask<Void,Void,Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<NicePlace> currentPlaces = mNicePlaces.getValue();
                currentPlaces.add(nicePlace);
                mNicePlaces.postValue(currentPlaces);
                mIsUpdating.postValue(false);
            }
        }.execute();
    }
    public LiveData<List<NicePlace>> getNicePlaces()
    {
        return mNicePlaces;
    }
    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}
