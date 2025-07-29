package org.example.project

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import org.example.project.component.BaseScreen
import org.example.project.component.BaseSpacer
import org.example.project.effect.ProductEffects
import org.example.project.intent.ProductIntent
import org.example.project.model.Product
import org.example.project.viewmodel.ProductViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.androidx.compose.koinViewModel

// Created by Zain Shakoor
// on 7/25/2025

@Composable
fun RelatedProductScreen(
    navController: NavController,
    productViewModel: ProductViewModel = koinViewModel()
) {

    val stateScreen by productViewModel.productUiState.collectAsState()
    val eff = productViewModel.effects.receiveAsFlow()
    val context = LocalContext.current
    LaunchedEffect(Unit)
    {

        productViewModel.onIntent(ProductIntent.LoadProductandDetails)
    }

    LaunchedEffect(Unit) {
        eff.collectLatest {
            when (it) {
                is ProductEffects.ShowError -> {
                    Toast.makeText(context, it.mesgError, Toast.LENGTH_LONG).show()

                }

                is ProductEffects.ShowToast -> {
                    Toast.makeText(context, it.mesg, Toast.LENGTH_LONG).show()

                }
            }


        }

    }


    BaseScreen {
        Column(modifier = Modifier.fillMaxSize()) {
            BaseSpacer(height = 10.dp)

            TopBarComponent(" Related Products")
            BaseSpacer(height = 20.dp)
            Text(
                "You Might Also Like",
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 23.sp,
                modifier = Modifier.padding(start = 15.dp)
            )
            BaseSpacer(height = 10.dp)
            Row(modifier = Modifier.wrapContentHeight().wrapContentWidth()) {
                when {

                    stateScreen.isLoading -> {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }

                    stateScreen.error != null -> {
                        Text("Error : ${stateScreen.error}")

                    }

                    else -> {
                        if (stateScreen.products.isNotEmpty()) {
                            ProductCardSliderListHorizontal(
                                navController = navController,
                                products = stateScreen.products
                            )
                        }
                    }

                }


            }
            BaseSpacer(height = 10.dp)
            Text(
                "Customer Also Bought",
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 23.sp,
                modifier = Modifier.padding(start = 15.dp)
            )
            ProductCardSliderListVertical(
                products = stateScreen.products
            )
        }
    }
}


@Composable
fun ProductCardSliderListHorizontal(
    navController: NavController,
    products: List<Product>
) {
    LazyRow {
        contentPadding(start = 10.dp, end = 10.dp)
        items(products.size)

        { items ->
            val item = products[items]
            ProductCardSlider(
                onTap = {
                    navController.navigate("productDetails")

                },
                title = item.title,
                desc = item.description,
                price = item.price.toString(),
                image = item.images?.firstOrNull() ?: ""
            )
        }
    }
}


@Composable
fun ProductCardSliderListVertical(products: List<Product>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),

        ) {
        contentPadding(start = 10.dp, end = 10.dp)
        items(products.size)

        { items ->
            val item = products[items]
            ProductCardSlider(
                {},
                title = item.title,
                desc = "",
                price = item.price.toString(),
                image = item.images?.firstOrNull() ?: ""
            )
        }
    }
}


@Composable
fun ProductCardSlider(
    onTap: () -> Unit,
    title: String,
    desc: String,
    price: String,
    image: String
) {
    Column(
        modifier = Modifier
            .width(160.dp) // fixed width for consistent horizontal spacing
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .clickable { onTap() }
                .height(180.dp)
                .border(
                    width = 1.dp,
                    color = Color.Transparent, // or any other color
                    shape = RoundedCornerShape(10.dp)
                )               .background(Color.Gray)
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = rememberAsyncImagePainter(image),
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.medium)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = title, style = MaterialTheme.typography.bodyLarge, maxLines = 1)

        if (desc.isNotEmpty()) {
            Text(text = desc, style = MaterialTheme.typography.bodySmall, maxLines = 1)

        }
        Text(text = "PKR $price", style = MaterialTheme.typography.labelSmall)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewRelatedScreen() {
    val navController = rememberNavController()
    RelatedProductScreen(navController)
}

//
//
//@Preview
//@Composable
//fun previewProductCardSlider(navController: NavController) {
//    ProductCardSlider({})
//}


//@Preview
//@Composable
//fun previewProductCardSliderVertical() {
//    ProductCardSliderListVertical(
//        products = TODO()
//    )
//
//}


//@Preview
//@Composable
//fun previewProductList() {
//    ProductCardSliderListHorizontal()
//
//}

@Composable
fun getDrawableResource(drawable: DrawableResource): Painter {
    return painterResource(drawable)
}
