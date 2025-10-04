package com.p2he.rustore.ui.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.p2he.rustore.R
import com.p2he.rustore.ui.theme.RuStoreDarkPink
import com.p2he.rustore.ui.theme.RuStoreDarkPurpleGray
import com.p2he.rustore.ui.theme.RuStoreWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsScreen(navController: NavController) {
    Scaffold(
        topBar = { AboutUsTopAppBar(navController) },
        containerColor = RuStoreDarkPurpleGray
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "О НАС",
                color = RuStoreWhite,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(top = 20.dp, bottom = 16.dp)
            )
            Text(
                text = "RUSTORE — это официальный российский магазин приложений для ANDROID, разработанный VK совместно с другими крупными IT-компаниями при поддержке Минцифры РФ. Платформа предназначена для поиска, скачивания и обновления приложений, игр, сервисов и других цифровых продуктов пользователями устройств на базе ANDROID.",
                color = RuStoreWhite,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Основные возможности и назначение:",
                color = RuStoreWhite,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "• В RUSTORE можно найти и скачать различные категории приложений, включая банковские, образовательные, развлекательные, навигационные программы и игры.",
                    color = RuStoreWhite,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
                Text(
                    text = "• Платформа предоставляет доступ к привычным российским сервисам и приложениям.",
                    color = RuStoreWhite,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
                Text(
                    text = "• RUSTORE проходят многоуровневую проверку, что обеспечивает безопасность приложений и защищенность пользовательских данных, как заявляется на официальном сайте.",
                    color = RuStoreWhite,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
                Text(
                    text = "• Платформа способствует развитию российской IT-индустрии, предоставляя разработчикам инструменты для монетизации, продвижения и автоматизации публикации своих приложений через",
                    color = RuStoreWhite,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Composable
fun AboutUsTopAppBar(navController: NavController) {
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
                fontSize = 16.sp,
                modifier = Modifier.clickable { navController.navigate("gallery") })
            Text(
                "КАТЕГОРИИ",
                color = RuStoreWhite,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                modifier = Modifier.clickable { navController.navigate("categories") })
            Text(
                "О НАС",
                color = RuStoreDarkPink,
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
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
