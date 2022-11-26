package com.example.businesscard

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.businesscard.ui.theme.BusinessCardTheme
import com.example.businesscard.R


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BusinessCard()
                }
            }
        }
    }

    @Composable
    fun BusinessCard() {
        val logo = painterResource(id = R.drawable.android_logo)
        val email = stringResource(id = R.string.user_email)
        val fone = stringResource(id = R.string.user_contact)
        val repo = stringResource(id = R.string.github_shared_link)

        val context = LocalContext.current


        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.background_color_visiting_card)),

            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = logo,
                    contentDescription = null,
                    modifier = Modifier.width(80.dp))

                Text(
                    text = stringResource(R.string.user_name),
                    fontSize = 36.sp,
                    color = Color.White)

                Text(
                    text = stringResource(R.string.role_name),
                    color = colorResource(id = R.color.subtitle_color_visiting_card))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),

            ) {
            Column (
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.Center)
                    .fillMaxWidth(.7f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally

            ){
                RowIconText(
                    Icons.Filled.Call,
                    text = stringResource(R.string.user_contact),
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel: $fone"))
                            startActivity(intent)
                        }
//                        .fillMaxWidth(.7f)
                )
                RowIconText(
                    Icons.Filled.Share,
                    text = stringResource(R.string.github_shared),
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW , Uri.parse(repo))
                            startActivity(intent)
                        }
//                        .fillMaxWidth(.7f)
                )
                RowIconText(
                    Icons.Filled.Email,
                    text = stringResource(R.string.user_email),
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(Intent.ACTION_SENDTO)
                            intent.setData(Uri.parse("mailto:"))
                            intent.putExtra(Intent.EXTRA_EMAIL, email)
                            intent.putExtra(Intent.EXTRA_SUBJECT, "")

                            startActivity(context, intent, null)
                        }
//                        .fillMaxWidth(.7f),
                )
            }
        }
    }

    @Composable
    fun RowIconText(image: ImageVector, text: String, modifier: Modifier = Modifier) {
//        Divider(color = Color.Gray, thickness = 1.dp)
        Column(
            modifier = modifier,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 5.dp, end = 5.dp)
            ) {
                Icon(image, contentDescription = null)
                Text(
                    text= text,
                    modifier = Modifier.padding(start = 20.dp),
                    color = colorResource(id = R.color.contact_text_color)
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        BusinessCardTheme {
            BusinessCard()
        }
    }
}