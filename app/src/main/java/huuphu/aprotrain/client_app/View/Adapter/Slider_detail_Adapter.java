package huuphu.aprotrain.client_app.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import huuphu.aprotrain.client_app.R;

public class Slider_detail_Adapter extends SliderViewAdapter<Slider_detail_Adapter.Holder>{

    List<String> images;

    public Slider_detail_Adapter(List<String> images){

        this.images = images;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.silder_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        Picasso.get()
                .load(images.get(position))
                .resize(400, 400)
                .centerCrop()
                .into(viewHolder.imageView);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    public class Holder extends  ViewHolder{

        ImageView imageView;

        public Holder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.image_item);
        }
    }

}
