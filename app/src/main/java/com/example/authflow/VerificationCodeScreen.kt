package com.example.authflow

import android.os.CountDownTimer
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.authflow.ui.theme.BorderGray
import com.example.authflow.ui.theme.DarkText
import com.example.authflow.ui.theme.GrayText
import com.example.authflow.ui.theme.GreenPrimary
import com.example.authflow.ui.theme.RedError
import com.example.authflow.ui.theme.White

@Composable
fun VerificationCodeScreen(
    title: String = "Check your email",
    subtitle: String = "We've sent the code to your email",
    onVerifyClick: () -> Unit,
    onBackClick: () -> Unit
) {
    var code by remember { mutableStateOf(TextFieldValue("", TextRange(0))) }
    var timeLeft by remember { mutableStateOf(192) } // 3:12 in seconds
    var timerRunning by remember { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }

    val minutes = timeLeft / 60
    val seconds = timeLeft % 60
    val timerText = String.format("%02d:%02d", minutes, seconds)
    val isCodeComplete = code.text.length == 4

    // Countdown timer
    DisposableEffect(timerRunning) {
        if (!timerRunning) return@DisposableEffect onDispose { }

        val timer = object : CountDownTimer(timeLeft * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = (millisUntilFinished / 1000).toInt()
            }
            override fun onFinish() {
                timeLeft = 0
                timerRunning = false
            }
        }.start()

        onDispose { timer.cancel() }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        // Title
        Text(
            text = title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Subtitle
        Text(
            text = subtitle,
            fontSize = 16.sp,
            color = GrayText
        )

        Spacer(modifier = Modifier.height(60.dp))

        // Hidden text field for actual input
        BasicTextField(
            value = code,
            onValueChange = { newValue ->
                val filtered = newValue.text.filter { it.isDigit() }.take(4)
                val newSelection = if (filtered.length == 4) TextRange(4) else TextRange(filtered.length)
                code = TextFieldValue(filtered, newSelection)
            },
            modifier = Modifier
                .size(1.dp)
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            cursorBrush = SolidColor(Color.Transparent),
            decorationBox = { }
        )

        // Code display boxes
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (i in 0 until 4) {
                val digit = code.text.getOrNull(i)?.toString() ?: ""
                val isActive = i == code.text.length

                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(
                            width = if (isActive) 2.dp else 1.dp,
                            color = when {
                                isActive -> GreenPrimary
                                digit.isNotEmpty() -> BorderGray
                                else -> BorderGray
                            },
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(White)
                        .clickable { focusRequester.requestFocus() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = digit,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkText,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(60.dp))

        // Timer text
        Text(
            text = "code expires in: ",
            fontSize = 14.sp,
            color = GrayText
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = timerText,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = RedError
        )

        Spacer(modifier = Modifier.weight(1f))

        // Verify Button
        Button(
            onClick = onVerifyClick,
            enabled = isCodeComplete,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(28.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = GreenPrimary,
                disabledContainerColor = GreenPrimary.copy(alpha = 0.5f),
                contentColor = White,
                disabledContentColor = White.copy(alpha = 0.7f)
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "Verify",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Send again button
        TextButton(
            onClick = {
                timeLeft = 192
                timerRunning = true
                code = TextFieldValue("", TextRange(0))
            },
            enabled = !timerRunning
        ) {
            Text(
                text = "Send again",
                fontSize = 14.sp,
                color = if (!timerRunning) GreenPrimary else GrayText,
                textDecoration = if (!timerRunning) TextDecoration.Underline else TextDecoration.None
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}
