package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zain.kmplearningsession.android.component.BaseScreen
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.item1
import org.example.project.component.BaseSpacer
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

// Created by Zain Shakoor
// on 7/25/2025


@Composable
fun RelatedProductScreen() {
    BaseScreen {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBarComponent(" Related Products")
            BaseSpacer(height = 20.dp)
            Text(
                "You Might Also Like",
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 23.sp,
                modifier = Modifier.padding(start = 15.dp)
            )
            BaseSpacer(height = 25.dp)
            ProductCardSliderListHorizontal()
            BaseSpacer(height = 25.dp)
            Text(
                "Customer Also Bought",
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 23.sp,
                modifier = Modifier.padding(start = 15.dp)
            )
            ProductCardSliderListVertical()
        }
    }
}


@Composable
fun ProductCardSliderListHorizontal() {
    LazyRow {
        contentPadding(start = 10.dp, end = 10.dp)
        items(10)
        {
            ProductCardSlider()
        }
    }
}


@Composable
fun ProductCardSliderListVertical() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),

    ) {
        contentPadding(start = 10.dp, end = 10.dp)
        items(10)
        {
            ProductCardSlider()
        }
    }
}


@Composable
fun ProductCardSlider() {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .padding(20.dp), // Padding from all sides
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = 170.dp, height = 200.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(Res.drawable.item1),
                contentDescription = "Product Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )

        }

        Spacer(modifier = Modifier.height(12.dp)) // Space between image and text

        Text(
            text = "Product Title",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Product Title",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Product Title",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Preview
@Composable
fun previewPreviewRelatedScreen() {
    RelatedProductScreen()
}


@Preview
@Composable
fun previewProductCardSlider() {
    ProductCardSlider()
}


@Preview
@Composable
fun previewProductCardSliderVertical() {
    ProductCardSliderListVertical()

}


@Preview
@Composable
fun previewProductList() {
    ProductCardSliderListHorizontal()

}

@Composable
fun getDrawableResource(drawable: DrawableResource): Painter {
    return org.jetbrains.compose.resources.painterResource(drawable)
}
