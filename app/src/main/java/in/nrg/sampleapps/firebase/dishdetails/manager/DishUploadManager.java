package in.nrg.sampleapps.firebase.dishdetails.manager;


import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import in.nrg.sampleapps.firebase.RestaurantApp;
import in.nrg.sampleapps.firebase.dishdetails.model.DishDetails;

import static in.nrg.sampleapps.firebase.utils.VendorConstants.APP_URL;
import static in.nrg.sampleapps.firebase.utils.VendorConstants.ROOT_NODE;

/**
 * Upload image and dish details with image to firebase
 * <p>
 * https://firebase.google.com/docs/storage/android/start
 *
 * @author Nayanesh Gupte
 */
public class DishUploadManager implements OnProgressListener<UploadTask.TaskSnapshot>,
        OnPausedListener<UploadTask.TaskSnapshot>,
        OnSuccessListener<UploadTask.TaskSnapshot>,
        OnFailureListener {

    private DishDetails dishDetails;

    private UploadTask uploadTask;

    private DishUploadListener dishUploadListener;

    public interface DishUploadListener {

        void onDishUploadCompeted();
    }

    private static DishUploadManager mInstance;

    private DishUploadManager() {
    }

    /**
     * Singleton object
     *
     * @return
     */
    public static DishUploadManager getInstance() {
        if (null == mInstance) {
            synchronized (DishUploadManager.class) {
                if (null == mInstance) {
                    mInstance = new DishUploadManager();
                }
            }
        }
        return mInstance;
    }

    public void setDishUploadListener(DishUploadListener dishUploadListener) {
        this.dishUploadListener = dishUploadListener;
    }

    /**
     * Upload dish to firebase
     *
     * @param restaurantName
     * @param dishName
     * @param dishContent
     * @param timeToCook
     * @param imagePath
     */
    public void uploadDishDetails(String restaurantName, String dishName, String dishContent,
                                  String timeToCook, Uri imagePath) {
        //Creating Dish object
        dishDetails = new DishDetails();

        //Adding values
        dishDetails.setDishName(dishName);
        dishDetails.setDishContents(dishContent);
        dishDetails.setTimeToCook(timeToCook);
        dishDetails.setRestaurantName(restaurantName);

        uploadDish(imagePath);
    }

    private void uploadDishDetailsWithImageUrl(String imageUrl) {
        dishDetails.setImageUrl(imageUrl);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference restaurantRef = FirebaseDatabase
                .getInstance()
                .getReference(ROOT_NODE)
                .child(uid);

        DatabaseReference pushRef = restaurantRef.push();
        String pushId = pushRef.getKey();
        dishDetails.setPushId(pushId);
        pushRef.setValue(dishDetails);

        if (null != dishUploadListener) {
            dishUploadListener.onDishUploadCompeted();
        }
    }

    /**
     * Upload image and generate URL before uploading data so that data can have imageurl.
     *
     * @param file
     */
    private void uploadDish(Uri file) {
        FirebaseStorage storage = FirebaseStorage.getInstance();

        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl(APP_URL);

        // Create the file metadata
        StorageMetadata metadata = new StorageMetadata.Builder()
                .setContentType("image/jpeg")
                .build();

        // Upload file and metadata to the path 'images/mountains.jpg'
        uploadTask = storageRef
                .child("images/" + file.getLastPathSegment())
                .putFile(file, metadata);

        // Listen for state changes, errors, and completion of the upload.
        uploadTask.addOnProgressListener(this)
                .addOnPausedListener(this)
                .addOnFailureListener(this)
                .addOnSuccessListener(this);
    }

    @SuppressWarnings("VisibleForTests")
    @Override
    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
        double progress = (100.0 * (double) taskSnapshot.getBytesTransferred()) / (double) taskSnapshot.getTotalByteCount();
        Toast.makeText(RestaurantApp.getInstance(), "Upload is " + Math.round(progress) + "% done", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        // Handle unsuccessful uploads
        Toast.makeText(RestaurantApp.getInstance(), "Upload failed !", Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("VisibleForTests")
    @Override
    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        // Handle successful uploads on complete
        Uri downloadUrl = taskSnapshot.getMetadata().getDownloadUrl();
        uploadDishDetailsWithImageUrl(downloadUrl.toString());
    }

    @Override
    public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
        Toast.makeText(RestaurantApp.getInstance(), "Upload paused !", Toast.LENGTH_SHORT).show();
    }
}
