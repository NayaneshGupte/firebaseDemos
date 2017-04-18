package in.nrg.sampleapps.firebase.dishdetails.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import in.nrg.sampleapps.firebase.R;
import in.nrg.sampleapps.firebase.dishdetails.model.DishDetails;

import static in.nrg.sampleapps.firebase.utils.VendorConstants.KEY_SINGLE_DISH_DETAILS;

/**
 * Fragment to display complete details of dish.
 *
 * @author Nayanesh Gupte
 */
public class DishDetailFragment extends Fragment {

    private ImageView imgvDishPic;

    private TextView txtvDishName;
    private TextView txtvContent;

    private DishDetails dishDetails;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDataFromBundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dish_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgvDishPic = (ImageView) view.findViewById(R.id.imgvDishPic);
        txtvDishName = (TextView) view.findViewById(R.id.txtvDishName);
        txtvContent = (TextView) view.findViewById(R.id.txtvContent);

        setData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle(getString(R.string.dish_details));

    }

    private void getDataFromBundle() {
        Bundle bundle = getArguments();
        dishDetails = bundle.getParcelable(KEY_SINGLE_DISH_DETAILS);
    }

    private void setData() {
        //image loading library to load images using urls
        Picasso.with(getActivity())
                .load(dishDetails.getImageUrl())
                .placeholder(R.drawable.chef)
                .error(R.drawable.chef)
                .into(imgvDishPic);

        txtvDishName.setText(dishDetails.getDishName());

        txtvContent.setText(dishDetails.getDishContents());
    }
}
