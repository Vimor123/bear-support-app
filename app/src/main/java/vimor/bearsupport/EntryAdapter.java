package vimor.bearsupport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryViewHolder> {

    Context context;
    List<EntryModel> entries;

    public EntryAdapter(Context context, List<EntryModel> entries) {
        this.context = context;
        this.entries = entries;
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EntryViewHolder(LayoutInflater.from(context).inflate(R.layout.entry_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EntryViewHolder holder, int position) {
        EntryModel entryModel = entries.get(position);
        holder.titleOutput.setText(entryModel.getTitle());

        LocalDate date = entryModel.getDate();
        String dateString = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
            dateString = formatter.format(date);
        }
        holder.dateOutput.setText(dateString);
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public class EntryViewHolder extends RecyclerView.ViewHolder {

        TextView titleOutput;
        TextView dateOutput;

        public EntryViewHolder(@NonNull View itemView) {
            super(itemView);
            titleOutput = itemView.findViewById(R.id.entryItemTitle);
            dateOutput = itemView.findViewById(R.id.entryItemDate);
        }
    }
}
