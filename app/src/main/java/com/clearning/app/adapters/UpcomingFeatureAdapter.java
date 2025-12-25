package com.clearning.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.clearning.app.R;
import com.clearning.app.models.UpcomingFeature;
import java.util.List;

public class UpcomingFeatureAdapter extends RecyclerView.Adapter<UpcomingFeatureAdapter.FeatureViewHolder> {
    private Context context;
    private List<UpcomingFeature> features;

    public UpcomingFeatureAdapter(Context context, List<UpcomingFeature> features) {
        this.context = context;
        this.features = features;
    }

    @NonNull
    @Override
    public FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_upcoming_feature, parent, false);
        return new FeatureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureViewHolder holder, int position) {
        UpcomingFeature feature = features.get(position);
        
        holder.tvEmoji.setText(feature.getEmoji());
        holder.tvFeatureName.setText(feature.getName());
        holder.tvFeatureDescription.setText(feature.getDescription());
        holder.tvStatus.setText(feature.getStatus());
    }

    @Override
    public int getItemCount() {
        return features.size();
    }

    static class FeatureViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmoji, tvFeatureName, tvFeatureDescription, tvStatus;

        public FeatureViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmoji = itemView.findViewById(R.id.tvEmoji);
            tvFeatureName = itemView.findViewById(R.id.tvFeatureName);
            tvFeatureDescription = itemView.findViewById(R.id.tvFeatureDescription);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
