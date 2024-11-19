package com.noxis.centeredslider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.noxis.centeredslider.ui.CenteredSlider
import com.noxis.centeredslider.ui.theme.CenteredSliderTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CenteredSliderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var value by remember { mutableFloatStateOf(5f) }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        CenteredSlider(
                            value = value,
                            onValueChanged = { value = it },
                            valueRange = -20f..20f,
                            center = 0f,
                            centerThreshold = 1f,
                            centerIndicator = {
                                Box(
                                    Modifier
                                        .width(6.dp)
                                        .height(16.dp)
                                        .background(
                                            color = MaterialTheme.colorScheme.surface,
                                            CircleShape
                                        )
                                        .padding(1.dp)
                                        .shadow(elevation = 10.dp, shape = CircleShape)
                                        .background(
                                            color = MaterialTheme.colorScheme.onSurface,
                                            CircleShape
                                        )
                                )
                            },
                            centerTrack = { activeSection ->
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(1.dp)
                                        .background(
                                            color = MaterialTheme.colorScheme.onSurface.copy(
                                                alpha = .5f
                                            )
                                        )
                                )
                                activeSection { isAboveCenter ->
                                    val brush = remember {
                                        Brush.horizontalGradient(
                                            when {
                                                isAboveCenter -> listOf(
                                                    Color(0xFF38BDF8),
                                                    Color(0xFF34D399)
                                                )

                                                else -> listOf(Color(0xFFEF4444), Color(0xFFEC4899))
                                            }
                                        )
                                    }
                                    Box(
                                        Modifier
                                            .fillMaxWidth()
                                            .height(8.dp)
                                            .scale(scaleX = 1.3f, scaleY = 1f)
                                            .blur(30.dp, BlurredEdgeTreatment.Unbounded)
                                            .background(
                                                brush = brush,
                                                shape = CircleShape
                                            )
                                    )
                                    Box(
                                        Modifier
                                            .fillMaxWidth()
                                            .height(8.dp)
                                            .background(
                                                brush = brush,
                                                shape = CircleShape
                                            )
                                    ) {
                                        Box(
                                            Modifier
                                                .align(if (!isAboveCenter) Alignment.CenterStart else Alignment.CenterEnd)
                                                .size(8.dp)
                                                .padding(2.dp)
                                                .background(color = Color.Black, CircleShape)
                                        )
                                    }
                                }
                            },
                            thumb = {
                                Box(
                                    Modifier
                                        .size(24.dp)
                                        .border(
                                            width = Dp.Hairline,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            shape = CircleShape
                                        )
                                )
                                Box(
                                    Modifier
                                        .offset(y = (-24).dp)
                                        .width((.4).dp)
                                        .height(16.dp)
                                        .background(MaterialTheme.colorScheme.onSurface)
                                )
                                Text(
                                    "${value.roundToInt()}",
                                    modifier = Modifier.offset(y = (-42).dp),
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 10.sp
                                    ),
                                    color = MaterialTheme.colorScheme.onSurface,
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
