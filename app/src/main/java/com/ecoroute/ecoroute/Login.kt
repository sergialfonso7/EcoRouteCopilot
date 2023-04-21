package com.ecoroute.ecoroute

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

import androidx.navigation.compose.rememberNavController
import com.ecoroute.ecoroute.ui.theme.*

var textFieldEmail = String()
var textFieldPassword = String()


class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcoRouteTheme {
                EcoRouteTheme {
                    Column {
                    }
                }
            }
        }
    }
}

@Composable
fun LoginView(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Row() {
       Column() {
           Row() {
               MyImageComponent()
           }
           Row() {
               Welcome()
           }
           Row {
               EmailInputTextField()
           }
           Row{
               PasswordText()
           }
           Row {
               PasswordInputTextField()
           }
           Row{
               SignInButton { field1, field2 -> onButtonClicked(navController, field1, field2) }
           }
           Row{
               RegisterButton(navController)
           }
           Row{
               GoogleButton(onClick = ::onButtonClickedGoogle)
           }
       }
    }
}




private fun onButtonClickedGoogle() {
    // Do something with the two field values
}

private fun onButtonClicked(navController: NavController, field1: String, field2: String) {
    println(field1)
    println(field2)
    navController.navigate("Routes")
}

@Composable
private fun MyImageComponent(
    modifier: Modifier = Modifier) {
    Column(
       modifier = modifier
           .fillMaxWidth()
           .padding(bottom = 8.dp, top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
       val painter: Painter = painterResource(id = R.drawable.logo_ecoroute)
       Image(painter = painter, contentDescription = "My Image")
   }
}

@Composable
private fun Welcome(
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    )  {
        Text(
            text = stringResource(
                R.string.login_message
            ),
            style = TypeFont.body1,
            modifier = Modifier.padding(start = 25.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(
                R.string.welcome
            ),
            style = TypeFont.body1,
            modifier = Modifier.padding(start = 25.dp),
            fontSize = 15.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Bold

        )

        Text(
            text = stringResource(
                R.string.mail
            ),
            style = TypeFont.body1,
            modifier = Modifier.padding(start = 25.dp, top = 20.dp),
            fontSize = 15.sp,
            color = GreenECO,
            fontWeight = FontWeight.Bold
        )

    }

}

@Composable
private fun PasswordText(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
    ) {
        Text(
            text = stringResource(
                R.string.password
            ),
            style = TypeFont.body1,
            modifier = Modifier.padding(start = 25.dp, top = 20.dp),
            fontSize = 15.sp,
            color = GreenECO,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun EmailInputTextField() {
    val textEmail = remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier
        .padding(start = 25.dp, end = 25.dp)
        .border(width = 4.dp, shape = RoundedCornerShape(0.dp), color = Color.Transparent)
    ) {
        TextField(
            value = textEmail.value,
            onValueChange = { newValue ->
                textEmail.value = newValue
                textFieldEmail = textEmail.value.text
            },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black),
            singleLine = true,

            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = GreenECO,
                unfocusedIndicatorColor = Color.Gray
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )

        )
    }
}


@Composable
private fun PasswordInputTextField(

) {
    val textPassword = remember { mutableStateOf(TextFieldValue()) }
    var passwordVisibility by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .padding(start = 25.dp, end = 25.dp)
        .border(width = 4.dp, shape = RoundedCornerShape(0.dp), color = Color.Transparent)
    ) {
        TextField(
            value = textPassword.value,
            onValueChange = { newValue ->
                textPassword.value = newValue
                textFieldPassword = textPassword.value.text
            },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black),
            singleLine = true,

            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = GreenECO,
                unfocusedIndicatorColor = Color.Gray
            ),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),

        )
    }
}

@Composable
fun SignInButton(
    onClick: (String, String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp, bottom = 5.dp, top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { onClick(textFieldEmail, textFieldPassword) },
            colors = ButtonDefaults.buttonColors(backgroundColor = GreenECO,),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()

        ) {
            Text(
                stringResource(
                R.string.login_message
                ),
                textAlign = TextAlign.Center,
                color = Color.White,
                style = TypeFont.body1,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}


@Composable
fun RegisterButton(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {navController.navigate("register") },
            colors = ButtonDefaults.buttonColors(backgroundColor = BlueECO,),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()

        ) {
            Text(
                stringResource(
                    R.string.register_message
                ),
                textAlign = TextAlign.Center,
                color = Color.White,
                style = TypeFont.body1,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}


@Composable
fun GoogleButton(
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Button(
            onClick = { onButtonClickedGoogle() },
            colors = ButtonDefaults.buttonColors(backgroundColor = OrangeECO,),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()

        ) {
            Row(
                modifier = Modifier
            ) {
                Image(

                    painter = painterResource(R.drawable.googlelogo),
                    contentDescription = "Google Icon",
                    modifier = Modifier.size(24.dp)
                )

                Spacer(Modifier.width(30.dp))

                Text(
                    stringResource(
                        R.string.google_login
                    ),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    style = TypeFont.body1,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}



