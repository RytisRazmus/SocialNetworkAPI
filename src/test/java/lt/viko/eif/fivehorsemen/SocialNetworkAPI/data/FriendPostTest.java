package lt.viko.eif.fivehorsemen.SocialNetworkAPI.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class FriendPostTest {

    FriendPost friendPost;

    @BeforeEach
    void setUp() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = format.parse("2020-05-14");
        String dmy = format.format(myDate);

        friendPost = new FriendPost(myDate,"Laurynas","Zlatkus","1",
                "Maciau gera pana.","https://upload.wikimedia.org/wikipedia/commons/0/0c/Cow" +
                "_female_black_white.jpg","https://i.pinimg.com/474x/ff/5d/06/ff5d0624c089399d5736f8ce" +
                "0cc5ebeb.jpg");
    }

    @Test
    void getDate() {
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");

        String response = dmyFormat.format(friendPost.getDate());
        assertEquals("2020-05-14",response);
    }

    @Test
    void getProfileImage() {
    }

    @Test
    void getName() {
    }

    @Test
    void getSurname() {
    }
}