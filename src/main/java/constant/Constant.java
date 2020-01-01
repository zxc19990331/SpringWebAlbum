package constant;

import java.util.HashMap;
import java.util.Map;

public class Constant {
    //至尊nb
    public static final String IMAGE_PATH = "E:\\study-软件工程\\web_album_rep";
    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String PASSWORD = "password";
    public static final String USER_STATE = "user_state";
    public static final String TYPE = "type";

    public static final String ALB_NAME = "album_name";
    public static final String ALB_ID = "id";
    public static final String ALB_UER_ID = "user_id";
    public static final String CREATE_TIME = "create_time";
    public static final String COVER_ID = "cover_id";
    public static final String DESCP = "descp";
    public static final String ALBUM_STATE = "album_state";
    public static final String PRAISE_COUNT = "praise_count";
    public static final String CATEGORY = "category";

    public static final String PHOTO_ID = "id";
    public static final String PHOTO_NAME = "name";
    public static final String URL = "url";

    public static final String MESSAGE_ID ="id";
    public static final String MESSAGE_TOID ="to_id";
    public static final String MESSAGE_FROMID ="from_id";
    public static final String MESSAGE_CONTEXT ="context";
    public static final String MESSAGE_CREATE_TIME ="create_time";
    public static final String MESSAGE_EXTRA ="extra";

    public static final String ORDER_DEFAULT = "order_default";
    public static final String ORDER_DATE = "order_date";
    public static final String ORDER_DATE_DESC = "order_default_desc";
    public static final String ORDER_DATE_ASC = "order_default_asc";

    public static final String BANNED = "banned";
    public static final String NORMAL = "normal";

    public static final String BAN_USR = "ban_user";
    public static final String DEBAN_USR = "deban_user";
    public static final String BAN_ALBUM = "ban_album";
    public static final String DEBAN_ALBUM = "deban_album";

    public static final String ID = "id";
    public static final String FROM_ID = "from_id";
    public static final String TO_ID = "to_id";
    public static final String OPERATE = "operate";
    public static final String NOTE = "note";

    public static final Map<String,String> OP_NAME_MAP = new HashMap<String,String>(){{
        put(BAN_USR,"封禁用户");
        put(BAN_ALBUM,"封禁相册");
        put(DEBAN_USR,"解禁用户");
        put(DEBAN_ALBUM,"解禁相册");
    }};

    public static final String COM_ID = "id";
    public static final String COM_CONTEXT = "context";
    public static final String COM_USERID = "user_id";
    public static final String COM_FROMID = "from_id";

    public static final String FOLLOW_FROMID = "from_id";
    public static final String FOLLOW_TOID = "to_id";


}
