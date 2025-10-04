package com.p2he.rustore.ui.gallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.p2he.rustore.R
import com.p2he.rustore.model.AppModel
import com.p2he.rustore.repository.AppRepository
import com.p2he.rustore.ui.ViewModelFactory
import com.p2he.rustore.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(
    navController: NavController,
    viewModel: GalleryViewModel = viewModel(factory = ViewModelFactory(AppRepository()))
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { GalleryTopAppBar(navController) },
        containerColor = RuStoreDarkPurpleGray // Замена на цвет из палитры
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (val state = uiState) {
                is GalleryUiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = RuStoreWhite)
                    }
                }
                is GalleryUiState.Success -> {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(20.dp),
                        color = RuStoreWhite
                    ) {
                        LazyColumn(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            item { Spacer(modifier = Modifier.height(8.dp)) } // Отступ сверху
                            items(state.apps) { app ->
                                AppListItem(
                                    app = app,
                                    onAppClick = { navController.navigate("appDetails/${app.id}") }
                                )
                            }
                            item { Spacer(modifier = Modifier.height(8.dp)) } // Отступ снизу
                        }
                    }
                }
                is GalleryUiState.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = state.message, color = RuStoreWhite)
                    }
                }
            }
        }
    }
}

@Composable
fun GalleryTopAppBar(navController: NavController) {
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
            Text("ГЛАВНАЯ", color = RuStoreDarkPink, textDecoration = TextDecoration.Underline, fontWeight = FontWeight.Bold)
            Text(
                "КАТЕГОРИИ",
                color = RuStoreWhite,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.clickable { navController.navigate("categories") }
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GalleryScreenPreview() {
    RuStoreTheme {
        val fakeNavController = NavController(androidx.compose.ui.platform.LocalContext.current)
        val fakeViewModel = GalleryViewModel(AppRepository())
        GalleryScreen(navController = fakeNavController, viewModel = fakeViewModel)
    }
}
