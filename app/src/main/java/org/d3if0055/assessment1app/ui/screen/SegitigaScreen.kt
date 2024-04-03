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
fun SegitigaScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.segitiga)) },
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
        ScreenContentSegitiga(Modifier.padding(padding))
    }
}

@Composable
fun ScreenContentSegitiga(modifier: Modifier) {
    var alas by rememberSaveable { mutableStateOf("") }
    var alasError by rememberSaveable { mutableStateOf(false) }

    var tinggi by rememberSaveable { mutableStateOf("") }
    var tinggiError by rememberSaveable { mutableStateOf(false) }

    var luas by rememberSaveable { mutableDoubleStateOf(0.0) }

    val symbols = DecimalFormatSymbols(Locale("id")).apply {
        decimalSeparator = ','
        groupingSeparator = '.'
    }

    val formatter = DecimalFormat("#,##0.00", symbols)
    val formattedArea = formatter.format(luas)
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
            text = stringResource(id = R.string.segitiga_deskripsi),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = alas,
            onValueChange = {
                alas = it
                alasError = !alas.matches(Regex("[1-9][0-9]*"))
            },
            label = { Text(text = stringResource(id = R.string.alas)) },
            isError = alasError,
            trailingIcon = { IconPickerSegitiga(alasError) },
            supportingText = { ErrorHintSegitiga(alasError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = tinggi,
            onValueChange = {
                tinggi = it
                tinggiError = !tinggi.matches(Regex("[1-9][0-9]*"))
            },
            label = { Text(text = stringResource(id = R.string.tinggi)) },
            isError = tinggiError,
            trailingIcon = { IconPickerSegitiga(tinggiError) },
            supportingText = { ErrorHintSegitiga(tinggiError) },
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
                    alasError = (alas == "" || !alas.matches(Regex("[1-9][0-9]*")))
                    tinggiError = (tinggi == "" || !tinggi.matches(Regex("[1-9][0-9]*")))

                    if (alasError || tinggiError) return@Button

                    luas = 0.5 * alas.toDouble() * tinggi.toDouble()
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.hitung))
            }


            Spacer(Modifier.width(8.dp))

            Button(
                onClick = {
                    alas = ""
                    tinggi = ""
                    luas = 0.0
                    alasError = false
                    tinggiError = false
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.reset))
            }

        }

        if (luas != 0.0) {
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(
                text = "Luas: $formattedArea",
                style = MaterialTheme.typography.titleMedium
            )
            Button(
                onClick = {
                    shareData(
                        context = context,
                        message = context.getString(R.string.bagikan_template_segitiga,
                            formattedArea)
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
fun IconPickerSegitiga(error: Boolean) {
    if (error) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    }
}

@Composable
fun ErrorHintSegitiga(error: Boolean) {
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
fun ScreenPreviewSegitiga() {
    Assessment1AppTheme {
        LingkaranScreen(rememberNavController())
    }
}

