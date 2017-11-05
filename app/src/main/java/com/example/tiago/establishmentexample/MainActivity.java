package com.example.tiago.establishmentexample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tiago.establishmentexample.PerfilFragment.PerfilFragment;
import com.example.tiago.establishmentexample.cartItemFragment.CartItemFragment;
import com.example.tiago.establishmentexample.diagoFragment.DialogFragment;
import com.example.tiago.establishmentexample.diagoFragment.DialogPromotionFragment;
import com.example.tiago.establishmentexample.domain.CartItem;
import com.example.tiago.establishmentexample.domain.Order;
import com.example.tiago.establishmentexample.product.Product;
import com.example.tiago.establishmentexample.product.ProductList;
import com.example.tiago.establishmentexample.product.ProductsFragment;
import com.example.tiago.establishmentexample.promotionShop.PromotionShopFragment;
import com.example.tiago.establishmentexample.promotionalListRecycler.PromotioList;
import com.example.tiago.establishmentexample.promotionalListRecycler.Promotion;
import com.example.tiago.establishmentexample.promotionalListRecycler.RecyclerFragment;
import com.example.tiago.establishmentexample.shopFragment.BeginShopFragment;
import com.example.tiago.establishmentexample.shopFragment.ShopFragment;
import com.example.tiago.establishmentexample.userPerfilFragment.UserFragment;
import com.example.tiago.establishmentexample.utils.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        DialogFragment.MyDialogFragmentListener,
        DialogPromotionFragment.MyDialogFragmentListener ,
        ProductsFragment.ProductsFragmentListener,
        RecyclerFragment.PromotionsFragmentListener{

    private String from;
    private ImageView imgBackground;
   
    private TextView mTextMessage;
    private FragmentTransaction fragmentTransaction;
    private List<CartItem> cartItens;
    private ProductList mProducts = new ProductList();
    private PromotioList mPromotions = new PromotioList();



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(from.equals("restaurant")){
                        PerfilFragment fragment = new PerfilFragment();
                        loadFragment(fragment, "products");
                    }else if(from.equals("shop")){
                        BeginShopFragment fragment2 = new BeginShopFragment();
                        loadFragment(fragment2, "promotion_shop");
                    }

                    return true;
                case R.id.navigation_dashboard:
                    if(from.equals("restaurant")){
                        RecyclerFragment fragment1 = RecyclerFragment.novaInstancia(mPromotions);
                        loadFragment(fragment1, "promotional");
                    }else if(from.equals("shop")){
                        PromotionShopFragment fragment2 = new PromotionShopFragment();
                        loadFragment(fragment2, "promotion_shop");
                    }

                    return true;
                case R.id.navigation_notifications:
                    if(from.equals("restaurant")){
                        ProductsFragment fragment2 = ProductsFragment.novaInstancia(mProducts);
                        loadFragment(fragment2, "products");
                    }else if(from.equals("shop")){
                        ShopFragment fragment2 = ShopFragment.novaInstancia(mProducts);
                        loadFragment(fragment2, "products");
                    }
                    return true;
                case R.id.navitation_user:
                    UserFragment fragment3 = new UserFragment();
                    loadFragment(fragment3, "products");
                    return true;
            }
            return false;
        }

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        imgBackground = (ImageView) findViewById(R.id.img_background);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BeginShopFragment fragment2 = new BeginShopFragment();
        loadFragment(fragment2, "promotion_shop");

        from = getIntent().getStringExtra("from");
        if(from.equals("shop")){
            Glide.with(this).load("https://fotos.vivadecora.com.br/decoracao-loja-de-roupas-armario-branco-elizabethmar-20460-proportional-height_cover_medium.jpg").into(imgBackground);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                if(cartItens==null){
                    Toast.makeText(this, "Seu carrinho ainda está vazio", Toast.LENGTH_SHORT).show();
                }else{
                    Order list = new Order();
                    list.itens = cartItens;
                    CartItemFragment fragment = CartItemFragment.novaInstancia(list);
                    loadFragment(fragment, "cart");
                }

                break;


            default:
                break;
        }
        return false;

    }

    private void loadFragment(Fragment fragment, String tag) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment, tag);

            fragmentTransaction.commitAllowingStateLoss();
    }


    @Override
    public void onReturnFromDialog(CartItem cartItem) {
         boolean exist = false;
        if(cartItens==null){
            cartItens = new ArrayList<>();
        }

        for(CartItem item : cartItens){
            if(item.product.objectId.equals(cartItem.product.objectId)){
                item.quantity += cartItem.quantity;
                Toast.makeText(this, "Produto Adicionado com Sucesso", Toast.LENGTH_SHORT).show();
                exist= true;
            }
        }
        if(!exist){
            cartItens.add(cartItem);
            Toast.makeText(this, "Produto Adicionado ao Seu carrinho", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onReturnListProducts(List<Product> products) {
        mProducts.products = products;
    }

    @Override
    public void onReturnListPromotions(List<Promotion> promotions) {
        mPromotions.promotions = promotions;
    }
}
