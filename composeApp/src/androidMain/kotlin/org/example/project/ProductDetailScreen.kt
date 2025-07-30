package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.item1
import org.example.project.component.BaseScreen
import org.example.project.component.BaseSpacer
import org.example.project.model.Product
import org.example.project.viewmodel.SharedViewModel

// Created by Zain Shakoor
// on 7/28/2025


@Composable
fun ProductDetailScreen(sharedProductViewModel: SharedViewModel) {
    val product = sharedProductViewModel.selectedProduct
    println(product)

    if (product == null) {
        // You can show a loading, error, or navigate back
        BaseScreen {
            Column(modifier = Modifier.fillMaxSize()) {
                TopBarComponent("Product")
                Text(
                    "No product selected.",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        return
    }

    BaseScreen {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBarComponent("Product")
            BaseSpacer(height = 10.dp)
            ProductImageWithTitlesDesc(product = product)
            BaseSpacer(height = 10.dp)
            ProductReviewCount()
        }
    }
}


@Composable
fun ProductImageWithTitlesDesc(product: Product) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(218.dp),
        painter = rememberAsyncImagePainter(product.images?.firstOrNull()),
        contentScale = ContentScale.FillWidth,
        contentDescription = ""
    )

    BaseSpacer(height = 20.dp)
    Text(
        text = product.title,
        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(start = 15.dp)
    )
    BaseSpacer(height = 20.dp)
    Text(
        text = product.description,
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xff82696B)
        ),
        modifier = Modifier.padding(start = 15.dp)
    )
    BaseSpacer(height = 20.dp)
    Text(
        text = "$${product.price}",
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xff82696B)
        ), modifier = Modifier.padding(start = 15.dp)
    )
}

@Composable
fun ProductReviewCount(modifier: Modifier = Modifier) {
    val dummyRatings = mapOf(
        5 to 50,
        4 to 25,
        3 to 10,
        2 to 5,
        1 to 2
    )
    val total = dummyRatings.values.sum()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Left Column: 4.5 rating, stars, and review count
        Column(
            modifier = Modifier.weight(0.3f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "4.5",
                style = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            StarRating(rating = 5, onRatingChanged = {}, modifier = Modifier.padding(start = 8.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "1,234 reviews",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Right Column: Progress Bars
        Column(
            modifier = Modifier.weight(0.7f)
        ) {
            dummyRatings.toSortedMap(compareByDescending { it }).forEach { (stars, count) ->
                val percentage = count / total.toFloat()

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        text = "$stars",
                        fontSize = 14.sp,
                        modifier = Modifier.width(20.dp)
                    )
//                    Icon(
//                        imageVector = Icons.Filled.Star,
//                        contentDescription = null,
//                        tint = Color(0xFFFFC107),
//                        modifier = Modifier.size(16.dp)
//                    )
                    LinearProgressIndicator(
                        progress = percentage,
                        modifier = Modifier
                            .weight(1f)
                            .height(8.dp)
                            .padding(horizontal = 8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = Color(0xff171212),
                        trackColor = Color(0xffE3DEDE)
                    )
                    Text(
                        text = "${(percentage * 100).toInt()}%",
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}


@Composable
fun StarRating(
    rating: Int,
    onRatingChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
    totalStars: Int = 5
) {
    Row {

        for (i in 1..totalStars) {
            Icon(
                contentDescription = "",
                imageVector = if (i <= rating) Icons.Filled.Star else Icons.Outlined.Star,
                tint = Color(0xff171212), // gold
                modifier = Modifier
                    .size(32.dp)
                    .padding(start = 5.dp)
                    .clickable { onRatingChanged(i) }
            )
        }

    }

}

@Composable
fun ProductRatingProgressbar(
    ratingCounts: Map<Int, Int>, // e.g., mapOf(5 to 40, 4 to 20, ...)
    modifier: Modifier = Modifier
) {
    val maxCount = ratingCounts.maxOfOrNull { it.value } ?: 1

    Column(modifier = modifier) {
        for (i in 5 downTo 1) {
            val count = ratingCounts[i] ?: 0
            val progress = count / maxCount.toFloat()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            ) {
                Text(
                    text = i.toString(),
                    modifier = Modifier.width(24.dp),
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                )
                LinearProgressIndicator(
                    progress = progress.coerceIn(0f, 1f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    color = Color(0xFF171212), // Green
                    trackColor = Color(0XFFE3DEDE)
                )

                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = count.toString(),
                    style = TextStyle(fontSize = 12.sp)
                )
            }
        }
    }
}


@Composable
fun AverageRatingBar(
    averageRating: Float, // e.g., 4.2f
    maxRating: Int = 5
) {
    val progress = averageRating / maxRating

    Column(modifier = Modifier.fillMaxWidth()) {
        // Progress Bar
        LinearProgressIndicator(
            progress = progress.coerceIn(1f, 1f),
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(5.dp)),
            color = Color(0xFF4CAF50), // Green color
            trackColor = Color.LightGray
        )

        Spacer(modifier = Modifier.height(4.dp))

    }
}

@Composable
fun RatingCard() {

    Column(){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Image(
                painter = org.jetbrains.compose.resources.painterResource(Res.drawable.item1),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            BaseSpacer(width = 10.dp)
            Column {
                Text(
                    text = "count.toString()",
                    modifier = Modifier.padding(top = 10.dp),
                    style = TextStyle(fontSize = 12.sp)
                )
                Text(
                    text = "2 months ago",
                    style = TextStyle(fontSize = 12.sp)
                )
            }

        }
        StarRating(4, {})
        Text(
            text = LoremIpsum(words = 16).values.first(),
            style = TextStyle(fontSize = 12.sp))

        Row{
            Icon(Icons.Filled.ThumbUp, contentDescription = "thump up", Modifier.size(15.dp))
                    BaseSpacer(width = 20.dp)
            Icon(Icons.Filled.ThumbUp, contentDescription = "thump up", Modifier.size(15.dp))

        }
    }



}


//@Preview
//@Composable
//fun previewProductDetail() {
//    ProductDetailScreen()
//}

@Preview
@Composable
fun previewProductCount() {
    ProductReviewCount()
}

@Preview
@Composable
fun previewStartRating() {
    StarRating(3, {})
}

@Preview
@Composable
fun previewProductRatingProgressbar() {
    val dummyRatings = mapOf(
        5 to 50,
        4 to 25,
        3 to 10,
        2 to 5,
        1 to 2
    )
    ProductRatingProgressbar(
        dummyRatings
    )
}


@Preview
@Composable
fun previewProductAverageRatingBar() {
    AverageRatingBar(
        averageRating = 11.5f,
        maxRating = 100
    )
}


@Preview
@Composable
fun previewReviewCard() {
    RatingCard()

}
