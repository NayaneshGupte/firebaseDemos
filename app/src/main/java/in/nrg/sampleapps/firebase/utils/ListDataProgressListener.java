package in.nrg.sampleapps.firebase.utils;

/**
 * Handle progress while fetching data from Firebase
 *
 * @author Nayanesh Gupte
 */
public interface ListDataProgressListener {

    void onListDataFetchStarted();

    void onListDataLoaded();

    void onListDataLoadingCancelled();
}