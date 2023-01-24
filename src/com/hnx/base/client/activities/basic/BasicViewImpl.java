package com.hnx.base.client.activities.basic;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.constants.ButtonType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.hnx.base.client.activities.basic.widgets.HeaderPanel;

public class BasicViewImpl extends Composite implements BasicView {

	private static BasicViewImplUiBinder uiBinder = GWT.create(BasicViewImplUiBinder.class);

	interface BasicViewImplUiBinder extends UiBinder<Widget, BasicLayout> {
	}
	
	protected EventBus eventBus;
	private final BasicLayout basicLayout;
	
	public static class BasicLayout {
		@UiField protected HTMLPanel mainPanel;
		@UiField protected HeaderPanel headerPanel;
		@UiField protected FlowPanel contentPanel;
		@UiField protected Button buttonHome, buttonProducts, buttonSettings, buttonProcess;
		private BasicViewImpl basicViewImpl;
		
		public BasicLayout() {
		}
		
		public HTMLPanel getMainPanel() {
			return mainPanel;
		}
		
		public Widget asWidget() {
			return basicViewImpl;
		}
		
		public HeaderPanel getHeaderPanel() {
			return headerPanel;
		}
		
		public FlowPanel getContentPanel() {
			return contentPanel;
		}
		
		public Button getButtonHome() {
			return buttonHome;
		}
		
		public Button getButtonProducts() {
			return buttonProducts;
		}
		
		public Button getButtonSettings() {
			return buttonSettings;
		}
		
		public Button getButtonProcess() {
			return buttonProcess;
		}
	}
	
	public BasicViewImpl() {
		this.basicLayout = new BasicLayout();
		initWidget(uiBinder.createAndBindUi(this.basicLayout));
	}
	
	protected void setView(Widget widget) {
		getBasicLayout().getContentPanel().clear();
		getBasicLayout().getContentPanel().add(widget);
	}
	
	protected void setActiveTab(Button button) {
		this.getBasicLayout().getButtonHome().setActive(false);
		this.getBasicLayout().getButtonHome().setType(ButtonType.DEFAULT);
		this.getBasicLayout().getButtonProducts().setActive(false);
		this.getBasicLayout().getButtonProducts().setType(ButtonType.DEFAULT);
		this.getBasicLayout().getButtonSettings().setActive(false);
		this.getBasicLayout().getButtonSettings().setType(ButtonType.DEFAULT);
		this.getBasicLayout().getButtonProcess().setActive(false);
		this.getBasicLayout().getButtonProcess().setType(ButtonType.DEFAULT);
		button.setActive(true);
		button.setType(ButtonType.PRIMARY);
	}
	
	@Override
	public BasicLayout getBasicLayout() {
		return basicLayout;
	}
	
	@Override
	public void refreshView() {
	}

	@Override
	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	@Override
	public EventBus getEventBus() {
		return eventBus;
	}
	
	@Override
	public HeaderPanel getHeaderPanel() {
		return basicLayout.getHeaderPanel();
	}
}
