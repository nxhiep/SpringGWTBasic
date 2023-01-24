/**
 * 
 */
package com.hnx.base.client.view;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.ModalBody;
import org.gwtbootstrap3.client.ui.ModalFooter;
import org.gwtbootstrap3.client.ui.ModalHeader;
import org.gwtbootstrap3.client.ui.TextArea;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.ModalBackdrop;
import org.gwtbootstrap3.extras.datepicker.client.ui.DatePicker;
import org.gwtbootstrap3.extras.summernote.client.ui.Summernote;

import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.hnx.base.client.ClientUtils;

public class MyDialog extends Modal {

	protected VerticalPanel contentPanel = new VerticalPanel();
	protected Button okButton = new Button("OK");
	protected Button cancelButton = new Button("Cancel");
	protected HTML headerHTML = new HTML("");
	protected AsyncCallback<Void> callBack = null;
	protected ModalHeader header = new ModalHeader();
	protected ModalBody body = new ModalBody();
	protected ModalFooter footer = new ModalFooter();
	protected boolean editable = true;
	
	public MyDialog() {
		super();
		//this.getElement().getStyle().setZIndex(Config.ZINDEX_LEVEL1);
		this.getElement().getStyle().setProperty("clip", "auto !important");
		body.setWidth("100%");
		contentPanel.setWidth("100%");
		this.setClosable(true);
		this.setFade(true);
		okButton.setType(ButtonType.PRIMARY);
		cancelButton.setType(ButtonType.DANGER);
		this.add(header);
		this.add(body);
		this.add(footer);
		body.add(contentPanel);
		footer.add(okButton);
		footer.add(cancelButton);
		header.add(headerHTML);
		this.setDataBackdrop(ModalBackdrop.STATIC);
		okButton.setText("OK");
	    okButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (callBack != null) {
					callBack.onSuccess(null);
				}
				if (callBack == null) {
					hide();
				}
			}
		});
	    cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (callBack != null) {
					callBack.onFailure(null);
				}
				if (callBack == null) {
					hide();
				}
			}
		});
	}
	
	public MyDialog(String width) {
		this();
		this.setWidth(ClientUtils.isMobileWeb() ? "100%" : (width != null && !width.isEmpty() ? width : "80%"));
	}
	
	public void show(String title, Widget content, String okButton, String cancelButton, AsyncCallback<Void> callBack) {
		this.callBack = callBack;
		this.show(title, content, okButton, cancelButton);
	}
	
	public void showWithCenter(String title, Widget content, String okButton, String cancelButton, boolean center) {
		headerHTML.setHTML("<b>" + title + "</b>");
		if (content != null) {
			contentPanel.clear();
			contentPanel.add(content);
		}
		if (okButton != null && !okButton.isEmpty()) {
			this.okButton.setVisible(true);
			this.okButton.setText(okButton);
		}
		else {
			this.okButton.setVisible(false);
		}
		if (cancelButton != null && !cancelButton.isEmpty()) {
			this.cancelButton.setVisible(true);
			this.cancelButton.setText(cancelButton);
		}
		else {
			this.cancelButton.setVisible(false);
		}
		this.show();
	}
	
	
	public void show(String title, Widget content, String okButton, String cancelButton) {
		showWithCenter(title, content, okButton, cancelButton, true);
	}
	
	public VerticalPanel getMainPanel() {
		return this.contentPanel;
	}
	
	public Button getOkButton() {
		return this.okButton;
	}
	
	public Button getCancelButton() {
		return this.cancelButton;
	}
	
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	public void setContent(Widget content) {
		if (content != null) {
			contentPanel.clear();
			content.getElement().getStyle().setProperty("padding", "5px 10px 5px 5px");
			contentPanel.add(content);
			contentPanel.setCellHorizontalAlignment(content, HasHorizontalAlignment.ALIGN_CENTER);
		}
	}
	
	public ModalBody getModalBody() {
		return this.body;
	}
	
	public void setFixedHeight(int height) {
		this.getModalBody().getElement().getStyle().setOverflow(Overflow.AUTO);
		this.getModalBody().setHeight(height + "px");
	}
	
	public static void prompt(String title, String text, boolean isHtml, AsyncCallback<String> callback) {
		MyDialog dialog = new MyDialog();
		Widget editor = isHtml ? new Summernote() : new TextArea();
		if (editor instanceof Summernote) {
			((Summernote) editor).setCode(text);
		}
		else if (editor instanceof TextArea) {
			((TextArea)editor).setText(text);
		}
		dialog.show(title, editor, "OK", "Cancel", new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				dialog.hide();
				if (callback != null) {
					callback.onFailure(caught);
				}
			}

			@Override
			public void onSuccess(Void result) {
				dialog.hide();
				String content = editor instanceof Summernote ? ((Summernote)editor).getCode() : ((TextArea)editor).getText();
				if (callback != null) {
					callback.onSuccess(content);
				}
			}
		});
	}
	
	public static void selectDate(String title, Long value, AsyncCallback<Date> callback) {
		MyDialog dialog = new MyDialog();
		DatePicker datePicker = new DatePicker();
		if (value != null && value > 0) {
			datePicker.setValue(new Date(value));
		}
		dialog.show(title, datePicker, "OK", "Cancel", new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				dialog.hide();
				if (callback != null) {
					callback.onFailure(caught);
				}
			}

			@Override
			public void onSuccess(Void result) {
				dialog.hide();
				if (callback != null) {
					callback.onSuccess(datePicker.getValue());
				}
			}
		});
	}
	
	public ModalBody getBody() {
		return body;
	}
}