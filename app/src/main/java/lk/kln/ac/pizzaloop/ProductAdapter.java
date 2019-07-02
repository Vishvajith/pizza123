package lk.kln.ac.pizzaloop;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/*
 * RecycleView.Adapter
 * RecycleView.ViewHolder
 * */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

private Context mCtx;
private List<Product> productList;

    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
      Product product = productList.get(position);


      holder.textViewTitle.setText(product.getTitle());
      holder.textViewDesc.setText(product.getShortdesc());
      holder.textViewPrice.setText(String.valueOf(product.getRating()));
      holder.textViewRating.setText(String.valueOf(product.getPrice()));



        Glide.with(mCtx)
                .load(product.getImage())
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewTitle, textViewDesc, textViewRating, textViewPrice;



       public ProductViewHolder(@NonNull View itemView) {
           super(itemView);

           imageView = itemView.findViewById(R.id.imageView);
           textViewTitle = itemView.findViewById(R.id.textViewTitle);
           textViewDesc = itemView.findViewById(R.id.textViewShortDesc);
           textViewRating = itemView.findViewById(R.id.textViewRating);
           textViewPrice = itemView.findViewById(R.id.textViewPrice);

       }
   }


}
