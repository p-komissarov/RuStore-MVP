package com.p2he.rustore.ui.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.p2he.rustore.R
import com.p2he.rustore.model.AppModel
import com.p2he.rustore.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(navController: NavController) {
    Scaffold(
        topBar = { CategoriesTopAppBar(navController) },
        containerColor = RuStoreDarkPurpleGray
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                color = RuStoreWhite
            ) {
                Column {
                    SubCategoryTabs()
                    CategoryContent(navController = navController)
                }
            }
        }
    }
}

@Composable
fun CategoriesTopAppBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Главная",
                color = RuStoreWhite,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.clickable { navController.navigate("gallery") })
            Text("Категории", color = RuStoreWhite, textDecoration = TextDecoration.Underline, fontWeight = FontWeight.Bold)
            Text("О нас", color = RuStoreWhite, fontWeight = FontWeight.Normal)
        }
        IconButton(onClick = { /* TODO: Menu click */ }) {
            // ПРИМЕЧАНИЕ: Иконка флага из макета заменена на иконку меню
            Image(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = "Меню",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun SubCategoryTabs() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("СОЦ. СЕТИ", fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline, color = RuStoreBlack, fontSize = 14.sp)
        Text("ИГРЫ", fontWeight = FontWeight.Normal, color = RuStoreGray, fontSize = 14.sp)
        Text("МАРКЕТПЛЕЙСЫ", fontWeight = FontWeight.Normal, color = RuStoreGray, fontSize = 14.sp)
    }
}

@Composable
fun CategoryContent(navController: NavController) {
    // ИСПРАВЛЕНО: Добавлены все недостающие параметры в модель
    val apps = remember {
        listOf(
            AppModel("1", "БЕЗ НАЗВАНИЯ", "НЕИЗВЕСТЕН", "", "", "Категория", "", "0+", "", "1.0", "0MB"),
            AppModel("2", "БЕЗ НАЗВАНИЯ", "НЕИЗВЕСТЕН", "", "", "Категория", "", "0+", "", "1.0", "0MB"),
            AppModel("3", "БЕЗ НАЗВАНИЯ", "НЕИЗВЕСТЕН", "", "", "Категория", "", "0+", "", "1.0", "0MB"),
            AppModel("4", "БЕЗ НАЗВАНИЯ", "НЕИЗВЕСТЕН", "", "", "Категория", "", "0+", "", "1.0", "0MB"),
            AppModel("5", "БЕЗ НАЗВАНИЯ", "НЕИЗВЕСТЕН", "", "", "Категория", "", "0+", "", "1.0", "0MB"),
            AppModel("6", "БЕЗ НАЗВАНИЯ", "НЕИЗВЕСТЕН", "", "", "Категория", "", "0+", "", "1.0", "0MB"),
            AppModel("7", "БЕЗ НАЗВАНИЯ", "НЕИЗВЕСТЕН", "", "", "Категория", "", "0+", "", "1.0", "0MB"),
        )
    }

    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
    ) {
        item {
            Text(
                text = "КАТЕГОРИЯ",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = RuStoreBlack,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        items(apps) { app ->
            CategoryAppListItem(
                app = app,
                onAppClick = { navController.navigate("appDetails/${app.id}") }
            )
        }
    }
}

@Composable
fun CategoryAppListItem(app: AppModel, onAppClick: (String) -> Unit) {
    Column(modifier = Modifier.clickable { onAppClick(app.id) }) {
        Row(
            modifier = Modifier.padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(RuStoreLighterGray)
            )

            Spacer(Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = app.name.uppercase(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = RuStoreBlack
                )
                Text(
                    text = app.developer,
                    fontSize = 12.sp,
                    color = RuStoreGray
                )
                Spacer(Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(
                        onClick = { /* TODO: Download click */ },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = RuStoreBrightPink),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                        modifier = Modifier.height(24.dp)
                    ) {
                        Text("СКАЧАТЬ", fontSize = 10.sp, color = RuStoreWhite)
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "покупки в \nприложении",
                        fontSize = 8.sp,
                        lineHeight = 9.sp,
                        color = RuStoreGray,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
        HorizontalDivider(color = RuStoreLighterGray, thickness = 1.dp)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategoriesScreenPreview() {
    RuStoreTheme {
        CategoriesScreen(navController = NavController(androidx.compose.ui.platform.LocalContext.current))
    }
}
