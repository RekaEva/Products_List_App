package com.example.productslistapp.presentation.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.productslistapp.R
import com.example.productslistapp.presentation.viewmodel.ProductViewModel

@Composable
fun ProductScreen(
    productViewModel: ProductViewModel,
    onClickNav: () -> Unit = {},
    id: Int
) {
    val uiState by productViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        productViewModel.getProductData(id)
    }

    Column {
        TextButton(
            onClick = { onClickNav() },
            modifier = Modifier.border(
                2.dp,
                Color.Black,
                RoundedCornerShape(10.dp)
            ),
        ) {
            Text(
                text = stringResource(R.string.back_button),
                fontSize = 16.sp,
                color = Color.Black
            )
        }
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp),
                )
            }
        } else if (uiState.productData != null) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                ProductDataCard(
                    title = uiState.productData?.title,
                    description = uiState.productData?.description,
                    thumbnail = uiState.productData?.thumbnail,
                    price = uiState.productData?.price,
                    rating = uiState.productData?.rating,
                    brand = uiState.productData?.brand
                )
            }
        } else uiState.error?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(bottom = 10.dp)
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = errorMessage(it),
                    textAlign = TextAlign.Center
                )
                TextButton(
                    onClick = { productViewModel.getProductData(id) },
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.CenterHorizontally)
                        .border(
                            2.dp,
                            Color.Black,
                            RoundedCornerShape(10.dp)
                        )
                ) {
                    Text(
                        text = stringResource(R.string.update_button),
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun ProductDataCard(
    title: String?,
    description: String?,
    thumbnail: String?,
    price: Int?,
    rating: Double?,
    brand: String?
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Column {
            thumbnail?.let {
                AsyncImage(
                    model = thumbnail,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Fit
                )
            }
            title?.let {
                Text(
                    text = title,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 15.dp)
                )
            }
            brand?.let {
                Text(
                    text = stringResource(id = R.string.brand) + " $brand",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(5.dp)
                )
            }
            rating?.let {
                Text(
                    text = stringResource(id = R.string.rating) + " * $rating",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(5.dp)
                )
            }
            price?.let {
                Text(
                    text = stringResource(id = R.string.price) + " $price$",
                    modifier = Modifier.padding(5.dp)
                )
            }
            description?.let {
                Text(
                    text = description,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }
}


