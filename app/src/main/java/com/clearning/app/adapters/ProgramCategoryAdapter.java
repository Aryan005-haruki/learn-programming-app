package com.clearning.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.clearning.app.R;
import com.clearning.app.models.ProgramCategory;
import java.util.List;

public class ProgramCategoryAdapter extends RecyclerView.Adapter<ProgramCategoryAdapter.CategoryViewHolder> {
    private Context context;
    private List<ProgramCategory> categories;
    private OnCategoryClickListener listener;

    public interface OnCategoryClickListener {
        void onCategoryClick(ProgramCategory category);
    }

    public ProgramCategoryAdapter(Context context, List<ProgramCategory> categories, OnCategoryClickListener listener) {
        this.context = context;
        this.categories = categories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_program_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        ProgramCategory category = categories.get(position);
        
        holder.tvEmoji.setText(category.getEmoji());
        holder.tvCategoryName.setText(category.getName());
        holder.tvDescription.setText(category.getDescription());
        holder.tvProgramCount.setText(String.format("%d Programs", category.getProgramCount()));
        
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCategoryClick(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvEmoji, tvCategoryName, tvDescription, tvProgramCount;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            tvEmoji = itemView.findViewById(R.id.tvEmoji);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvProgramCount = itemView.findViewById(R.id.tvProgramCount);
        }
    }
}
