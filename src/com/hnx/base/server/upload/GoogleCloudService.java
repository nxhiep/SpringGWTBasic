package com.hnx.base.server.upload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.util.logging.Logger;

import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFileOptions.Builder;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;
import com.hnx.base.shared.Config;

public class GoogleCloudService {
	private static GcsService gcsService = null;
	public static final Logger log = Logger.getLogger(GoogleCloudService.class.getName());

	private static GoogleCloudService _instance = null;
	
	public static GoogleCloudService getInstance() {
		if(_instance == null) {
			_instance = new GoogleCloudService();
		}
		if (gcsService == null)
			gcsService = GcsServiceFactory.createGcsService(RetryParams.getDefaultInstance());
		return _instance;
	}
	
	public String createObject(String bucket, String name, String mimeType, String cacheControl, byte[] content) throws IOException {
		GcsFilename filename = new GcsFilename(bucket, name);
		return createObject(filename, content, mimeType, cacheControl);
	}

	public String createObject(GcsFilename fileName, byte[] content, String mimeType, String cacheControl) throws IOException {
		try {
			Builder builder = new GcsFileOptions.Builder();
			builder.mimeType(mimeType);
			if(cacheControl != null && !cacheControl.isEmpty()) {
				builder.cacheControl(cacheControl); // "public, max-age=1000"
			}
			GcsFileOptions options = builder.acl("public-read").build();
			GcsOutputChannel outputChannel = gcsService.createOrReplace(fileName, options);// GcsFileOptions.getDefaultInstance());
			outputChannel.write(ByteBuffer.wrap(content));
			outputChannel.close();
			System.out.println("Upload success");
			return Config.GOOGLE_CLOUD_STORAGE_URL + fileName.getBucketName() + "/" + fileName.getObjectName();
		} catch (Exception e) {
			log.warning("Upload fails: " + e.getMessage() + ":" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return Config.GOOGLE_CLOUD_STORAGE_URL + fileName.getBucketName() + "/" + fileName.getObjectName();
	}

	public String createObject(String bucket, String name, String mimeType, String cacheControl, InputStream inputStream) throws IOException {
		GcsFilename filename = new GcsFilename(bucket, name);
		return createObject(filename, mimeType, cacheControl, inputStream);
	}

	public String createObject(GcsFilename fileName, String mimeType, String cacheControl, InputStream inputStream) throws IOException {
		try {
			Builder builder = new GcsFileOptions.Builder();
			builder.mimeType(mimeType);
			if(cacheControl != null && !cacheControl.isEmpty()) {
				builder.cacheControl(cacheControl); // "public, max-age=1000"
			}
			GcsFileOptions options = builder.acl("public-read").build();
			GcsOutputChannel outputChannel = gcsService.createOrReplace(fileName, options);// GcsFileOptions.getDefaultInstance());
			copy(inputStream, Channels.newOutputStream(outputChannel));
			outputChannel.close();
			System.out.println("Upload success");
			return Config.GOOGLE_CLOUD_STORAGE_URL + fileName.getBucketName() + "/" + fileName.getObjectName();
		} catch (Exception e) {
			log.warning("Upload fails: " + e.getMessage() + ":" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return Config.GOOGLE_CLOUD_STORAGE_URL + fileName.getBucketName() + "/" + fileName.getObjectName();
	}

	public void copy(InputStream input, OutputStream output) throws IOException {
		try {
			byte[] buffer = new byte[Config.FILE_SIZE_LIMIT];
			int bytesRead = input.read(buffer);
			while (bytesRead != -1) {
				output.write(buffer, 0, bytesRead);
				bytesRead = input.read(buffer);
			}
		} finally {
			input.close();
			output.close();
		}
	}

	public void deleteObject(String bucket, String name) throws IOException {
		try {
			gcsService.delete(new GcsFilename(bucket, name));
			System.out.println("Delete success");
		} catch (Exception e) {
			log.warning("Upload fails: " + e.getMessage() + ":" + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}