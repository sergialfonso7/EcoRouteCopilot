package com.ecoroute.ecoroute

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecoroute.ecoroute.ui.theme.EcoRouteTheme
import com.ecoroute.ecoroute.ui.theme.GreenECO
import com.ecoroute.ecoroute.ui.theme.RedECO
import com.ecoroute.ecoroute.ui.theme.TypeFont


@Composable
fun ProfileView(//navController: NavController,
            modifier: Modifier = Modifier
){
    Row(modifier = modifier){
        Column{
            Row{
                GoBack()
            }
            Row{
                UserProfileImage()
            }
            Row{
                UserName()
            }
            Row{
                UserEmail()
            }
            Row{
                EditProfile()
            }
            Row{
                UserVehicles()
            }
            Row{
                UserHistory()
            }
            Row{
                UserValorations()
            }
            Row {
                UserRewards()
            }
            Row {
                UserMarks()
            }
            Row{
                LogOutButton()
            }
        }
    }
}

@Composable
private fun GoBack() {
   Column() {
       TextButton(
           border = BorderStroke(0.dp, Color.Transparent),
           onClick = {  },
           colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
           modifier = Modifier
               .padding(start = 15.dp, top = 20.dp),
       ) {
           Row(
               modifier = Modifier
           ) {
               Image(

                   painter = painterResource(R.drawable.close),
                   contentDescription = "Vehicle",
                   modifier = Modifier.size(20.dp)
               )
           }
       }
   }
}

@Composable
private fun UserProfileImage(
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val painter: Painter = painterResource(id = R.drawable.profile)
        Image(painter = painter, contentDescription = "My Image",
            Modifier
                .clip(CircleShape)
                .size(75.dp))
    }
}

@Composable
private fun UserName(
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        horizontalAlignment =  Alignment.CenterHorizontally
    )  {
        Text(
            text = stringResource(
                R.string.username
            ),
            style = TypeFont.body1,
            fontSize = 25.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun UserEmail(
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        horizontalAlignment =  Alignment.CenterHorizontally
    )  {
        Text(
            text = stringResource(
                R.string.mail
            ),
            style = TypeFont.body1,
            fontSize = 20.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
private fun EditProfile(

) {

    Column(
        modifier = Modifier
            .padding(start = 50.dp, top = 15.dp),
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
                Image(

                    painter = painterResource(R.drawable.useredit),
                    contentDescription = "Vehicle",
                    modifier = Modifier.size(35.dp)
                )
                Spacer(Modifier.width(15.dp))
                Text(
                    stringResource(
                        R.string.editprofile
                    ),
                    textAlign = TextAlign.Left,
                    color = Color.Black,
                    style = TypeFont.body1,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
    }
}

@Composable
private fun UserVehicles(

) {

    Column(
        modifier = Modifier
            .padding(start = 50.dp, top = 10.dp),
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
                Image(

                    painter = painterResource(R.drawable.electriccar),
                    contentDescription = "Vehicle",
                    modifier = Modifier.size(35.dp)
                )
                Spacer(Modifier.width(15.dp))
                Text(
                    stringResource(
                        R.string.uservehicles
                    ),
                    textAlign = TextAlign.Left,
                    color = Color.Black,
                    style = TypeFont.body1,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
    }
}


@Composable
private fun UserHistory(

) {

    Column(
        modifier = Modifier
            .padding(start = 50.dp, top = 10.dp),
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
                Image(

                    painter = painterResource(R.drawable.historical),
                    contentDescription = "Vehicle",
                    modifier = Modifier.size(35.dp)
                )
                Spacer(Modifier.width(15.dp))
                Text(
                    stringResource(
                        R.string.userhistory
                    ),
                    textAlign = TextAlign.Left,
                    color = Color.Black,
                    style = TypeFont.body1,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
    }
}

@Composable
private fun UserValorations(

) {

    Column(
        modifier = Modifier
            .padding(start = 50.dp, top = 10.dp),
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
                Image(

                    painter = painterResource(R.drawable.favourite),
                    contentDescription = "Vehicle",
                    modifier = Modifier.size(35.dp)
                )
                Spacer(Modifier.width(15.dp))
                Text(
                    stringResource(
                        R.string.uservalorations
                    ),
                    textAlign = TextAlign.Left,
                    color = Color.Black,
                    style = TypeFont.body1,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
    }
}

@Composable
private fun UserRewards(

) {

    Column(
        modifier = Modifier
            .padding(start = 50.dp, top = 10.dp),
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
                Image(

                    painter = painterResource(R.drawable.medal),
                    contentDescription = "Vehicle",
                    modifier = Modifier.size(35.dp)
                )
                Spacer(Modifier.width(15.dp))
                Text(
                    stringResource(
                        R.string.userrewards
                    ),
                    textAlign = TextAlign.Left,
                    color = Color.Black,
                    style = TypeFont.body1,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
    }
}

@Composable
private fun UserMarks(

) {

    Column(
        modifier = Modifier
            .padding(start = 50.dp, top = 10.dp),
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
                Image(

                    painter = painterResource(R.drawable.location),
                    contentDescription = "Vehicle",
                    modifier = Modifier.size(35.dp)
                )
                Spacer(Modifier.width(15.dp))
                Text(
                    stringResource(
                        R.string.usermarks
                    ),
                    textAlign = TextAlign.Left,
                    color = Color.Black,
                    style = TypeFont.body1,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
    }
}


class Profile : ComponentActivity() {
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
fun LogOutButton(
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 70.dp, end = 70.dp, bottom = 10.dp, top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(backgroundColor = RedECO),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()

        ) {
            Text(
                stringResource(
                    R.string.logout_message
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

@Preview(showBackground = true)
@Composable
fun PreviewProfile() {
    ProfileView()
}