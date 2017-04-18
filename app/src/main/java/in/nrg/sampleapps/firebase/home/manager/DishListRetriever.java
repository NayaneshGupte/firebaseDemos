package in.nrg.sampleapps.firebase.home.manager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import in.nrg.sampleapps.firebase.dishdetails.model.DishDetails;
import in.nrg.sampleapps.firebase.utils.ListDataProgressListener;

import static in.nrg.sampleapps.firebase.utils.VendorConstants.INDEX;
import static in.nrg.sampleapps.firebase.utils.VendorConstants.ROOT_NODE;

/**
 * Fire query to firebase and retrieve all dishes uploaded by one particular user
 *
 * @author Nayanesh Gupte
 */
public class DishListRetriever implements ChildEventListener, ValueEventListener {

    private ListDataProgressListener listDataProgressListener;

    public DishListRetriever(ListDataProgressListener listDataProgressListener) {
        this.listDataProgressListener = listDataProgressListener;
        this.listDataProgressListener.onListDataFetchStarted();
    }

    public Query fetchAllDishes() {
        listDataProgressListener.onListDataFetchStarted();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance()
                .getReference(ROOT_NODE)
                .child(uid)
                .orderByChild(INDEX);

        query.addChildEventListener(this);
        query.addListenerForSingleValueEvent(this);

        return query;
    }


    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        //convert dataSnapshot into POJO
        DishDetails dishDetails = dataSnapshot.getValue(DishDetails.class);
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {
    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        this.listDataProgressListener.onListDataLoaded();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        this.listDataProgressListener.onListDataLoadingCancelled();
    }
}
