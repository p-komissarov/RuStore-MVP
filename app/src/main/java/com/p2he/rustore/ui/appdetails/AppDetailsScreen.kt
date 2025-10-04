package com.p2he.rustore.ui.appdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.p2he.rustore.model.AppModel

val RuStoreMagenta = Color(0xFFC70077)
val DarkGrayBg = Color(0xFF1E1E1E) // Сделал фон темнее для контраста

@Composable
fun AppDetailsScreen(
    navController: NavController,
    appId: String, // ID теперь обязательный
    viewModel: AppDetailsViewModel = viewModel()
) {
    // Запускаем загрузку данных только один раз, когда appdId меняется
    LaunchedEffect(key1 = appId) {
        viewModel.loadApp(appId)
    }

    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = uiState) {
            is AppDetailsUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is AppDetailsUiState.Success -> {
                // Когда данные загружены, показываем основной контент
                AppDetailsContent(navController, state.app)
            }
            is AppDetailsUiState.Error -> {
                Text(
                    "Не удалось загрузить информацию о приложении.",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailsContent(navController: NavController, app: AppModel) {
    Scaffold(
        topBar = {
            Column(modifier = Modifier.background(DarkGrayBg)) {
                TopAppBar(
                    title = { },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад", tint = Color.White)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
                // Передаем реальные данные в заголовок
                AppHeader(app)
            }
        },
        containerColor = DarkGrayBg // фон для Scaffold
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(MaterialTheme.colorScheme.surface) // Используем цвет темы
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item { QuickInfoSection(app) }
            item { ScreenshotsSection() }
            item { DescriptionSection(app) }
            item { FullInfoSection(app) }
        }
    }
}

@Composable
fun AppHeader(app: AppModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = app.iconUrl,
            contentDescription = "Иконка ${app.name}",
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(app.name, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(app.developer, color = Color.Gray, fontSize = 16.sp)
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = { /* TODO: Действие для скачивания */ },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = RuStoreMagenta)
            ) {
                Text("СКАЧАТЬ")
            }
        }
    }
}

@Composable
fun QuickInfoSection(app: AppModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Top
    ) {
        InfoChip(text = app.ageRating, label = "Возраст")
        InfoChip(text = app.size, label = "Размер")
        InfoChip(icon = Icons.Default.Category, label = app.category)
        InfoChip(icon = Icons.Default.Person, label = app.developer)
    }
}

@Composable
fun InfoChip(text: String? = null, icon: ImageVector? = null, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(80.dp)
            .padding(vertical = 4.dp)
    ) {
        if (text != null) {
            Text(text, fontWeight = FontWeight.Bold, fontSize = 16.sp, textAlign = TextAlign.Center)
        }
        if (icon != null) {
            Icon(icon, contentDescription = label, modifier = Modifier.size(28.dp), tint = MaterialTheme.colorScheme.primary)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, fontSize = 12.sp, textAlign = TextAlign.Center, lineHeight = 14.sp)
    }
}


@Composable
fun ScreenshotsSection() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(4) {
            Box(
                modifier = Modifier
                    .height(280.dp)
                    .width(160.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.LightGray)
            )
        }
    }
}

@Composable
fun DescriptionSection(app: AppModel) {
    Column {
        Text("Описание", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text(
            text = app.longDescription,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun FullInfoSection(app: AppModel) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("Информация о приложении", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        InfoRow(label = "Версия", value = app.version)
        InfoRow(label = "Разработчик", value = app.developer)
        InfoRow(label = "Категория", value = app.category)
        InfoRow(label = "Возрастное ограничение", value = app.ageRating)
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, fontWeight = FontWeight.Normal)
    }
}
