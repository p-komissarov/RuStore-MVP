package com.p2he.rustore.ui.appdetails

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.p2he.rustore.R
import com.p2he.rustore.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsScreen(navController: NavController, appId: String?) {
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
                IconButton(onClick = { /* TODO: Действие для меню */ }) {
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
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            AppHeader(appId = appId)

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
                    item { QuickInfoSection() }
                    item { ScreenshotsSection() }
                    item { DescriptionSection() }
                    item { FullInfoSection() }
                }
            }
        }
    }
}

@Composable
fun AppHeader(appId: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(RuStoreDarkPurpleGray)
            // ИСПРАВЛЕНО: Добавлено больше места для всей панели
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // ИСПРАВЛЕНО: Задан большой, но фиксированный размер
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(RuStoreLighterGray)
        )
        Spacer(Modifier.width(24.dp))
        // ИСПРАВЛЕНО: Кнопка "СКАЧАТЬ" снова на месте
        Column(modifier = Modifier.weight(1f)) {
            Text("НАЗВАНИЕ ПРИЛОЖЕНИЯ", color = RuStoreWhite, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("ИМЯ РАЗРАБОТЧИКА", color = RuStoreLightGray, fontSize = 16.sp)
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
fun QuickInfoSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Bottom
    ) {
        InfoChip(text = "0+", label = "")
        InfoChip(text = "500 КБ", label = "")
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
fun ScreenshotsSection() {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        items(4) {
            Box(
                modifier = Modifier
                    .height(300.dp)
                    .width(180.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(RuStoreLighterGray)
            )
        }
    }
}

@Composable
fun DescriptionSection() {
    Column {
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), color = RuStoreLighterGray)
        Text(
            text = "ОПИСАНИЕ ОПИСАНИЕ ОПИСАНИЕ ТРАЛАЛАЛАЛАЛАЛЛА ОПИСАНИЕ ОПИСАНИЕ " +
                    "ОПИСАНИЕ ТРАПАЛАЛАЛАЛАЛЛАОПИСАНИЕ ОПИСАНИЕ ОПИСАНИЕ " +
                    "ТРАПАЛАЛАЛАЛЛАОПИСАНИЕ ОПИСАНИЕ ОПИСАНИЕ ОПИСАНИЕ ОПИСАНИЕ",
            color = RuStoreGray,
            fontSize = 14.sp
        )
    }
}

@Composable
fun FullInfoSection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("ИНФОРМАЦИЯ", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = RuStoreBlack)
        Spacer(modifier = Modifier.height(8.dp))
        InfoRow(label = "ПРОДАВЕЦ", value = "ИМЯ")
        InfoRow(label = "РАЗМЕР", value = "0 КБ")
        InfoRow(label = "КАТЕГОРИЯ", value = "НАЗВАНИЕ КАТЕГОРИИ")
        InfoRow(label = "СОВМЕСТИМОСТЬ", value = "ANDROID, IOS, MACOS, WINDOWS")
        InfoRow(label = "ЯЗЫК", value = "PY, ENG")
        InfoRow(label = "ВОЗРАСТНОЕ ОГРАНИЧЕНИЕ", value = "0+")
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
        AppDetailsScreen(navController = NavController(androidx.compose.ui.platform.LocalContext.current), appId = "1")
    }
}
