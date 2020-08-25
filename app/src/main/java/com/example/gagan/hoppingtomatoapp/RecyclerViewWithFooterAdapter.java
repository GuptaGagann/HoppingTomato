package com.example.gagan.hoppingtomatoapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewWithFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int FOOTER_VIEW = 1;

    private ArrayList<String> data; // Take any list that matches your requirement.
    private Context context;

    // Define a constructor
    public RecyclerViewWithFooterAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
    }

    // Define a ViewHolder for Footer view
    public class FooterViewHolder extends ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);

            final EditText menuItemName, menuItemPrice;
            TextView add_new;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Do whatever you want on clicking the item
                }
            });

            menuItemName=itemView.findViewById(R.id.menuListItem);
            menuItemPrice=itemView.findViewById(R.id.price);
            add_new=itemView.findViewById(R.id.add);
            add_new.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(v.getContext(),menuItemName.getText()+" :: "+menuItemPrice.getText(),Toast.LENGTH_LONG).show();
                    data.add(menuItemName.getText()+" :: "+menuItemPrice.getText());
                    Toast.makeText(v.getContext(),data.get(getAdapterPosition())+" added!",Toast.LENGTH_LONG).show();
                    menuItemName.setText(null);
                    menuItemPrice.setText(null);
                    notifyDataSetChanged();

                }
            });
        }
    }

    // Now define the ViewHolder for Normal list item
    public class NormalViewHolder extends ViewHolder {
        public NormalViewHolder(View itemView) {
            super(itemView);
            TextView menuListItem;
            TextView delete;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Do whatever you want on clicking the normal items
                }
            });
            delete = itemView.findViewById(R.id.delete);
            menuListItem =itemView.findViewById(R.id.menuListItem);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.remove(getLayoutPosition());
                    getItemCount();
                    notifyDataSetChanged();
                }
            });

        }
    }



    // And now in onCreateViewHolder you have to pass the correct view
    // while populating the list item.

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;

        if (viewType == FOOTER_VIEW) {
            v = LayoutInflater.from(context).inflate(R.layout.add_menu_list_footer, parent, false);
            FooterViewHolder vh = new FooterViewHolder(v);
            return vh;
        }

        v = LayoutInflater.from(context).inflate(R.layout.add_menu_list, parent, false);

        NormalViewHolder vh = new NormalViewHolder(v);

        return vh;
    }

    // Now bind the ViewHolder in onBindViewHolder
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        try {
            if (holder instanceof NormalViewHolder) {
                NormalViewHolder vh = (NormalViewHolder) holder;
                vh.bindView(position);
            } else if (holder instanceof FooterViewHolder) {
                FooterViewHolder vh = (FooterViewHolder) holder;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Now the critical part. You have return the exact item count of your list
    // I've only one footer. So I returned data.size() + 1
    // If you've multiple headers and footers, you've to return total count
    // like, headers.size() + data.size() + footers.size()

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }

        if (data.size() == 0) {
            //Return 1 here to show nothing
            return 1;
        }

        // Add extra view to show the footer view
        return data.size() + 1;
    }

    // Now define getItemViewType of your own.

    @Override
    public int getItemViewType(int position) {
        if (position == data.size()) {
            // This is where we'll add footer.
            return FOOTER_VIEW;
        }

        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // So you're done with adding a footer and its action on onClick.
    // Now set the default ViewHolder for NormalViewHolder

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Define elements of a row here
        public ViewHolder(View itemView) {
            super(itemView);
            // Find view by ID and initialize here
            TextView menuListItem;
            TextView delete;
            menuListItem = itemView.findViewById(R.id.menuListItem);
            delete = itemView.findViewById(R.id.delete);
        }

        public void bindView(int position) {
            // bindView() method to implement actions
            TextView menuListItem;
            TextView delete;
            menuListItem = itemView.findViewById(R.id.menuListItem);
            menuListItem.setText(data.get(position));
        }
    }
}
