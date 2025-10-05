package com.p2he.rustore.ui.gallery

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.p2he.rustore.R
import com.p2he.rustore.model.AppModel
import com.p2he.rustore.repository.AppRepository
import com.p2he.rustore.ui.theme.*

@Composable
fun GalleryScreen(
    navController: NavController,
    viewModel: GalleryViewModel = viewModel()
) {
    GalleryContent(navController = navController, viewModel = viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryContent(navController: NavController, viewModel: GalleryViewModel) {
    val uiState = viewModel.uiState.collectAsState().value
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission Accepted: Do something
        } else {
            // Permission Denied: Do something
        }
    }

    Scaffold(
        topBar = { GalleryTopAppBar(navController) },
        containerColor = RuStoreDarkPurpleGray
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            shape = RoundedCornerShape(20.dp),
            color = RuStoreWhite
        ) {
            when (uiState) {
                is GalleryUiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is GalleryUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                    ) {
                        items((uiState as GalleryUiState.Success).apps) { app ->
                            AppListItem(
                                app = app,
                                onAppClick = { appId -> navController.navigate("app_details/$appId") },
                                onDownloadClick = { launcher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE) }
                            )
                        }
                    }
                }
                is GalleryUiState.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = (uiState as GalleryUiState.Error).message,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
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
            Text(stringResource(R.string.home), color = RuStoreDarkPink, textDecoration = TextDecoration.Underline, fontWeight = FontWeight.Bold)
            Text(
                stringResource(R.string.categories),
                color = RuStoreWhite,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.clickable { navController.navigate("categories") }
            )
            Text(
                stringResource(R.string.about_us),
                color = RuStoreWhite,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.clickable { navController.navigate("about") }
            )
        }
        IconButton(onClick = { navController.navigate("menu") }) {
            Image(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = stringResource(R.string.menu),
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun AppListItem(
    app: AppModel,
    onAppClick: (String) -> Unit,
    onDownloadClick: () -> Unit
) {
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
                        onClick = onDownloadClick,
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = RuStoreBrightPink),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                        modifier = Modifier.height(24.dp)
                    ) {
                        Text(stringResource(R.string.download), fontSize = 10.sp, color = RuStoreWhite)
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.in_app_purchases),
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
        GalleryScreen(navController = fakeNavController)
    }
}
