package com.example.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                color = Color.Blue
            ) {
                CreateBizCard()
            }
        }
    }
}

@Composable
fun CreateBizCard() {

    val buttonClickState = remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(CornerSize(15.dp)),
            backgroundColor = Color.White,
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile(
                    Modifier
                        .size(150.dp)
                        .padding(5.dp)
                )
                Divider()
                CreateInfo()


                Button(
                    onClick = {
                        buttonClickState.value = !buttonClickState.value
                    }) {
                    Text(
                        text = "Portfolio", style = MaterialTheme.typography.button
                    )
                }

                if (buttonClickState.value) {
                    Content()
                } else {
                    Box() {

                    }
                }
            }
        }
    }
}

@Composable
fun Content() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            Portfolio(data = listOf("Project1", "Project2", "Project3"))
        }
    }
}


@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data) { item ->
            CreateItem(item)
        }
    }
}


@Composable
fun CreateItem(item: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        border = BorderStroke(0.5.dp, color = Color.LightGray),
        elevation = 4.dp,
        shape = RoundedCornerShape(CornerSize(6.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(5.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            CreateImageProfile(
                Modifier
                    .size(60.dp)
                    .padding(4.dp)
            )

            Column(modifier = Modifier.padding(5.dp)) {
                Text(
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    text = item
                )

                Text(
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray,
                    text = "A great project indeed"
                )
            }
        }
    }
}

@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant,
            text = "Miles P."
        )

        Text(
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.subtitle1,
            text = "Android Compose Programmer",
        )

        Text(
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.subtitle1,
            text = "@themilesCompose"
        )
    }
}

@Composable
fun CreateImageProfile(modifier: Modifier) {
    Surface(
        modifier = modifier,
        border = BorderStroke(0.5.dp, color = Color.LightGray),
        shape = CircleShape,
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {

        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "profile image",
            modifier = Modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CreateBizCard()
}