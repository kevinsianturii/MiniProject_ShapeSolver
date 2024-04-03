package org.d3if0055.assessment1app.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if0055.assessment1app.R
import org.d3if0055.assessment1app.model.BangunDatar


class MainViewModel : ViewModel() {

    val bangunDatarList = listOf(
        BangunDatar(
            title = "Persegi",
            bagImageId = R.drawable.persegi
        ),
        BangunDatar(
            title = "Persegi Panjang",
            bagImageId = R.drawable.persegi_panjang
        ),
        BangunDatar(
            title = "Lingkaran",
            bagImageId = R.drawable.lingkaran
        ),
        BangunDatar(
            title = "Segitiga",
            bagImageId = R.drawable.segitiga
        )
    )
}

