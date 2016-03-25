package edu.hdu.lab.checkIn.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Constants {
    /**
     * 存放DataSource的Map,key为DataSource的名称，value为相应的DataSource实例。
     * 这个Map是给DatasourceRouter来使用的，用来实现动态多数据源。
     */
    public final static Map<Object, Object> cachedDatasourceMap = new ConcurrentHashMap<Object, Object>();

    /**
     * 初始化时用到的DataSource名称
     */
    public final static String DATASOURCE_BOOTSTRAP = "bootstrapDs";
    /**
     * 数据源URL中的占位字符串，使用时将该字符串替换为数据库名
     */
    public final static String DATASOURCE_TEMPLATE_URL_PLACEHOLDER = "MYDATABASE";

    public final static String DATE_FORMAT = "yyyy-MM-dd";
    public final static String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String TIME_DATE_FORMAT = "HH:mm";

    /**
     * 即时搜索时数据库字段的模糊查询所需的占位符
     */
    public final static String SEARCH_TOKEN = "%";

    /**
     * 操作返回值参数名
     */
    public final static String RESULT_CODE_STRING = "resultCode";

    /**
     * 未读消息的返回名
     */
    public final static String RESULT_NAME_UNREAD = "unread";

    /**
     * 未接收邮包的数量的返回名
     */
    public final static String RESULT_NAME_UNRECEIVED = "unreceived";

    /**
     * 小区数据库不存在的错误码
     */
    public final static int RESULT_CODE_PLACE_NOT_EXISTS = 999;

    /**
     * 日期格式不正确错误码
     */
    public final static int RESULT_CODE_DATEFORMAT_WRONG = 998;

    /**
     * 文件目录创建失败错误码
     */
    public final static int RESULT_CODE_DIR_CREATION_FAILED = 997;

    /**
     * 文件存储失败的错误码
     */
    public final static int RESULT_CODE_FILE_SAVE_FAILED = 996;

    /**
     * 找不到该用户的错误码
     */
    public final static int RESULT_CODE_USER_NOT_FOUND = 995;

    /**
     * 用户已存在的错误码
     */
    public final static int RESULT_CODE_USER_ALREADY_EXISTS = 994;

    /**
     * 物业通知已读的错误码
     */
    public final static int RESULT_CODE_NOTIFICATION_ALREADY_READ = 993;

    /**
     * 因权限不足，而无法进行特定操作的错误码
     */
    public final static int RESULT_CODE_INSUFFICIENT_PRIVILEGE = 992;

    /**
     * 非法的参数值错误码
     */
    public final static int RESULT_CODE_ILLEGAL_PARAM = 991;

    /**
     * 通知业务层查询参数，最早时间
     */
    public final static String NOTIF_PARAM_EARLIST_TIME = "earliest_time";

    /**
     * 通知查询参数，最近三天
     */
    public final static String NOTIF_QUERY_PARAM_WITHIN_THREE_DAYS = "1";

    /**
     * 通知查询参数，最近一周
     */
    public final static String NOTIF_QUERY_PARAM_WINTIN_ONE_WEEK = "2";

    /**
     * 通知查询参数，最近一月
     */
    public final static String NOTIF_QUERY_PARAM_WITHIN_ONE_MONTH = "3";

    /**
     * 查询参数，用户标识符
     */
    public final static String QUERY_PARAM_USERID = "userId";

    /**
     * 邮包查询参数，接收者
     */
    public final static String PARCEL_PARAM_RECEIVER = "receiver";

    /**
     * 邮包查询参数，时间
     */
    public final static String PARCEL_PARAM_TIME = "time";

    /**
     * 投诉建议查询参数，开始时间
     */
    public final static String SUGGESTION_PARAM_STARTTIME = "startTime";

    /**
     * 投诉建议查询参数，结束时间
     */
    public final static String SUGGESTION_PARAM_ENDTIME = "endTime";

    /**
     * 投诉建议查询参数，创建者
     */
    public final static String SUGGESTION_PARAM_CREATOR = "creator";

    /**
     * 最大上传的图片数量
     */
    public final static int MAX_UPLOADED_IMAGES = 3;

    /**
     * Map中图片列表的key
     */
    public final static String MAP_KEY_IMAGES_LIST = "imgsList";

    /**
     * Map中音频文件的key
     */
    public final static String MAP_KEY_VOICE_FILE = "voiceFile";

    /**
     * Map中的百度地图API访问应用ak的key
     */
    public final static String MAP_KEY_BAIDUMAP_KEY = "baiduMapKey";

    /**
     * Map中的百度地图API主机名
     */
    public final static String MAP_KEY_BAIDUMAP_HOST = "baiduMapHost";

    /**
     * Map中的百度地图API的传输协议
     */
    public final static String MAP_KEY_BAIDUMAP_SCHEME = "baiduMapScheme";

    /**
     * Map中的百度地图API的路径
     */
    public final static String MAP_KEY_BAIDUMAP_PATH = "baiduMapPath";

    /**
     * 报修查询参数，开始时间
     */
    public final static String REPAIR_PARAM_START_TIME = "startTime";

    /**
     * 报修查询参数，结束时间
     */
    public final static String REPAIR_PARAM_END_TIME = "endTime";

    /**
     * 报修已提交（未修复）标志
     */
    public final static int REPAIR_NOT_FIXED = 0;

    /**
     * 报修已完成（已修复）标志
     */
    public final static int REPAIR_FIXED = 1;

    /**
     * 报修正在处理中标志
     */
    public final static int REPAIR_IN_PROCESS = 2;

    /**
     * 提交报修单时物业自动回复的内容
     */
    public final static String COMMENT_ON_SUBMIT_REPAIR_HISTORY = " 您好，您的报修单已提交，我们会尽快为您处理，谢谢！";

    /**
     * 报修类型
     */
    public static enum REPAIR_TYPE {
        DRAIN(1), //给排水维修
        ELECTRICAL_EQUIPMENT(2), //室内电器
        DOORS_AND_WINDOWS(3), //门窗
        CLEANING(4), //保洁
        MISC(5); //其他

        private final int value; // 类型值

        private REPAIR_TYPE(int value) {
            this.value = value;
        }

        public int Value() {
            return this.value;
        }
    }

    /**
     * 投诉建议类型
     */
    public static enum SUGGESTION_TYPE {
        COMMUNITY_SERVICE(1), //物业服务
        ENVIRONMENT(2), //环境卫生
        ORDER(3), //小区秩序
        CHARGE_ISSUE(4), //收费问题
        PUBLIC_FIX(5), //公共报修
        MISC(6); //其他

        private final int value; //类型值

        private SUGGESTION_TYPE(int value) {
            this.value = value;
        }

        public int Value() {
            return this.value;
        }
    }


    /**
     * 投诉建议状态，已受理
     */
    public final static Integer SUGGESTION_STATUS_IN_PROCESS = 0;

    /**
     * 投诉建议状态，已完成
     */
    public final static Integer SUGGESSTION_STATUS_COMPLETED = 1;

    /**
     * 小区大家庭查询参数，类型
     */
    public final static String FAMILY_QUERY_PARAM_TYPE = "type";

    /**
     * 小区大家庭查询参数，开始时间
     */
    public final static String FAMILY_QUERY_PARAM_START_TIME = "startTime";

    /**
     * 小区大家庭查询参数，结束时间
     */
    public final static String FAMILY_QUERY_PARAM_END_TIME = "endTime";

    /**
     * 预订配送Service查询参数，类型
     */
    public final static String GROCERY_PARAM_TYPE = "type";

    /**
     * 预订配送Service查询参数，距离
     */
    public final static String GROCERY_PARAM_DISTANCE = "distance";

    /**
     * 预订配送Service查询参数，用户位置坐标
     */
    public final static String GROCERY_PARAM_USER_LOCATION = "myLocation";

    /**
     * 以字符串形式表示的坐标中，经度和维度之间的分隔符
     */
    public final static String LOCATION_SEPERATOR = ",";

    /**
     * 预订配送模块店铺类型，水果店
     */
    public final static String GROCERY_TYPE_FRUIT = "1";

    /**
     * 预订配送模块店铺类型，蔬菜店
     */
    public final static String GROCERY_TYPE_VEGETABLE = "2";

    /**
     * 预订配送模块店铺类型，三餐店
     */
    public final static String GROCERY_TYPE_CANTEEN = "3";

    /**
     * 生活导航查询参数，类型
     */
    public final static String LIFE_PARAM_TYPE = "type";

    /**
     * 生活导航查询参数，名称
     */
    public final static String LIFE_PARAM_NAME = "name";

    /**
     * 生活导航查询参数，距离
     */
    public final static String LIFE_PARAM_DISTANCE = "distance";

    /**
     * 生活导航查询参数，访问者的位置经纬度
     */
    public final static String LIFE_PARAM_MYLOCATION = "myLocation";

    /**
     * 生活导航模块类型，教育类
     */
    public final static String LIFE_TYPE_EDUCATION = "1";

    /**
     * 生活导航模块类型，休闲类
     */
    public final static String LIFE_TYPE_ENTERTAINMENT = "2";

    /**
     * 生活导航模块类型，家政类
     */
    public final static String LIFE_TYPE_HOUSEKEEPING = "3";

    /**
     * 生活导航模块类型，其它类
     */
    public final static String LIFE_TYPE_MISC = "4";

    /**
     * 用户角色，物业
     */
    public final static int USER_ROLE_ADMIN = 1;

    /**
     * 用户角色，预订配送商户
     */
    public final static int USER_ROLE_GROCERY = 2;

    /**
     * 用户角色，业主
     */
    public final static int USER_ROLE_RESIDENT = 3;

    /**
     * 用户角色，生活导航商户
     */
    public final static int USER_ROLE_LIFE = 4;

    /**
     * 用户角色，商户（主要用于前台登录判断用户身份的，属于虚拟角色）
     */
    public final static int USER_ROLE_BUSINESS = 5;

    /**
     * 用户角色， 维修工
     */
    public final static int USER_ROLE_WORKER = 6;
    /**
     * 用户角色，施工队添加信息
     */
    public final static int USER_ROLE_CONSTRUCTION = 8;

    /**
     * 话题类型，小区资讯
     */
    public final static int TOPIC_TYPE_ANNOUNCEMENT = 1;

    /**
     * 话题类型，业主论坛
     */
    public final static int TOPIC_TYPE_USERFORUM = 2;

    /**
     * 话题类型，跳蚤市场
     */
    public final static int TOPIC_TYPE_BAZAAR = 3;

    /**
     * 话题类型，活动约伴
     */
    public final static int TOPIC_TYPE_ACTIVITY = 4;

    /**
     * 广告类型，简单投放广告
     */
    public final static int AD_TYPE_TYPICAL = 1;

    /**
     * 广告类型，按次数收费广告
     */
    public final static int AD_TYPE_PAYWARE_BY_CLICK = 2;

    /**
     * 广告类型，启动屏广告
     */
    public final static int AD_TYPE_FIRST_START = 3;

    /**
     * 消息状态，已读
     */
    public final static int MESSAGE_STATUS_READ = 1;

    /**
     * 消息状态，未读
     */
    public final static int MESSAGE_STATUS_UNREAD = 2;

    /**
     * 消息类型，小区大家庭论坛回复消息
     */
    public final static int MESSAGE_TYPE_FAMILY_THREAD_NEW_COMMENT = 1;

    /**
     * 默认文本
     */
    public final static String DEFAULT_TEXTS = "无";

    /**
     * 默认的上传图片文件格式
     */
    public final static String UPLOADED_IMG_FILE_DEFAULT_POSTFIX = "png";

    /**
     * 默认的上传语音文件格式
     */
    public final static String UPLOADED_VOICE_FILE_DEFAULT_POSTFIX = "amr";

    /**
     * 默认的浏览器端语音文件格式
     */
    public final static String DEFAULT_WEBBROWSER_AUDIO_FILE_FORMAT = "wav";

    /**
     * 文件类型，未知
     */
    public final static int FILE_TYPE_UNKNOWN = 0;

    /**
     * 文件类型，语音
     */
    public final static int FILE_TYPE_AUDIO = 1;

    /**
     * 文件类型，图片
     */
    public final static int FILE_TYPE_IMAGE = 2;

    /**
     * 邮包状态，已签收
     */
    public final static int PARCEL_STATUS_RECEIVED = 1;

    /**
     * 邮包状态，未签收
     */
    public final static int PARCEL_STATUS_NOT_RECEIVED = 0;

    /**
     * 查询参数，页数
     */
    public final static String QUERY_PARAM_OFFSET = "offset";

    /**
     * 查询参数，页面大小
     */
    public final static String QUERY_PARAM_SIZE = "size";

    /**
     * 静态文件的存放目录
     */
    public final static String STATIC_FILES_PATH = "/home/justin/Shares/smart_community_static_files/";

    /**
     * 维修人员信息的正则表达式
     */
    public final static String REPAIR_WORKER_INFO_PATTERN = "维修人员：(.*)\n联系电话：(.*)";
}
