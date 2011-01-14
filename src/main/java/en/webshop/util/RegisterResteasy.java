package en.webshop.util;

import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

public final class RegisterResteasy {
	private static boolean registered = false;
	
	public static void register() {
		if (!registered) {
			RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
			registered = true;
		}
	}
	
	private RegisterResteasy() {
	}
}
