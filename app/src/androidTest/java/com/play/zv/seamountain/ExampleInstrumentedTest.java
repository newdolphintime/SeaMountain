package com.play.zv.seamountain;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.orhanobut.logger.Logger;
import com.play.zv.seamountain.db.JavbusDBOpenHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private JavbusDBOpenHelper javbusDBOpenHelper;

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();


        assertEquals("com.play.zv.seamountain", appContext.getPackageName());
        testPublishSubject();

    }
    public void testPublishSubject() {
        SQLiteDatabase db = javbusDBOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM movieinfo where num = ?", new String[]{"MIDE-065"});
        //存在数据才返回true
        if (cursor.moveToFirst()) {

            String name = cursor.getString(cursor.getColumnIndex("stars"));

            Logger.d(name);
        }
        cursor.close();

    }
}
