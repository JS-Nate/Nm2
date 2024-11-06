package com.example.noteme2;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<ModelNote> noteModels;
    Adapter(Context context, List<ModelNote> noteModels){
        this.inflater = LayoutInflater.from(context);
        this.noteModels = noteModels;
    }

    // Filters for searching
    public void filterList(List<ModelNote> filteredList) {
        noteModels = filteredList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = noteModels.get(position).getNoteTitle();
        String description = noteModels.get(position).getNoteDescription();
        String hexColor = noteModels.get(position).getNoteColor();
        int color = Color.parseColor(hexColor);



        holder.cardView.setCardBackgroundColor(color);
        holder.nTitle.setText(title);
        holder.nDescription.setText(description);

    }
    @Override
    public int getItemCount() {
        return noteModels.size();
    }

    // Displays the notes on the main page from the values saved in the Database
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nTitle, nDescription;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nTitle = itemView.findViewById(R.id.nTitle);
            nDescription = itemView.findViewById(R.id.nDescription);
            cardView = itemView.findViewById(R.id.cardView);


            // Displays the selected note's details
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                    intent.putExtra("ID", noteModels.get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
