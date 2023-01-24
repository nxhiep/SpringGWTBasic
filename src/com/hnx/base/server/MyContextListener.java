package com.hnx.base.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.googlecode.objectify.ObjectifyService;
import com.hnx.base.shared.model.ActionInfo;
import com.hnx.base.shared.model.ActivityInfo;
import com.hnx.base.shared.model.ClusterStructure;
import com.hnx.base.shared.model.Consumable;
import com.hnx.base.shared.model.ConsumableInput;
import com.hnx.base.shared.model.ConsumableOutput;
import com.hnx.base.shared.model.Product;
import com.hnx.base.shared.model.ProductMaterial;
import com.hnx.base.shared.model.RoadTextTure;
import com.hnx.base.shared.model.UserInfo;

public class MyContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ObjectifyService.register(ActionInfo.class);
		ObjectifyService.register(ActivityInfo.class);
		ObjectifyService.register(ClusterStructure.class);
		ObjectifyService.register(Consumable.class);
		ObjectifyService.register(ConsumableInput.class);
		ObjectifyService.register(ConsumableOutput.class);
		ObjectifyService.register(Product.class);
		ObjectifyService.register(ProductMaterial.class);
		ObjectifyService.register(ProductMaterial.class);
		ObjectifyService.register(RoadTextTure.class);
		ObjectifyService.register(UserInfo.class);
	}
}
