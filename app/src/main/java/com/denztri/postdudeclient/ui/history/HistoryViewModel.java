package com.denztri.postdudeclient.ui.history;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.denztri.postdudeclient.MainActivity;
import com.denztri.postdudeclient.database.AppDatabase;
import com.denztri.postdudeclient.database.entity.RequestHistoryModel;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class HistoryViewModel extends AndroidViewModel {
    private MutableLiveData<List<RequestHistoryModel>> historyData;
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final AppDatabase db;

    public HistoryViewModel(Application application) {
        super(application);
        this.db = AppDatabase.getInstance(application);
    }

    public void insertOne(RequestHistoryModel model){
        if (historyData == null) historyData = new MutableLiveData<>();
        executor.execute(() -> {
            db.requestHistoryDao().insertOne(model);
            loadHistory();
        });
    }

    public LiveData<List<RequestHistoryModel>> getAllHistory(){
        if (historyData == null) historyData = new MutableLiveData<>();
        executor.execute(() -> {
            if (db.requestHistoryDao().checkDb() > 0){
                loadHistory();
            }

        });

        return historyData;
    }

    private void loadHistory(){
        executor.execute(() -> {
            List<RequestHistoryModel> list = db.requestHistoryDao().getAllHistory();
            handler.post(() -> historyData.setValue(list));
        });
    }
}