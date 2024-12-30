package com.example.homework_jc.ui.locations.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.homework_jc.data.model.LocationResponse

@Composable
fun LocationCard(location: LocationResponse.Location, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column {
                Text(text = location.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = location.type, style = MaterialTheme.typography.bodySmall)
                Text(text = location.dimension, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}