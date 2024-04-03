package org.d3if0055.assessment1app.ui.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.d3if0055.assessment1app.R
import org.d3if0055.assessment1app.model.BangunDatar
import org.d3if0055.assessment1app.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    val bangunDatarList = mainViewModel.bangunDatarList
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.About.route)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(id = R.string.tentang_aplikasi)
                        )
                    }
                }
            )
        }
    ) { padding ->
        BangunDatarList(
            modifier = Modifier.padding(padding),
            bangunDatarList = bangunDatarList,
            navController = navController,
            context = LocalContext.current
        )
    }
}

@Composable
fun BangunDatarList(
    bangunDatarList: List<BangunDatar>,
    navController: NavHostController,
    modifier: Modifier,
    context: Context
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(bangunDatarList) { bangunDatar ->
            BangunDatarItem(
                imageId = bangunDatar.bagImageId,
                title = bangunDatar.title,
                navController = navController,
                context = context
            )
        }
    }
}

@Composable
fun BangunDatarItem(
    imageId: Int,
    title: String,
    navController: NavHostController,
    context: Context
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate(route = getBangunDatarRoute(title, context))
            },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


private fun getBangunDatarRoute(title: String, context: Context): String {
    return when (title) {
        // context.getString(R.string.id) untuk mendapatkan string
        context.getString(R.string.persegi) -> Screen.Persegi.route
        context.getString(R.string.persegi_panjang) -> Screen.PersegiPanjang.route
        context.getString(R.string.lingkaran) -> Screen.Lingkaran.route
        context.getString(R.string.segitiga) -> Screen.Segitiga.route
        else -> ""
    }
}




