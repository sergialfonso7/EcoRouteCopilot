package com.ecoroute.ecoroute

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ecoroute.ecoroute.ui.theme.*

class Register : ComponentActivity() {
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
fun RegisterView(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier){
        Column() {
            Row() {
                CreateAccount()
            }
            Row() {
                UsernameText()
            }
            Row {
                UsernameInputText()
            }
            Row{
                EmailText()
            }
            Row{
                EmailInputText()
            }
            Row{
                PasswordText()
            }
            Row {
                PasswordInputText()
            }
            Row{
                RegisterVehicles()
            }
            Row{
                CompulsoryDataText()
            }
            Row{
                AcceptTerms()
            }
            Row{
                NextButton()
            }
            Row{
                HasAccount(navController)
            }
        }
    }
}


var textNewUsername = String()
var textNewMail = String()
var textNewPassword = String()

@Composable
private fun CreateAccount(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 3.dp, top = 10.dp),
    ) {
        Text(
            text = stringResource(
                R.string.register_message
            ),
            style = TypeFont.body1,
            modifier = Modifier.padding(start = 25.dp, top = 20.dp),
            fontSize = 30.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun UsernameText(
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
    )  {
        Text(
            text = stringResource(
                R.string.username
            ) + " *",
            style = TypeFont.body1,
            modifier = Modifier.padding(start = 25.dp),
            fontSize = 15.sp,
            color = GreenECO,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
private fun EmailText(
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
    )  {
        Text(
            text = stringResource(
                R.string.mail
            ) + " *",
            style = TypeFont.body1,
            modifier = Modifier.padding(start = 25.dp),
            fontSize = 15.sp,
            color = GreenECO,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
private fun PasswordText(
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
    )  {
        Text(
            text = stringResource(
                R.string.password
            ) + " *",
            style = TypeFont.body1,
            modifier = Modifier.padding(start = 25.dp),
            fontSize = 15.sp,
            color = GreenECO,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
private fun CompulsoryDataText(
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    )  {
        Text(
            text = stringResource(
                R.string.compulsory_text
            ) ,
            style = TypeFont.body1,
            modifier = Modifier.padding(start = 25.dp),
            fontSize = 10.sp,
            color = Color.Red,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
private fun UsernameInputText() {
    val textUsername = remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier
        .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp)
        .border(width = 0.dp, shape = RoundedCornerShape(0.dp), color = Color.Transparent)
    ) {
        TextField(
            value = textUsername.value,
            onValueChange = { newValue ->
                textUsername.value = newValue
                textNewUsername = textUsername.value.text
            },
            placeholder = { Text (stringResource(
                R.string.username
            ))},
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
            ),

        )
    }
}

@Composable
private fun EmailInputText() {
    val textEmail = remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier
        .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp)
        .border(width = 0.dp, shape = RoundedCornerShape(0.dp), color = Color.Transparent)
    ) {
        TextField(
            value = textEmail.value,
            onValueChange = { newValue ->
                textEmail.value = newValue
                textNewMail = textEmail.value.text
            },
            placeholder = { Text (stringResource(
                R.string.mail
            ))},
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
            ),

            )
    }
}

@Composable
private fun PasswordInputText() {
    val textPassword = remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier
        .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp)
        .border(width = 0.dp, shape = RoundedCornerShape(0.dp), color = Color.Transparent)
    ) {
        TextField(
            value = textPassword.value,
            onValueChange = { newValue ->
                textPassword.value = newValue
                textNewPassword = textPassword.value.text
            },
            placeholder = { Text (stringResource(
                R.string.password
            ))},
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
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),

            )
    }
}

@Composable
fun RegisterVehicles(

) {

    Column(
        modifier = Modifier
            .padding(start = 17.dp, end = 75.dp, top = 10.dp),
    ) {
        TextButton(
            border = BorderStroke(0.dp, Color.Transparent),
            onClick = {  },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            modifier = Modifier

        ) {
            Row(
                modifier = Modifier
            ) {
                Text(
                    stringResource(
                        R.string.register_vehicle
                    ),
                    textAlign = TextAlign.Left,
                    color = GreenECO,
                    style = TypeFont.body1,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(Modifier.width(15.dp))
                Image(

                    painter = painterResource(R.drawable.vehicleicon),
                    contentDescription = "Vehicle",
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    }
}

@Composable
private fun AcceptTerms() {
    // Initialize the checkbox state
    val checkedState = remember { mutableStateOf(false) }

        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
            colors = CheckboxDefaults.colors(GreenECO), // Optional
            modifier = Modifier
                .size(2.dp) // Optional
                .padding(start = 33.dp, top = 20.dp)
        )

        Text(
            text = stringResource(
                R.string.accept_privacity
            ),
            style = TypeFont.body1,
            modifier = Modifier.padding(start = 55.dp, top = 14.dp),
            fontSize = 12.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
}

@Composable
fun NextButton(
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 35.dp, end = 35.dp, bottom = 5.dp, top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(backgroundColor = GreenECO,),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()

        ) {
            Text(
                stringResource(
                    R.string.next
                ),
                textAlign = TextAlign.Center,
                color = Color.White,
                style = TypeFont.body1,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}


@Composable
private fun HasAccount( navController: NavController,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    )  {
       Row() {
           Text(
               text = stringResource(
                   R.string.existsaccount
               ),
               style = TypeFont.body1,
               modifier = Modifier
                 .padding(top = 15.dp),
               fontSize = 15.sp,
               color = Color.Gray,
               fontWeight = FontWeight.Bold
           )
           Spacer(modifier = Modifier.width(5.dp))

           TextButton(onClick = { navController.navigate("login") }) {
              Text( text = stringResource(
                   R.string.login_message
               ),
               style = TypeFont.body1,
               modifier = Modifier,
               fontSize = 15.sp,
               color = GreenECO,
               fontWeight = FontWeight.Bold
              )
           }
       }
    }

}

fun onClick() {
    TODO("Not yet implemented")
}

/*
@Preview(showBackground = true)
@Composable
fun PreviewGreeting() {
    RegisterView()
}

 */
