package com.example.phoenix.tvmazeexamplepresent;



import static org.mockito.Mockito.*;

import com.example.phoenix.tvmazeexamplepresent.models.film_detail.FilmDetails;
import com.example.phoenix.tvmazeexamplepresent.models.films_list.FilmsList;
import com.example.phoenix.tvmazeexamplepresent.server_conector.GetDataFromServer;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

//@RunWith(AndroidJUnit4.class)
public class ExampleUnitTest {

//    @Rule
//    ActivityTestRule<HomeActivity> homeActivityActivityTestRule= new ActivityTestRule<HomeActivity>(HomeActivity.class);

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getListFilms(){
        final Object syncObject = new Object();
        Callback<List<FilmsList>> listCallback= new Callback<List<FilmsList>>() {
            @Override
            public void onResponse(Call<List<FilmsList>> call, Response<List<FilmsList>> response) {
                if (response.isSuccessful()){
                    synchronized (syncObject){
                        syncObject.notify();
                    }
                } else {
                        syncObject.notify();
                    Assert.fail("Response is Not Success");
                }
            }
            @Override
            public void onFailure(Call<List<FilmsList>> call, Throwable t) {
                Assert.fail("onFailure");
            }
        };
        new GetDataFromServer().filmsListApi(listCallback);
        synchronized (syncObject){
            try {
                syncObject.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void getDetailsFilm(){
        final Object syncObject = new Object();
        Callback<FilmDetails> detailsCallback= new Callback<FilmDetails>() {
            @Override
            public void onResponse(Call<FilmDetails> call, Response<FilmDetails> response) {
                if (response.isSuccessful()){
                    synchronized (syncObject){
                        syncObject.notify();
                    }
                } else {
                    syncObject.notify();
                    Assert.fail("Response is Not Success for FilmDetails");
                }
            }
            @Override
            public void onFailure(Call<FilmDetails> call, Throwable t) {
                Assert.fail("onFailure FilmDetails");
            }
        };
        new GetDataFromServer().filmDetailApi(detailsCallback,1,"cast");
        synchronized (syncObject){
            try {
                syncObject.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}