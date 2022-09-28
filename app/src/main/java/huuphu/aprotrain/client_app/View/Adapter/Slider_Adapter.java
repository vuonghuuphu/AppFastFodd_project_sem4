package huuphu.aprotrain.client_app.View.Adapter;

import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;

        import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import huuphu.aprotrain.client_app.Model.Slide;
import huuphu.aprotrain.client_app.R;

public class Slider_Adapter extends SliderViewAdapter<Slider_Adapter.Holder>{

    List<Slide> images;

    public Slider_Adapter(List<Slide> images) {
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
                .load(images.get(position).getThumbnail())
                .resize(800, 440)
                .centerCrop()
                .into(viewHolder.imageView);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    public class Holder extends  SliderViewAdapter.ViewHolder{

        ImageView imageView;

        public Holder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.image_item);
        }
    }

}
