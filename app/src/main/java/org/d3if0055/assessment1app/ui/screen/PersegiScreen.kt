package org.d3if0055.assessment1app.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0055.assessment1app.R
import org.d3if0055.assessment1app.ui.theme.Assessment1AppTheme
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersegiScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.persegi)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { padding ->
        ScreenContentPersegi(Modifier.padding(padding))
    }
}

@Composable
fun ScreenContentPersegi(modifier: Modifier) {
    var sisi by rememberSaveable { mutableStateOf("") }
    var sisiError by rememberSaveable { mutableStateOf(false) }

    var luas by rememberSaveable { mutableDoubleStateOf(0.0) }
    var keliling by rememberSaveable { mutableDoubleStateOf(0.0) }

    val symbols = DecimalFormatSymbols(Locale("id")).apply {
        decimalSeparator = ','
        groupingSeparator = '.'
    }

    val formatter = DecimalFormat("#,##0.00", symbols)
    val formattedArea = formatter.format(luas)
    val formattedPerimeter = formatter.format(keliling)
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.persegi_deskripsi),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = sisi,
            onValueChange = {
                sisi = it
                sisiError = !sisi.matches(Regex("[1-9][0-9]*"))
            },
            label = { Text(text = stringResource(id = R.string.sisi)) },
            isError = sisiError,
            trailingIcon = { IconPickerPersegi(sisiError) },
            supportingText = { ErrorHintPersegi(sisiError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .padding(top = 6.dp)
        ) {

            Button(
                onClick = {
                    sisiError = (sisi == "" || !sisi.matches(Regex("[1-9][0-9]*")))

                    if (sisiError) return@Button

                    luas = sisi.toDouble() * sisi.toDouble()
                    keliling = 4 * sisi.toDouble()
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.hitung))
            }


            Spacer(Modifier.width(8.dp))

            Button(
                onClick = {
                    sisi = ""
                    luas = 0.0
                    keliling = 0.0
                    sisiError = false
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.reset))
            }

        }

        if (luas != 0.0 || keliling != 0.0) {
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(
                text = "Luas: $formattedArea",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Keliling: $formattedPerimeter",
                style = MaterialTheme.typography.headlineMedium
            )
            Button(
                onClick = {
                    shareData(
                        context = context,
                        message = context.getString(R.string.bagikan_template_persegi,
                            formattedArea, formattedPerimeter)
                    )
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.bagikan))

            }
        }
    }
}

@Composable
fun IconPickerPersegi(error: Boolean) {
    if (error) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    }
}

@Composable
fun ErrorHintPersegi(error: Boolean) {
    if (error) {
        Text(text = stringResource(id = R.string.input_invalid))
    }
}

private fun shareData(context: Context, message: String){
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null){
        context.startActivity(shareIntent)
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreviewPersegi() {
    Assessment1AppTheme {
        PersegiScreen(rememberNavController())
    }
}