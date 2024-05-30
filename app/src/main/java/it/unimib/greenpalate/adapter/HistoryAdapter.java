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
import it.unimib.greenpalate.model.History;
import it.unimib.greenpalate.service.ImageLoadTask;
import it.unimib.greenpalate.utils.Utilities;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private final IHistoryRecyclerView recyclerViewInterface;
    Context context;
    List<History> foodList;

    private static final String TAG = "HistoryAdapter";

    public HistoryAdapter(Context context, IHistoryRecyclerView recyclerViewInterface, List<History> foodList){
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_item, parent, false);

        return new HistoryAdapter.ViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {

        History food = foodList.get(position);
        String ecoscore = food.getEcoScore();

        holder.foodNameTextView.setText(food.getName());
        holder.foodBrandTextView.setText(food.getBrandName());
        Utilities.ecoScoreSetter(ecoscore, holder.ecoScoreImageView);
        new ImageLoadTask(food.getImage(), holder.foodImageView).execute();
    }

    @Override
    public int getItemCount() {

        if(foodList == null)
            return 0;
        else {
            Log.d(TAG, "getItemCount: " + foodList.size());
            return foodList.size();
        }    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView foodNameTextView;
        TextView foodBrandTextView;
        ImageView foodImageView;
        ImageView ecoScoreImageView;

        public ViewHolder(@NonNull View itemView, IHistoryRecyclerView recyclerViewInterface) {
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
