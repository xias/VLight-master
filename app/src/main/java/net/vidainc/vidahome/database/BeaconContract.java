package net.vidainc.vidahome.database;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Aaron on 24/03/2015.
 */
public class BeaconContract {
    // The "Content authority" is a name for the entire content provider, similar to the
    // relationship between a domain name and its website.  A convenient string to use for the
    // content authority is the package name for the app, which is guaranteed to be unique on the
    // device.
    public static final String CONTENT_AUTHORITY = "com.ruanlopes.vidainc";

    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider.
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // Possible paths (appended to base content URI for possible URI's)
    public static final String PATH_ROOMS = "rooms";
    public static final String PATH_BEACONS = "beacons";

    public static final class RoomEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = "rooms";

        public static final String COLUMN_ROOM_NAME = "room_name";
        public static final String COLUMN_ROOM_X = "room_x";
        public static final String COLUMN_ROOM_Y = "room_y";
        public static final String COLUMN_ROOM_DRAWABLE_ID = "room_drawable_id";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ROOMS).build();
        public static final String CONTENT_TYPE =
                "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_ROOMS;

        public static Uri buildRoomUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class BeaconEntry implements BaseColumns{
        public static final String TABLE_NAME = "beacons";

        public static final String COLUMN_MAC_ADDRESS = "mac_address";
        public static final String COLUMN_BLUETOOTH_NAME = "bluetooth_name";
        public static final String COLUMN_LAST_KNOWN_DISTANCE = "last_distance";
        public static final String COLUMN_SERVICE_UUID = "service_uuid";
        public static final String COLUMN_LAST_KNOWN_RSSI = "rssi";
        public static final String COLUMN_PROXIMITY_UUID = "proximity_uuid";
        public static final String COLUMN_MAJOR_UUID = "major_uuid";
        public static final String COLUMN_MINOR_UUID = "minor_uuid";
        public static final String COLUMN_ROOM_KEY = "room";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_BEACONS).build();
        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_BEACONS;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_BEACONS;

        public static Uri buildBeaconUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildBeaconUriWithRoom(String roomName) {
            return CONTENT_URI.buildUpon().appendPath(roomName).build();
        }

        public static String getRoomNameFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }
}
