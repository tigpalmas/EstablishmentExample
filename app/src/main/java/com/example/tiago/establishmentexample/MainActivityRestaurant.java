package com.example.tiago.establishmentexample;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tiago.establishmentexample.PerfilFragment.PerfilFragment;
import com.example.tiago.establishmentexample.product.ProductList;
import com.example.tiago.establishmentexample.product.ProductsFragment;
import com.example.tiago.establishmentexample.promotionalListRecycler.PromotioList;
import com.example.tiago.establishmentexample.promotionalListRecycler.RecyclerFragment;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivityRestaurant extends AppCompatActivity {
    private Drawer result;
    private Toolbar toolbar;
    private FragmentTransaction fragmentTransaction;
    private ProductList mProducts = new ProductList();
    private PromotioList mPromotions = new PromotioList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_restaurant);
        toolbar = (Toolbar) findViewById(R.id.toolbarRestaurant);
        setSupportActionBar(toolbar);
        loadDrawer();
        ProductsFragment fragment1 = ProductsFragment.novaInstancia(mProducts);
        loadFragment(fragment1, "promotional");
    }


    public void loadDrawer() {
        List<Integer> headers = new ArrayList<>();
        Random rand = new Random();
        headers.add(R.drawable.balada);
        headers.add(R.drawable.restaurant);
        int random = headers.get(rand.nextInt(headers.size()));

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(random)
                .withSelectionListEnabledForSingleProfile(false)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {

                        return false;
                    }
                })
                .addProfiles(
                ).build();

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1000).withName("Home").withIcon(R.drawable.ic_home_black_24dp);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2000).withName("Promoções").withIcon(R.drawable.ic_sale_grey600_36dp);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3000).withName("Cardápio").withIcon(R.drawable.ic_food);;
        DividerDrawerItem dividerDrawerItem = new DividerDrawerItem();
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4000).withName("Seu Perfil/Pedidos").withIcon(R.drawable.ic_acount).withSubItems(
                new SecondaryDrawerItem().withName("Seus Pedidos").withLevel(2).withIdentifier(4001),
                new SecondaryDrawerItem().withName("Seu Perfil").withLevel(2).withIdentifier(4002),
                new SecondaryDrawerItem().withName("Pagamento").withLevel(2).withIdentifier(4003),
                new SecondaryDrawerItem().withName("Endereço de entrega").withLevel(2).withIdentifier(4004));
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5000).withName("Sobre").withIcon(R.drawable.ic_about);


//create the drawer and remember the `Drawer` result object
        result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems( item1, item2, item3, dividerDrawerItem,new SecondaryDrawerItem().withName("Geral").withEnabled(false), item4, item5 )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch ((int) drawerItem.getIdentifier()) {
                            case 1000:
                                PerfilFragment fragment = new PerfilFragment();
                                loadFragment(fragment, "perfil");
                                return false;

                            case 2000:
                                RecyclerFragment fragment2 = RecyclerFragment.novaInstancia(mPromotions);
                                loadFragment(fragment2, "promotional");
                                return false;

                            case 3000:
                                ProductsFragment fragment1 = ProductsFragment.novaInstancia(mProducts);
                                loadFragment(fragment1, "promotional");
                                return false;

                        }
                        return false;
                    }
                }).build();


        ProfileDrawerItem profile = new ProfileDrawerItem();


        profile.withName("não logado").withEmail("Clique na imagem para efetuar login");
        profile.withIcon(R.drawable.ic_action_user);


        headerResult.addProfile(profile, 0);

    }

    private void loadFragment(Fragment fragment, String tag) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, tag);

        fragmentTransaction.commitAllowingStateLoss();
    }

}
