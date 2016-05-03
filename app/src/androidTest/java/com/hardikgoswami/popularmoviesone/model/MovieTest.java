package com.hardikgoswami.popularmoviesone.model;

import android.os.Parcel;
import android.test.AndroidTestCase;


public class MovieTest extends AndroidTestCase {
    public static final String TEST_POSTER_PATH = "http://image.com/path.jpg";
    public static final String TEST_TITLE = "Iron man";
    private Movie mMovie;

    public void setUp() throws Exception {
        mMovie = new Movie(TEST_POSTER_PATH, TEST_TITLE);
    }

    public void testParcelable() {
        Parcel parcel = Parcel.obtain();
        mMovie.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        Movie parceledMovie = Movie.CREATOR.createFromParcel(parcel);
        assertEquals(mMovie, parceledMovie);
    }
}