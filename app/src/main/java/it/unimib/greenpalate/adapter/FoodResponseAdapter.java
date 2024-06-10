package it.unimib.greenpalate.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.unimib.greenpalate.R;
import it.unimib.greenpalate.model.Food;
import it.unimib.greenpalate.service.ImageLoadTask;
import it.unimib.greenpalate.utils.Utilities;

public class FoodResponseAdapter extends RecyclerView.Adapter<FoodResponseAdapter.ViewHolder> {

    private final IFoodResponseRecyclerView recyclerViewInterface;
    Context context;
    List<Food> foodResponseList;

    private static final String TAG = FoodResponseAdapter.class.getSimpleName();

    public FoodResponseAdapter(Context context, List<Food> foodResponseList,  IFoodResponseRecyclerView recyclerViewInterface){
        this.context = context;
        this.foodResponseList = foodResponseList;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public FoodResponseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_item, parent, false);

        return new FoodResponseAdapter.ViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodResponseAdapter.ViewHolder holder, int position) {
        Food food = foodResponseList.get(position);
        String ecoscore = food.getEcoScoreGrade();

        holder.foodNameTextView.setText(food.getProductName());
        holder.foodBrandTextView.setText(food.getBrand());
        Utilities.ecoScoreSetter(ecoscore, holder.ecoScoreImageView);
        new ImageLoadTask(food.getImage(), holder.foodImageView).execute();
    }

    @Override
    public int getItemCount() {

        if(foodResponseList == null)
            return 0;
        else {
            return foodResponseList.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView foodNameTextView;
        TextView foodBrandTextView;
        ImageView foodImageView;
        ImageView ecoScoreImageView;

        public ViewHolder(@NonNull View itemView, IFoodResponseRecyclerView recyclerViewInterface) {
            super(itemView);

            foodImageView = itemView.findViewById(R.id.itemImageView);
            foodNameTextView = itemView.findViewById(R.id.itemTitleTextView);
            foodBrandTextView = itemView.findViewById(R.id.itemBrandNameTextview);
            ecoScoreImageView = itemView.findViewById(R.id.itemEcoScoreImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}