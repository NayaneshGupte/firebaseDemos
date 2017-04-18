package in.nrg.sampleapps.firebase.home.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.Query;

import in.nrg.sampleapps.firebase.R;
import in.nrg.sampleapps.firebase.dishdetails.model.DishDetails;
import in.nrg.sampleapps.firebase.home.adapter.DishListFirebaseAdapter;
import in.nrg.sampleapps.firebase.home.adapter.DishListViewHolder;
import in.nrg.sampleapps.firebase.home.manager.DishListRetriever;
import in.nrg.sampleapps.firebase.home.manager.HomeFragmentManager;
import in.nrg.sampleapps.firebase.utils.ListDataProgressListener;
import in.nrg.sampleapps.firebase.utils.OnRecycleViewItemClickListener;


/**
 * Home page fragment showing list of dishes with basic information
 *
 * @author Nayanesh Gupte
 */
public class HomePageFragment extends Fragment implements OnRecycleViewItemClickListener,
        ListDataProgressListener {

    private ProgressBar progressBar;
    private RecyclerView recyclerHome;
    private DishListFirebaseAdapter mFirebaseAdapter;

    private HomeFragmentManager homeFragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerHome = (RecyclerView) view.findViewById(R.id.recyclerHome);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerHome.setLayoutManager(layoutManager);

        DishListRetriever dishListRetriever = new DishListRetriever(this);
        Query query = dishListRetriever.fetchAllDishes();

        this.mFirebaseAdapter = new DishListFirebaseAdapter(getActivity(), DishDetails.class,
                R.layout.row_dish_details, DishListViewHolder.class,
                query, dishListRetriever);

        recyclerHome.setAdapter(mFirebaseAdapter);
        mFirebaseAdapter.setOnRecycleViewItemClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeFragmentManager = new HomeFragmentManager(getActivity());
        getActivity().setTitle(getString(R.string.home));

    }


    @Override
    public void onItemClicked(Bundle bundle) {
        homeFragmentManager.showDishDetails(bundle);
    }

    @Override
    public void onListDataFetchStarted() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onListDataLoaded() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onListDataLoadingCancelled() {
        progressBar.setVisibility(View.GONE);
    }
}
