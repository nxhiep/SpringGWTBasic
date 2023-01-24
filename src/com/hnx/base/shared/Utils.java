package com.hnx.base.shared;

import java.util.List;

public class Utils {
	public static boolean isFileNameImage(String fileName){
		if(fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".png")
			|| fileName.toLowerCase().endsWith(".gif") || fileName.toLowerCase().endsWith(".jpeg")
			|| fileName.toLowerCase().endsWith(".svg")){
			return true;
		}
		return false;
	}
	
	public static boolean isFileNameDocument(String fileName){
		if(fileName.toLowerCase().endsWith(".pdf") || fileName.toLowerCase().endsWith(".docx") || fileName.toLowerCase().endsWith(".doc")
			|| fileName.toLowerCase().endsWith(".xlsx") || fileName.toLowerCase().endsWith(".xls")
			|| fileName.toLowerCase().endsWith(".ppt") || fileName.toLowerCase().endsWith(".pptx") 
			|| fileName.toLowerCase().endsWith(".ppsx")){
			return true;
		}
		return false;
	}
	
	public static boolean isFileNameSound(String fileName){
		if(fileName.toLowerCase().endsWith(".mp3") || fileName.toLowerCase().toLowerCase().contains("wav")){
			return true;
		}
		return false;
	}
	
	public static boolean isFileNameVideo(String fileName){
		fileName.toLowerCase();
		if(fileName.endsWith(".mp4") ){
			return true;
		}
		return false;
	}
	
	public static boolean isAudio(String contentType) {
		if(contentType == null) {
			return false;
		}
		return contentType.startsWith("audio");
	}
	
	public static boolean isVideo(String contentType) {
		if(contentType == null) {
			return false;
		}
		return contentType.startsWith("video");
	}

	public static boolean isDocument(String contentType) {
		if(contentType == null) {
			return false;
		}
		return contentType.startsWith("application") || contentType.startsWith("text");
	}
	
	public static boolean isImage(String contentType) {
		if(contentType == null) {
			return false;
		}
		return contentType.startsWith("image");
	}

	public static boolean equalsListLong(List<Long> array1, List<Long> array2) {
		if(array1.size() != array2.size()) {
			return false;
		}
		int count = 0;
		for (Long a : array2) {
			if(array2.contains(a)) {
				count++;
			}
		}
		return count == array2.size();
	}
}
