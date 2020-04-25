package com.example.groupassignment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity_Learn extends AppCompatActivity {

    private Dialog dialog, checkDialog, settingsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_learn);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_pet, R.id.navigation_learn, R.id.navigation_selflearn, R.id.navigation_shop)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

    // Add button to menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // On button click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            // Initalise dialog and set view
            settingsDialog = new Dialog(this);
            settingsDialog.setContentView(R.layout.settings_dialog);

            // Declare buttons and link XML
            Button info, changeLanguage;
            info = settingsDialog.findViewById(R.id.info);
            changeLanguage = settingsDialog.findViewById(R.id.changeLanguage);

            // Show dialog
            settingsDialog.show();

            // On change language click
            changeLanguage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkDialog = new Dialog(MainActivity_Learn.this);
                    checkDialog.setContentView(R.layout.exit_dialog);
                    settingsDialog.dismiss();

                    // Declare buttons and link XML
                    Button yes = checkDialog.findViewById(R.id.yes);
                    Button no = checkDialog.findViewById(R.id.no);
                    TextView check = checkDialog.findViewById(R.id.exit);
                    check.setText("Change languages?");
                    TextView checkInfo = checkDialog.findViewById(R.id.exitInfo);
                    checkInfo.setText("This is a permanent change so all your progress will be lost.");

                    // Show dialog
                    checkDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    checkDialog.show();

                    // If yes, return to menu
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity_Learn.this, LanguageActivity.class);
                            startActivity(intent);
                            checkDialog.dismiss();
                        }
                    });

                    // If no, dismiss dialog and return to current page
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            checkDialog.dismiss();
                        }
                    });
                }
            });

            // On info click
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Dialog for credits
                    dialog = new Dialog(MainActivity_Learn.this);
                    dialog.setContentView(R.layout.credits);
                    settingsDialog.dismiss();

                    // Link to XML
                    TextView yandex = dialog.findViewById(R.id.yandex);
                    TextView petSource = dialog.findViewById(R.id.petSource);
                    TextView iconsSource = dialog.findViewById(R.id.iconsSource);

                    // Link to credits
                    yandex.setMovementMethod(LinkMovementMethod.getInstance());
                    petSource.setMovementMethod(LinkMovementMethod.getInstance());
                    iconsSource.setMovementMethod(LinkMovementMethod.getInstance());

                    // Decreases the opacity of the background (fragment_pet_test) and displays dialog
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();

                    // Button dismisses dialog when pressed
                    Button closeButton = (Button) dialog.findViewById(R.id.closeButton);
                    closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }
            });
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
