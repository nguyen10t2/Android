package com.example.affirmations.model

import androidx.annotation.StringRes
import androidx.annotation.DrawableRes
data class Affirmations(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)