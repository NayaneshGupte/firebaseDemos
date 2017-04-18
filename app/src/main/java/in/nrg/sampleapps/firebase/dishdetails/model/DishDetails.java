package in.nrg.sampleapps.firebase.dishdetails.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing POJO for dish
 *
 * @author Nayanesh Gupte
 */
public class DishDetails implements Parcelable {

    private String dish_name;
    private String dish_contents;
    private String time_to_cook;
    private String restaurant_name;
    private String pushId;
    private String imageUrl;
    private String season;


    public DishDetails() {
    }


    protected DishDetails(Parcel in) {
        dish_name = in.readString();
        dish_contents = in.readString();
        time_to_cook = in.readString();
        restaurant_name = in.readString();
        pushId = in.readString();
        imageUrl = in.readString();
        season = in.readString();
    }

    public static final Creator<DishDetails> CREATOR = new Creator<DishDetails>() {
        @Override
        public DishDetails createFromParcel(Parcel in) {
            return new DishDetails(in);
        }

        @Override
        public DishDetails[] newArray(int size) {
            return new DishDetails[size];
        }
    };

    public String getDishName() {
        return dish_name;
    }

    public void setDishName(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getDishContents() {
        return dish_contents;
    }

    public void setDishContents(String dish_contents) {
        this.dish_contents = dish_contents;
    }

    public String getTimeToCook() {
        return time_to_cook;
    }

    public void setTimeToCook(String time_to_cook) {
        this.time_to_cook = time_to_cook;
    }

    public String getRestaurantName() {
        return restaurant_name;
    }

    public void setRestaurantName(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("dish_name", dish_name);
        result.put("dish_contents", dish_contents);
        result.put("time_to_cook", time_to_cook);
        result.put("restaurant_name", restaurant_name);
        return result;
    }

    @Override
    public String toString() {
        return "DishDetails{" +
                "dish_name='" + dish_name + '\'' +
                ", dish_contents='" + dish_contents + '\'' +
                ", time_to_cook='" + time_to_cook + '\'' +
                ", restaurant_name='" + restaurant_name + '\'' +
                ", pushId='" + pushId + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dish_name);
        dest.writeString(dish_contents);
        dest.writeString(time_to_cook);
        dest.writeString(restaurant_name);
        dest.writeString(pushId);
        dest.writeString(imageUrl);
        dest.writeString(season);
    }
}

