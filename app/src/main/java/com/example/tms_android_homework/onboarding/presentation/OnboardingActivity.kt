package com.example.tms_android_homework.onboarding.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.tms_android_homework.App
import com.example.tms_android_homework.databinding.ActivityOnboardingBinding
import com.example.tms_android_homework.note.presentation.MainActivity
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnboardingActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewBinding: ActivityOnboardingBinding
    private val viewModel: OnboardingViewModel by viewModels{viewModelFactory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this.application as App).provideAppComponent().inject(this)

        lifecycleScope.launch {
            viewModel.isOnboardingChecked.drop(1).collect {
                if (it) {
                    startActivity(Intent(this@OnboardingActivity, MainActivity::class.java))
                    finish()
                }
            }
        }

        enableEdgeToEdge()

        viewBinding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnLetsStart.setOnClickListener {
            viewModel.goToNotesList()
        }
    }
}