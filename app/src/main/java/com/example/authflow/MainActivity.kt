package com.example.authflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authflow.ui.theme.AuthFlowAppTheme
import com.example.authflow.ui.theme.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuthFlowAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = White
                ) {
                    AuthFlowNavHost()
                }
            }
        }
    }
}

@Composable
fun AuthFlowNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "signup") {
        composable("signup") {
            SignUpScreen(
                onSignUpClick = { navController.navigate("verification_code_signup") },
                onForgotPasswordClick = { navController.navigate("password_recovery") }
            )
        }
        composable("verification_code_signup") {
            VerificationCodeScreen(
                title = "Check your email",
                subtitle = "We've sent the code to your email",
                onVerifyClick = { navController.navigate("signup") { popUpTo("signup") { inclusive = true } } }
            )
        }
        composable("password_recovery") {
            PasswordRecoveryScreen(
                onSendClick = { navController.navigate("password_verification_code") }
            )
        }
        composable("password_verification_code") {
            PasswordVerificationCodeScreen(
                onVerifyClick = { navController.navigate("new_password") }
            )
        }
        composable("new_password") {
            NewPasswordScreen(
                onDoneClick = { navController.navigate("signup") { popUpTo("signup") { inclusive = true } } }
            )
        }
    }
}

fun PasswordVerificationCodeScreen(onVerifyClick: () -> Unit) {

}

fun VerificationCodeScreen(title: String, subtitle: String, onVerifyClick: () -> Unit) {

}
