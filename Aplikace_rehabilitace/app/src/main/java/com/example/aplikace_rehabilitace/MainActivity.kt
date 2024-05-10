package com.example.aplikace_rehabilitace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewpager2.widget.ViewPager2
import com.example.aplikace_rehabilitace.databinding.ActivityMainBinding
import com.example.aplikace_rehabilitace.databinding.FragmentWelcomeBinding
import com.example.aplikace_rehabilitace.ui.theme.Aplikace_rehabilitaceTheme
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    val usedAccountId = -1L // TODO: změnit



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu -> Toast.makeText(this, "Klikli jste na Menu",Toast.LENGTH_SHORT).show()
            R.id.home -> Toast.makeText(this, "Klikli jste na Home Screen",Toast.LENGTH_SHORT).show()
            R.id.user_account -> Toast.makeText(this, "Klikli jste na User Account",Toast.LENGTH_SHORT).show()
        }
        return true
    }
    companion object{
        fun setCurrentPatientId(id: Long) {
            MainApplicationClass.setCurrentPatientId(id)
        }

        fun getCurrentPatientId(): Long {
            return MainApplicationClass.getCurrentPatientId()
        }
    }

}

