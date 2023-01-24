package com.hnx.base.shared;

public class Config {
	public static final String TEXT_EMPTY = "";
	public static final long LONG_NULL = -1;
	public static final int INT_NULL = -1;
	public static final long NULL_ID = -1l;
	
	public static final int STATUS_DELETED = -1;
	public static final int STATUS_PRIVATE = 0;
	public static final int STATUS_PUBLIC = 1;
	
	public static final int GENDER_OTHER = -1;
	public static final int GENDER_FE_MALE = 0;
	public static final int GENDER_MALE = 1;
	
	public static final int LOGIN_FAILED = -1;
	public static final int LOGIN_SUCCESS = 0;
	public static final int LOGIN_ACCOUNT_IS_USED = 1;
	public static final int LOGIN_ACCOUNT_NOT_EXIST = 2;
	public static final int LOGIN_WRONG_PASSWORD = 3;
	public static final int LOGIN_WRONG_PROVIDER = 4;
	public static final int LOGIN_ACCOUNT_NOT_ACTIVATED = 5;
	public static final int LOGIN_MOBILE_IS_USED = 6;
	
	public static final String DATE_FORMAT_FULL = "dd/MM/yyyy hh:mm:ss";
	public static final String DATE_FORMAT_DATE = "dd/MM/yyyy";

	public static final String ADMIN_PASSWORD = "abc@123";
	
	public static final int DEFAULT_EXPIRED_TIME = 30;

	public static final String BUCKET_NAME = "deploy-temp.appspot.com";
	public static final String BUCKET_NAME_FOLDER_IMAGE = "images";
	public static final String BUCKET_NAME_FOLDER_VIDEO = "videos";
	public static final String BUCKET_NAME_FOLDER_AUDIO = "audios";
	public static final String BUCKET_NAME_FOLDER_DOCUMENT = "documents";
	public static final String BUCKET_NAME_FOLDER_OTHER = "others";
	
	public static final String GOOGLE_CLOUD_STORAGE_URL = "https://storage.googleapis.com/";
	public static int FILE_SIZE_LIMIT = 10 * 1024 * 1024 * 1024; // 10GB
}
