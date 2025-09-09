package com.example.tms_android_homework.di
import com.example.tms_android_homework.nbrb.presentation.NbrbFragment
import com.example.tms_android_homework.note.presentation.ListFragment
import com.example.tms_android_homework.onboarding.presentation.OnboardingActivity
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        DatabaseModule::class,
        NetworkModel::class,
        RepoModel::class,
        AppModule::class,
        ViewModelModule::class
    ]
)
@Singleton
@FeatureScope
interface AppComponent {
    fun inject(nbrbFragment: NbrbFragment)
    fun inject(listFragment: ListFragment)
    fun inject(onboardingActivity: OnboardingActivity)
}