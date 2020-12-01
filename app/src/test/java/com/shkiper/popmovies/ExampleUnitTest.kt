package com.shkiper.popmovies

import com.shkiper.popmovies.retrofit.RetrofitBuilder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun getMovieTest(){

        GlobalScope.launch {
            val movie = RetrofitBuilder.apiService.findById("432342", "912a717e1b01d2516a9338ad19401e12", "ru")
            print(movie)
            assertEquals(null, movie)
        }


    }

}