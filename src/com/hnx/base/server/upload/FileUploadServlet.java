package com.hnx.base.server.upload;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hnx.base.shared.Config;
import com.hnx.base.shared.Utils;

public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger("UploadServlet");

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			fileUpload.setSizeMax(Config.FILE_SIZE_LIMIT);

			String bucket = req.getParameter("bucket");
			
			List<FileItem> items = fileUpload.parseRequest(req);

			for (FileItem item : items) {
				if (item.isFormField()) {
					logger.warning("Received form field:");
					logger.warning("Name: " + item.getFieldName());
					logger.warning("Value: " + item.getString());
				} else {
					logger.warning("Received file:");
					logger.warning("Name: " + item.getName());
					logger.warning("Size: " + item.getSize());
					logger.warning("ContentType: " + item.getContentType());
					logger.warning("FieldName: " + item.getFieldName());
				}

				if (!item.isFormField()) {
					if (item.getSize() > Config.FILE_SIZE_LIMIT) {
						resp.sendError(HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE, "File size exceeds limit");
						return;
					}
					if(bucket == null || bucket.isEmpty()) {
						bucket = Config.BUCKET_NAME + "/" + Config.BUCKET_NAME_FOLDER_OTHER;
						if(Utils.isAudio(item.getContentType())) {
							bucket = Config.BUCKET_NAME + "/" + Config.BUCKET_NAME_FOLDER_AUDIO;
						}
						if(Utils.isDocument(item.getContentType())) {
							bucket = Config.BUCKET_NAME + "/" + Config.BUCKET_NAME_FOLDER_DOCUMENT;
						}
						if(Utils.isImage(item.getContentType())) {
							bucket = Config.BUCKET_NAME + "/" + Config.BUCKET_NAME_FOLDER_IMAGE;
						}
						if(Utils.isVideo(item.getContentType())) {
							bucket = Config.BUCKET_NAME + "/" + Config.BUCKET_NAME_FOLDER_VIDEO;
						}
					}
					logger.warning("bucket " + bucket);
					long time = new Date().getTime();
					String url = GoogleCloudService.getInstance().createObject(bucket, "file-" + time + "-" + item.getName(), 
							item.getContentType(), null, item.getInputStream());
					
					final PrintWriter writer = resp.getWriter();
					writer.write(url);
					if (!item.isInMemory())
						item.delete();
				}
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Throwing servlet exception for unhandled exception", e);
			throw new ServletException(e);
		}
	}

}