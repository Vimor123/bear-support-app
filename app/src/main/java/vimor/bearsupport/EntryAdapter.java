package vimor.bearsupport;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent entryIntent = new Intent(context, EntryViewActivity.class);
                entryIntent.putExtra("title", entryModel.getTitle());
                String dateString = "";
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
                    dateString = formatter.format(entryModel.getDate());
                }
                entryIntent.putExtra("date", dateString);
                entryIntent.putExtra("entry", entryModel.getEntry());
                entryIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(entryIntent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Context wrapper = new ContextThemeWrapper(context, R.style.PopupMenu);
                PopupMenu menu = new PopupMenu(wrapper, view);
                MenuInflater inflater = menu.getMenuInflater();
                inflater.inflate(R.menu.entry_popup_menu, menu.getMenu());
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getTitle().equals("EDIT")) {
                            Intent entryIntent = new Intent(context, EntryEditActivity.class);
                            entryIntent.putExtra("id", entryModel.getId());
                            entryIntent.putExtra("title", entryModel.getTitle());
                            String dateString = "";
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                dateString = formatter.format(entryModel.getDate());
                            }
                            entryIntent.putExtra("date", dateString);
                            entryIntent.putExtra("entry", entryModel.getEntry());
                            entryIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(entryIntent);
                        }
                        return true;
                    }
                });
                menu.show();
                return true;
            }
        });
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
