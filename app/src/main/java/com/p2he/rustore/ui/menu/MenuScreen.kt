package com.p2he.rustore.ui.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.p2he.rustore.repository.AppRepository
import com.p2he.rustore.ui.theme.RuStoreDarkPink
import com.p2he.rustore.ui.theme.RuStoreDarkPurpleGray
import com.p2he.rustore.ui.theme.RuStoreWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
    val previousRoute = remember { navController.previousBackStackEntry?.destination?.route }
    val repository = remember { AppRepository() }
    val subCategories = remember { repository.getUniqueCategories() }

    Scaffold(
        containerColor = RuStoreDarkPurpleGray
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Назад",
                    tint = RuStoreWhite,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.2f))
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier.padding(start = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Главная",
                    color = if (previousRoute == "gallery") RuStoreDarkPink else RuStoreWhite,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = if (previousRoute == "gallery") TextDecoration.Underline else TextDecoration.None,
                    modifier = Modifier.clickable { navController.navigate("gallery") { popUpTo("gallery") { inclusive = true } } }
                )

                Text(
                    text = "Категории",
                    color = if (previousRoute?.startsWith("categories") == true) RuStoreDarkPink else RuStoreWhite,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = if (previousRoute?.startsWith("categories") == true) TextDecoration.Underline else TextDecoration.None,
                    modifier = Modifier.clickable { navController.navigate("categories") { popUpTo("categories") { inclusive = true } } }
                )

                Column(modifier = Modifier.padding(start = 32.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    subCategories.forEach { subCategory ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "— ", color = RuStoreWhite, fontSize = 18.sp)
                            Text(
                                text = subCategory,
                                color = RuStoreWhite,
                                fontSize = 18.sp,
                                modifier = Modifier.clickable { navController.navigate("categories?subCategory=$subCategory") }
                            )
                        }
                    }
                }

                Text(
                    text = "О нас",
                    color = if (previousRoute == "about") RuStoreDarkPink else RuStoreWhite,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = if (previousRoute == "about") TextDecoration.Underline else TextDecoration.None,
                    modifier = Modifier.clickable { navController.navigate("about") { popUpTo("about") { inclusive = true } } }
                )
            }
        }
    }
}
