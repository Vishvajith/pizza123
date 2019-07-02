package lk.kln.ac.pizzaloop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String PRODUCT_URL="http://192.168.43.193:8080/demo/all";

    RecyclerView recyclerView;
    ProductAdapter adapter;

    List<Product>productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadProduct();


    }
    private void loadProduct(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, PRODUCT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray product =new JSONArray(response);

                            for (int i=0; i<product.length(); i++){
                                JSONObject prductObject = product.getJSONObject(i);

                                int id = prductObject.getInt("pizzaId");
                                String name = prductObject.getString("name");
                                String shortdesc = prductObject.getString("description");
                                double price = prductObject.getDouble("price");
                                double pizza_id =0;
                                String image_url =prductObject.getString("imageUrl");

                                Product productl =new Product(id, name, shortdesc, price, pizza_id, image_url);
                                productList.add(productl);
                            }

                            adapter = new ProductAdapter(MainActivity.this, productList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String,String>getParams() throws AuthFailureError{
                Map<String,String>params = new HashMap<>();
                return  params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
