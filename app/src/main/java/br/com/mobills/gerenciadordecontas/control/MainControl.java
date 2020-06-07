package br.com.mobills.gerenciadordecontas.control;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.Arrays;
import java.util.List;

import br.com.mobills.gerenciadordecontas.R;
import br.com.mobills.gerenciadordecontas.view.MainActivity;

public class MainControl {
    private Activity activity;

    private BottomNavigationView bottomNavigationView;
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;
    private Button btn_sign_out;


    private static final int MY_REQUEST_CODE = 1223;
    private List<AuthUI.IdpConfig> providers;


    public MainControl(Activity activity) {
        this.activity = activity;

        initComponents();
    }

    private void initComponents() {
        btn_sign_out = activity.findViewById(R.id.btn_sign_out);

        bottomNavigationView = activity.findViewById(R.id.nav_view);
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_despesa,
                R.id.navigation_receita).build();

        navController = Navigation.findNavController(activity, R.id.nav_host_fragment);

        setupActionBarControlNavigation();

        clickBtSignOut();
        initProviders();



    }

    private void setupActionBarControlNavigation() {
        NavigationUI.setupActionBarWithNavController((AppCompatActivity) activity, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    private void clickBtSignOut() {
        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance().signOut(activity
                ).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        btn_sign_out.setEnabled(false);
                        showSignInOptions();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initProviders() {
        providers = Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()


        );

        showSignInOptions();
    }

    private void showSignInOptions() {
        activity.startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().
                setAvailableProviders(providers).setTheme(R.style.MyTheme).build(), MY_REQUEST_CODE);

    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == MY_REQUEST_CODE) {

            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == activity.RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                Toast.makeText(activity, "" + user.getEmail(), Toast.LENGTH_SHORT).show();

                btn_sign_out.setEnabled(true);

            } else {
                Toast.makeText(activity, "" + response.getError().getMessage(), Toast.LENGTH_SHORT).show();

            }

        }


    }

}
