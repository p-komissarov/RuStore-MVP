package com.p2he.rustore.ui.details

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.p2he.rustore.R
import com.p2he.rustore.model.AppModel
import com.p2he.rustore.repository.AppRepository
import com.p2he.rustore.ui.theme.RuStoreBlack
import com.p2he.rustore.ui.theme.RuStoreBrightPink
import com.p2he.rustore.ui.theme.RuStoreDarkPink
import com.p2he.rustore.ui.theme.RuStoreDarkPurpleGray
import com.p2he.rustore.ui.theme.RuStoreGray
import com.p2he.rustore.ui.theme.RuStoreLightGray
import com.p2he.rustore.ui.theme.RuStoreLighterGray
import com.p2he.rustore.ui.theme.RuStoreTheme
import com.p2he.rustore.ui.theme.RuStoreWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsScreen(navController: NavController, appId: String?) {
    val repository = remember { AppRepository() }
    var app by remember { mutableStateOf<AppModel?>(null) }

    LaunchedEffect(appId) {
        app = appId?.let { repository.getAppById(it) }
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(RuStoreDarkPurpleGray)
                    .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Image(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = "Назад",
                        modifier = Modifier.size(32.dp)
                    )
                }
                IconButton(onClick = { navController.navigate("menu") }) {
                    Image(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = "Меню",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        },
        containerColor = RuStoreDarkPurpleGray
    ) { paddingValues ->
        if (app != null) {
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                AppHeader(app = app!!)

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    shape = RoundedCornerShape(20.dp),
                    color = RuStoreWhite
                ) {
                    LazyColumn(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        item { QuickInfoSection(app = app!!) }
                        item { ScreenshotsSection(screenshots = app!!.screenshots) }
                        item { DescriptionSection(app = app!!) }
                        item { FullInfoSection(app = app!!) }
                    }
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun AppHeader(app: AppModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(RuStoreDarkPurpleGray)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val painter = if (app.iconRes != null) {
            painterResource(id = app.iconRes)
        } else {
            rememberAsyncImagePainter(app.iconUrl)
        }

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(RuStoreLighterGray)
        )
        Spacer(Modifier.width(24.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(app.name, color = RuStoreWhite, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(app.developer, color = RuStoreLightGray, fontSize = 16.sp)
            Spacer(Modifier.height(12.dp))
            Button(
                onClick = { /* TODO: Действие для скачивания */ },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = RuStoreBrightPink),
                modifier = Modifier.height(28.dp),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp)
            ) {
                Text("СКАЧАТЬ", color = RuStoreWhite, fontSize = 11.sp)
            }
        }
    }
}

@Composable
fun QuickInfoSection(app: AppModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Bottom
    ) {
        InfoChip(text = app.ageRating, label = "")
        InfoChip(text = app.size, label = "")
        InfoChip(drawableRes = R.drawable.provider, label = "Разработчик")
        InfoChip(drawableRes = R.drawable.category, label = "Категория")
    }
}

@Composable
fun InfoChip(
    text: String? = null,
    icon: ImageVector? = null,
    @DrawableRes drawableRes: Int? = null,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
            .size(width = 80.dp, height = 80.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(RuStoreDarkPink)
            .padding(8.dp)
    ) {
        if (text != null) {
            Text(text, color = RuStoreWhite, fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center)
        }
        if (icon != null) {
            Icon(icon, contentDescription = label, tint = RuStoreWhite, modifier = Modifier.size(32.dp))
        }
        if (drawableRes != null) {
            Image(painter = painterResource(id = drawableRes), contentDescription = label, modifier = Modifier.size(40.dp))
        }
        if (label.isNotEmpty()) {
            Text(label, color = RuStoreWhite, fontSize = 10.sp, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun ScreenshotsSection(screenshots: List<Any>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        items(screenshots) { screenshot ->
            val painter = when (screenshot) {
                is String -> rememberAsyncImagePainter(screenshot)
                is Int -> painterResource(id = screenshot)
                else -> return@items
            }
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .height(300.dp)
                    .width(180.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(RuStoreLighterGray),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun DescriptionSection(app: AppModel) {
    Column {
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), color = RuStoreLighterGray)
        Text(
            text = app.longDescription,
            color = RuStoreGray,
            fontSize = 14.sp
        )
    }
}

@Composable
fun FullInfoSection(app: AppModel) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("ИНФОРМАЦИЯ", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = RuStoreBlack)
        Spacer(modifier = Modifier.height(8.dp))
        InfoRow(label = "ПРОДАВЕЦ", value = app.developer)
        InfoRow(label = "РАЗМЕР", value = app.size)
        InfoRow(label = "КАТЕГОРИЯ", value = app.category)
        InfoRow(label = "СОВМЕСТИМОСТЬ", value = "ANDROID")
        InfoRow(label = "ЯЗЫК", value = "PY, ENG")
        InfoRow(label = "ВОЗРАСТНОЕ ОГРАНИЧЕНИЕ", value = app.ageRating)
        InfoRow(label = "ПЛАТНЫЙ КОНТЕНТ", value = "ЕСТЬ")
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(50))
            .clip(RoundedCornerShape(50))
            .background(RuStoreDarkPink)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, color = RuStoreWhite, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        Text(value, color = RuStoreWhite, fontSize = 12.sp)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppDetailsScreenPreview() {
    RuStoreTheme {
        val navController = NavController(androidx.compose.ui.platform.LocalContext.current)
        AppDetailsScreen(navController = navController, appId = "1")
    }
}
