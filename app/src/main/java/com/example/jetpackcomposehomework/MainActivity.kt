package com.example.jetpackcomposehomework

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactDetails(contact =
            Contact(
                name = "Евгений",
                surname = "Лукашин",
                familyName = "Андреевич",
                imageRes = null,
                isFavorite = true,
                phone = "+7 495 495 95 95",
                address = "г. Москва, 3-я улица Строителей, д.25, кв. 12",
                email = "ELukhashin@practicum.ru",
            ))
        }
    }
}

@Composable
fun ContactDetails(contact: Contact) {
    Column(
        modifier = Modifier.statusBarsPadding().fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        RoundInitials(contact)
        Name(contact)
        InfoRow(R.string.phone, contact.phone)
        InfoRow(R.string.address, contact.address)
        if(!contact.email.isNullOrBlank()) {InfoRow(R.string.email, contact.email!!)}
    }
}

@Composable
fun InfoRow(@StringRes title: Int, info: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .padding(start = 120.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            text = stringResource(id = title),
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.End)
        )

        Text(
            text = info,
            modifier = Modifier
                .weight(2f)
                .wrapContentWidth(Alignment.Start)
                .padding(start = 10.dp),
            maxLines = 2
        )
    }
}

@Composable
fun RoundInitials(contact: Contact) {

    if (contact.imageRes == null) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.circle),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.LightGray),
            )
            Text(
                text = contact.name.take(1),
                modifier = Modifier.padding(end = 8.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = contact.surname!!.take(1),
                modifier = Modifier.padding(start = 8.dp),
                fontWeight = FontWeight.Bold
            )
        }
    } else {

        Image(
            modifier = Modifier.size(100.dp, 100.dp),
            painter = painterResource(contact.imageRes),
            contentDescription = null
        )

    }
}

@Composable
fun Name(contact: Contact) {
    Row(
        modifier = Modifier
            .padding(top = 15.dp, bottom = 60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                Text(
                    text = contact.name,
                    fontWeight = FontWeight.Bold
                )
                if (!contact.surname.isNullOrEmpty()) {
                    Text(
                        text = contact.surname,
                        modifier = Modifier.padding(start = 5.dp),
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = contact.familyName,
                    modifier = Modifier.padding(top = 5.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                )
                if (contact.isFavorite) {
                    Image(
                        modifier = Modifier
                            .padding(start = 8.dp),
                        painter = painterResource(id = android.R.drawable.star_big_on),
                        contentDescription = null
                    )
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun ContactDetailsPreview() {

    ContactDetails(contact = Contact(
        name = "Евгений",
        surname = "Андреевич",
        familyName = "Лукашин",
        imageRes = null,
        isFavorite = true,
        phone = "+7 495 495 95 95",
        address = "г. Москва, 3-я улица Строителей, д.25, кв. 12",
        email = "ELukhashin@practicum.ru",
    )
    )
}

@Preview(showSystemUi = true)
@Composable
fun ContactDetailsPreview2() {
    ContactDetails(contact = Contact(
        name = "Василий",
        surname = null,
        familyName = "Кузякин",
        imageRes = R.drawable.nia_480,
        isFavorite = false,
        phone = "---",
        address = "Ивановская область, дер. Крутово, д 4",
        email = null,
    )
    )
}



