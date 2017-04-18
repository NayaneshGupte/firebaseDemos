package in.nrg.sampleapps.firebase.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import in.nrg.sampleapps.firebase.R;
import in.nrg.sampleapps.firebase.dishdetails.model.DishDetails;
import in.nrg.sampleapps.firebase.home.fragment.HomePageFragment;

/**
 * ViewHolder class representing data in single row on {@link HomePageFragment}
 *
 * @author Nayanesh Gupte
 */
public class DishListViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;

    View itemView;

    private CircleImageView imagevDish;

    private TextView textViewDishName;
    private TextView textViewContent;
    private TextView textViewTime;

    public DishListViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        mContext = itemView.getContext();
        imagevDish = (CircleImageView) itemView.findViewById(R.id.imageItem);
        textViewDishName = (TextView) itemView.findViewById(R.id.textViewDishName);
        textViewContent = (TextView) itemView.findViewById(R.id.textViewContent);
        textViewTime = (TextView) itemView.findViewById(R.id.textViewTime);
    }

    void bindDishDtails(DishDetails dishDetails) {
        //image loading library to load images using urls
        Picasso.with(mContext)
                .load(dishDetails.getImageUrl())
                .placeholder(R.drawable.chef)
                .error(R.drawable.chef)
                .into(imagevDish);

        //set dish name
        textViewDishName.setText(dishDetails.getDishName());
        //set dis content
        textViewContent.setText(String.format("Content: %s", dishDetails.getDishContents()));
        //set time to cook
        textViewTime.setText(String.format("Time To Cook: %s mins", dishDetails.getTimeToCook()));
    }
}
