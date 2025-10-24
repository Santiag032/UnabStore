package me.santiagobrito.unabstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import me.santiagobrito.unabstore.ui.theme.UnabStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var starDestination = "login"

            val auth = Firebase.auth
            val currentUser = auth.currentUser

            if (currentUser != null){
                starDestination = "home"
            }else{
                starDestination = "login"
            }
            NavHost(
                navController = navController,
                startDestination = starDestination,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = "login") {
                    LoginScreen(onClickRegistrer = {
                        navController.navigate("register")
                    }, onSuccessfulLogin = {
                        navController.navigate("home"){
                            popUpTo("login"){inclusive = true}
                        }
                    })
                }
                composable(route = "register") {
                    RegisterScreen(onClickBack = {
                        navController.popBackStack()
                    }, onSuccessfulRegiter = {
                        navController.navigate("home"){
                            popUpTo(0)
                        }
                    })
                }
                composable(route = "home") {
                    HomeScreen(onClickLogout = {
                        navController.navigate("login"){
                            popUpTo(0)
                        }
                    })
                }
            }
        }
    }
}
