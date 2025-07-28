package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.item1
import org.example.project.component.BaseScreen
import org.example.project.component.BaseSpacer
import org.jetbrains.compose.resources.painterResource

// Created by Zain Shakoor
// on 7/28/2025


@Composable
fun ProductDetailScreen() {
    BaseScreen {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBarComponent("Product")
            BaseSpacer(height = 10.dp)
            ProductImageWithTitlesDesc()


        }


    }
}


@Composable
fun ProductImageWithTitlesDesc() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(218.dp),
        painter = painterResource(Res.drawable.item1),
        contentScale = ContentScale.FillWidth,
        contentDescription = ""
    )

    BaseSpacer(height = 20.dp)
    Text(
        text = "L'Oreal Paris Voluminous Lash\nParadise Mascara",
        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(start = 15.dp)
    )
    BaseSpacer(height = 20.dp)
    Text(
        text = "L'Oreal Paris • Mascara",
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xff82696B)
        ),
        modifier = Modifier.padding(start = 15.dp)
    )
    BaseSpacer(height = 20.dp)
    Text(
        text = "\$10.99",
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

    Column(modifier = modifier.padding(16.dp)) {
        // Rating value
        Text(
            text = "4.5",
            style = TextStyle(fontSize = 26.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(4.dp))

        // Star visuals
        StarRating(rating = 5, onRatingChanged = {}) // You can adjust based on average

        Spacer(Modifier.height(8.dp))

        // Reviews count
        Text(
            text = "1,234 reviews",
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
        )

        Spacer(Modifier.height(12.dp))

        // Ratings breakdown
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
                    modifier = Modifier.width(24.dp),
                    fontSize = 14.sp
                )
                LinearProgressIndicator(
                    progress = percentage,
                    modifier = Modifier
                        .weight(1f)
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = Color(0xFFFFC107),
                    trackColor = Color(0xFFE0E0E0)
                )
                Text(
                    text = "${(percentage * 100).toInt()}%",
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 12.sp
                )
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
                tint = Color(0xFFFFD700), // gold
                modifier = Modifier
                    .size(32.dp)
                    .padding(start = 15.dp)
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


@Preview
@Composable
fun previewProductDetail() {
    ProductDetailScreen()
}

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

