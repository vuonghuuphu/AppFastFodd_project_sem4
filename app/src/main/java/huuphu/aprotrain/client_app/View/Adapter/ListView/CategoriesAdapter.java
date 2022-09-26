package huuphu.aprotrain.client_app.View.Adapter.ListView;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import huuphu.aprotrain.client_app.Model.Category;
import huuphu.aprotrain.client_app.R;
import huuphu.aprotrain.client_app.View.Fragment.fragment_home;
import huuphu.aprotrain.client_app.View.Onclick.CategoriesOnclick;

public class CategoriesAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<Category> categoryList;
    CategoriesOnclick categoriesOnclick;
    List<String> stringList = new ArrayList<>();

    public CategoriesAdapter(Activity activity, List<Category> categoryList) {
        this.activity = activity;
        this.categoryList = categoryList;
    }

    public void setCategoriesOnclick(CategoriesOnclick categoriesOnclick){
        this.categoriesOnclick = categoriesOnclick;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = activity.getLayoutInflater().inflate(R.layout.item_cate_list,parent,false);
        CategoryHolder hoder = new CategoryHolder(itemview);
        return hoder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryHolder categoryHolder = (CategoryHolder) holder;
         Category model = categoryList.get(position);
        categoryHolder.tv_Name.setText(model.getName());

//        Glide
//                .with(activity)
//                .load("https://res.cloudinary.com/huytqth2008010/image/upload/v1663272369/qjsdd3us4dxw9dhz9jj9.png")
//                .centerCrop()
//                .into(categoryHolder.iv_Image);

        Picasso.get()
                .load(model.getThumbnail())
                .resize(100, 100)
                .centerCrop()
                .into(categoryHolder.iv_Image);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView tv_Name;
        ImageView iv_Image;

        public CategoryHolder (@NonNull View itemview){
            super(itemview);
            tv_Name = itemview.findViewById(R.id.tv_name_list_cate);
            iv_Image = itemview.findViewById(R.id.iv_item_list_cate);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    categoriesOnclick.onClickitem(getAdapterPosition());
                }
            });
        }


        @Override
        public void onClick(View view) {

        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
}