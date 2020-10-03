package com.example.androidbasicproject.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.example.androidbasicproject.database.UserDao;
import com.example.androidbasicproject.database.UserDatabase;
import com.example.androidbasicproject.database.UserEntity;

import java.util.Objects;

public class MyContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.androidbasicproject";
    private static final String BASE_PATH = UserEntity.TABLE_NAME;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
    private static final int CODE_USER = 1;
    private static final int CODE_USER_ID = 2;

    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, UserEntity.TABLE_NAME, CODE_USER);
        MATCHER.addURI(AUTHORITY, UserEntity.TABLE_NAME + "/*", CODE_USER_ID);
    }

    public MyContentProvider() {
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        final Cursor cursor;
        final Context context = getContext();
        if (context == null) {
            return null;
        }
        UserDao userDao = UserDatabase.getInstance(context).userDao();
        if (MATCHER.match(uri) == CODE_USER) {
            cursor = userDao.selectAll();
        } else {
            cursor = null;
        }
        Objects.requireNonNull(cursor).setNotificationUri(context.getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
