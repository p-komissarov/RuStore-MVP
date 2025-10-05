package com.p2he.rustore.ui.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.p2he.rustore.R
import com.p2he.rustore.model.AppModel
import com.p2he.rustore.repository.AppRepository
import com.p2he.rustore.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(navController: NavController, selectedSubCategory: String? = null) {
    val subCategories = listOf(
        "СОЦ. СЕТИ", "ИГРЫ", "МАРКЕТПЛЕЙСЫ", "ФОТО И ВИДЕО",
        "МУЗЫКА И АУДИО", "КНИГИ", "ОБРАЗОВАНИЕ", "ЗДОРОВЬЕ И СПОРТ"
    )
    var selectedTabIndex by remember { mutableStateOf(0) }

    LaunchedEffect(selectedSubCategory) {
        selectedSubCategory?.let { 
            val index = subCategories.indexOf(it)
            if (index != -1) {
                selectedTabIndex = index
            }
        }
    }

    Scaffold(
        topBar = { CategoriesTopAppBar(navController) },
        containerColor = RuStoreDarkPurpleGray
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(20.dp),
                color = RuStoreWhite
            ) {
                Column {
                    SubCategoryTabs(
                        selectedTabIndex = selectedTabIndex,
                        onTabSelected = { selectedTabIndex = it },
                        subCategories = subCategories
                    )
                    CategoryContent(navController = navController, selectedCategory = subCategories[selectedTabIndex])
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
            .height(96.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "ГЛАВНАЯ",
                color = RuStoreWhite,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.clickable { navController.navigate("gallery") })
            Text(
                "КАТЕГОРИИ",
                color = RuStoreDarkPink,
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.Bold,
            )
            Text(
                "О НАС",
                color = RuStoreWhite,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.clickable { navController.navigate("about") }
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
}

@Composable
fun SubCategoryTabs(selectedTabIndex: Int, onTabSelected: (Int) -> Unit, subCategories: List<String>) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        contentColor = RuStoreBlack,
        edgePadding = 16.dp,
        indicator = { tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = RuStoreBlack
                )
            }
        }
    ) {
        subCategories.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                text = { Text(text = title, fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal) }
            )
        }
    }
}

@Composable
fun CategoryContent(navController: NavController, selectedCategory: String) {
    val repository = remember { AppRepository() }
    val apps = remember(selectedCategory) {
        repository.getApps() // For now, we show all apps, as there's no category mapping
    }

    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
    ) {
        item {
            Text(
                text = selectedCategory,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = RuStoreBlack,
                modifier = Modifier.padding(top = 20.dp, bottom = 8.dp)
            )
        }
        items(apps) { app ->
            AppListItem(
                app = app,
                onAppClick = { navController.navigate("app_details/${app.id}") }
            )
        }
    }
}

@Composable
fun AppListItem(app: AppModel, onAppClick: (String) -> Unit) {
    Column(modifier = Modifier.clickable { onAppClick(app.id) }) {
        Row(
            modifier = Modifier.padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(RuStoreLighterGray)
            )

            Spacer(Modifier.width(24.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = app.name.uppercase(),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = RuStoreBlack
                )
                Text(
                    text = app.developer,
                    fontSize = 14.sp,
                    color = RuStoreGray
                )
                Spacer(Modifier.height(4.dp))
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
                        text = "ПОКУПКИ В\nПРИЛОЖЕНИИ",
                        fontSize = 8.sp,
                        lineHeight = 9.sp,
                        color = RuStoreGray
                    )
                }
            }
        }
        HorizontalDivider(color = RuStoreLighterGray, thickness = 1.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesScreenPreview() {
    RuStoreTheme {
        CategoriesScreen(navController = NavController(androidx.compose.ui.platform.LocalContext.current), selectedSubCategory = null)
    }
}
