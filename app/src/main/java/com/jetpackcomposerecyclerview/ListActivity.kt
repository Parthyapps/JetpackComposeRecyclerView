package com.jetpackcomposerecyclerview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jetpackcomposerecyclerview.ui.theme.JetpackComposeRecyclerViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeRecyclerViewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val dataItems = (0..100).map {
                        if (it % 2 == 0) {
                            ItemTypeOne("How easy was that!")
                        } else {
                            ItemTypeTwo("Insanely easy", "See you later old android")
                        }
                    }
                    MySimpleList(
                        modifier = Modifier.fillMaxSize(),
                        dataItems,
                    )
                }
            }
        }
    }
}

sealed class DataItem
data class ItemTypeOne(val text: String) : DataItem()
data class ItemTypeTwo(
    val text: String,
    val description: String,
) : DataItem()

@Composable
fun MySimpleList(
    modifier: Modifier = Modifier,
    dataItems: List<DataItem>
) {
    LazyColumn(modifier = modifier) {
        items(dataItems) { data ->
            when (data) {
                is ItemTypeOne -> ItemOne(itemTypeOne = data)
                is ItemTypeTwo -> ItemTwo(itemTypeTwo = data)
            }
        }
    }
}


@Composable
fun ItemOne(itemTypeOne: ItemTypeOne) {
    Text(text = itemTypeOne.text)
}

@Composable
fun ItemTwo(itemTypeTwo: ItemTypeTwo) {
    Column {
        Text(text = itemTypeTwo.text)
        Text(text = itemTypeTwo.description)
    }
}

