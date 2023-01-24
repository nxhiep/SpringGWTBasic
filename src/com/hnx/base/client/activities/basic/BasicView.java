package com.hnx.base.client.activities.basic;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.IsWidget;
import com.hnx.base.client.activities.basic.BasicViewImpl.BasicLayout;
import com.hnx.base.client.activities.basic.widgets.HeaderPanel;

public interface BasicView extends IsWidget {

	void refreshView();

	BasicLayout getBasicLayout();

	void setEventBus(EventBus eventBus);

	HeaderPanel getHeaderPanel();

	EventBus getEventBus();

}
