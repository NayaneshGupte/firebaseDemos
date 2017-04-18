package in.nrg.sampleapps.firebase.dishdetails.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import in.nrg.sampleapps.firebase.R;
import in.nrg.sampleapps.firebase.dishdetails.manager.DishUploadManager;

import static android.app.Activity.RESULT_OK;

/**
 * Upload new dish for specific user
 *
 * @author Nayanesh Gupte
 */
public class DishUploadFragment extends Fragment implements View.OnClickListener,
        DishUploadManager.DishUploadListener {

    //a constant to track the file chooser intent
    private static final int PICK_IMAGE_REQUEST = 234;

    private ImageView imageDish;
    private EditText editDishName;
    private EditText editDishContents;
    private EditText edtTxtTimeToCook;
    private EditText edtTxtrestaurantName;
    private Button btnUploadDish;

    //a Uri object to store file path
    private Uri filePath;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dish_upload, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageDish = (ImageView) view.findViewById(R.id.imageDish);

        btnUploadDish = (Button) view.findViewById(R.id.dishButton);

        editDishName = (EditText) view.findViewById(R.id.editDishName);
        editDishContents = (EditText) view.findViewById(R.id.editDishContents);
        edtTxtTimeToCook = (EditText) view.findViewById(R.id.TimeToCook);
        edtTxtrestaurantName = (EditText) view.findViewById(R.id.editrestaurantName);


        imageDish.setOnClickListener(this);
        btnUploadDish.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle(getString(R.string.upload_dish));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dishButton:
                uploadDishData();
                break;
            case R.id.imageDish:
                showFileChooser();
                break;
        }
    }


    private void uploadDishData() {
        //Getting values to store
        String restaurantName = edtTxtrestaurantName.getText().toString().trim();
        String dishName = editDishName.getText().toString().trim();
        String dishContents = editDishContents.getText().toString().trim();
        String timeToCook = edtTxtTimeToCook.getText().toString().trim();

        if (TextUtils.isEmpty(restaurantName)) {
            edtTxtrestaurantName.setError(getString(R.string.restaurant_validation_message));
            edtTxtrestaurantName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(dishName)) {
            editDishName.setError(getString(R.string.dishname_validation_message));
            editDishName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(dishContents)) {
            editDishContents.setError(getString(R.string.dishcontent_validation_message));
            editDishContents.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(timeToCook)) {
            edtTxtTimeToCook.setError(getString(R.string.timetocook_validation_message));
            edtTxtTimeToCook.requestFocus();
            return;
        }

        if (null == filePath) {
            Toast.makeText(getActivity(), R.string.image_validation_message, Toast.LENGTH_SHORT).show();
            return;
        }

        DishUploadManager dishUploadManager = DishUploadManager.getInstance();
        dishUploadManager.setDishUploadListener(this);
        dishUploadManager.uploadDishDetails(restaurantName, dishName, dishContents, timeToCook, filePath);
    }

    /**
     * method to show file chooser
     */
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.select_picture)),
                PICK_IMAGE_REQUEST);
    }

    //handling the image chooser activity result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                imageDish.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDishUploadCompeted() {
        //set empty / default data before uploading new dish
        imageDish.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.chef));
        edtTxtrestaurantName.setText("");
        editDishName.setText("");
        editDishContents.setText("");
        edtTxtTimeToCook.setText("");
    }
}