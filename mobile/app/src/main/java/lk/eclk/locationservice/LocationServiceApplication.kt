package lk.eclk.locationservice

import android.app.Application
import lk.eclk.locationservice.data.repository.Repository
import lk.eclk.locationservice.data.repository.RepositoryImpl
import lk.eclk.locationservice.data.db.AppDatabase
import lk.eclk.locationservice.data.proviers.JWTProvider
import lk.eclk.locationservice.data.proviers.JWTProviderImpl
import lk.eclk.locationservice.data.remote.interceptors.ConnectivityInterceptor
import lk.eclk.locationservice.data.remote.interceptors.ConnectivityInterceptorImpl
import lk.eclk.locationservice.data.remote.api.LocationServiceApiService
import lk.eclk.locationservice.data.remote.datasources.AuthenticationNetworkDataSource
import lk.eclk.locationservice.data.remote.datasources.AuthenticationNetworkDataSourceImpl
import lk.eclk.locationservice.data.remote.interceptors.AuthenticityInterceptor
import lk.eclk.locationservice.data.remote.interceptors.AuthenticityInterceptorImpl
import lk.eclk.locationservice.ui.home.HomeViewModelFactory
import lk.eclk.locationservice.ui.signin.SignInViewModelFactory
import lk.eclk.locationservice.ui.splash.SplashScreenViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class LocationServiceApplication : Application(), KodeinAware {


    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@LocationServiceApplication))

        // Dependency injection with kodein

        //database
        bind() from singleton { AppDatabase(instance()) }

        //providers
        bind<JWTProvider>() with singleton { JWTProviderImpl(instance()) }

        //interceptors
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind<AuthenticityInterceptor>() with singleton { AuthenticityInterceptorImpl() }

        // api services
        bind() from singleton { LocationServiceApiService(instance(), instance(),instance()) }

        //data sources - network
        bind<AuthenticationNetworkDataSource>() with singleton {
            AuthenticationNetworkDataSourceImpl(
                instance()
            )
        }

        //Repository
        bind<Repository>() with singleton {
            RepositoryImpl(
                instance(),
                instance()
            )
        }

        //view model factories
        bind() from provider { SplashScreenViewModelFactory(instance()) }
        bind() from provider { SignInViewModelFactory(instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }
    }
}