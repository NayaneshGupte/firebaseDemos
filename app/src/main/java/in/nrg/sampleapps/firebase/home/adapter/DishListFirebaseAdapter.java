package in.nrg.sampleapps.firebase.home.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import in.nrg.sampleapps.firebase.dishdetails.model.DishDetails;
import in.nrg.sampleapps.firebase.utils.OnRecycleViewItemClickListener;

import static in.nrg.sampleapps.firebase.utils.VendorConstants.KEY_SINGLE_DISH_DETAILS;
import static in.nrg.sampleapps.firebase.utils.VendorConstants.POSITION;

/**
 * Firebase provides FirebaseRecyclerAdapter. Benefit of using FirebaseRecyclerAdapter over normal
 * RecyclerViewAdapter is, it accommodates changes in data without any extra code.
 *
 * @author Nayanesh Gupte
 */
public class DishListFirebaseAdapter extends FirebaseRecyclerAdapter<DishDetails, DishListViewHolder> {
    private DatabaseReference mRef;
    private ChildEventListener mChildEventListener;
    private Context mContext;

    private OnRecycleViewItemClickListener onRecycleViewItemClickListener;


    public DishListFirebaseAdapter(Context context, Class<DishDetails> modelClass, int modelLayout,
                                   Class<DishListViewHolder> viewHolderClass,
                                   Query ref,
                                   ChildEventListener childEventListener) {

        super(modelClass, modelLayout, viewHolderClass, ref);

        mRef = ref.getRef();
        mContext = context;
        mChildEventListener = childEventListener;
    }

    public void setOnRecycleViewItemClickListener(OnRecycleViewItemClickListener onRecycleViewItemClickListener) {
        this.onRecycleViewItemClickListener = onRecycleViewItemClickListener;
    }

    @Override
    protected void populateViewHolder(final DishListViewHolder viewHolder, final DishDetails model, final int position) {
        viewHolder.bindDishDtails(model);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(POSITION, position);
                bundle.putParcelable(KEY_SINGLE_DISH_DETAILS, model);
                if (null != onRecycleViewItemClickListener) {
                    onRecycleViewItemClickListener.onItemClicked(bundle);
                }
            }
        });
    }


    @Override
    public void cleanup() {
        super.cleanup();
        mRef.removeEventListener(mChildEventListener);
    }

    @Override
    protected void onCancelled(DatabaseError error) {
        super.onCancelled(error);
    }
}