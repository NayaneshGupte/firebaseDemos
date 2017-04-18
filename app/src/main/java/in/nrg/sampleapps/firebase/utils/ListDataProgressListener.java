package in.nrg.sampleapps.firebase.utils;

public interface ListDataProgressListener {

    void onListDataFetchStarted();

    void onListDataLoaded();

    void onListDataLoadingCancelled();
}