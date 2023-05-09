package com.relayapp.live.data

object Constants {
    object HttpClient {
        const val CONNECT_TIMEOUT = 10L
        const val READ_TIMEOUT = 10L
        const val WRITE_TIMEOUT = 10L
        const val CONNECTION_TIME_OUT_MLS = CONNECT_TIMEOUT * 1000L
    }

    object DateFormat {
        const val EEEE_dd_MMMM = "EEEE',' dd MMMM"
        const val DEFAULT_FORMAT = "dd-mm-yyyy"
        const val HH_mm = "HH:mm"
    }

    object OpenWeather {
        const val WEATHER_ICON_URL = "https://openweathermap.org/img/wn/%s@4x.png"
        const val WEATHER_SMALL_ICON_URL = "https://openweathermap.org/img/wn/%s.png"

        // its not good idea to keep api key and base url like this we can make secure by putting inside
        // properties file get through BuildConfig variable
        // for This coding purpose only I am putting here
        const val YEK_IPA = "3cf14acd1832384b1f183935ac1d58cf"
        const val BASE_URL = "https://streaming-backend-api.herokuapp.com/"//"https://api.openweathermap.org/data/2.5/"
    }
}
