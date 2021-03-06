package com.github.aesteve.vertx.nubes.reflections.injectors.typed;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.net.SocketAddress;
import io.vertx.ext.web.RoutingContext;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.github.aesteve.vertx.nubes.Config;
import com.github.aesteve.vertx.nubes.context.PaginationContext;
import com.github.aesteve.vertx.nubes.marshallers.Payload;
import com.github.aesteve.vertx.nubes.reflections.injectors.typed.impl.EventBusParamInjector;
import com.github.aesteve.vertx.nubes.reflections.injectors.typed.impl.HttpVersionParamInjector;
import com.github.aesteve.vertx.nubes.reflections.injectors.typed.impl.PaginationContextParamInjector;
import com.github.aesteve.vertx.nubes.reflections.injectors.typed.impl.PayloadParamInjector;
import com.github.aesteve.vertx.nubes.reflections.injectors.typed.impl.RequestParamInjector;
import com.github.aesteve.vertx.nubes.reflections.injectors.typed.impl.ResourceBundleParamInjector;
import com.github.aesteve.vertx.nubes.reflections.injectors.typed.impl.ResponseParamInjector;
import com.github.aesteve.vertx.nubes.reflections.injectors.typed.impl.RoutingContextParamInjector;
import com.github.aesteve.vertx.nubes.reflections.injectors.typed.impl.SocketAddressParamInjector;
import com.github.aesteve.vertx.nubes.reflections.injectors.typed.impl.VertxParamInjector;

public class TypedParamInjectorRegistry {

	private Map<Class<?>, ParamInjector<?>> map;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TypedParamInjectorRegistry(Config config) {
		map = new HashMap<>();
		registerInjector(Vertx.class, new VertxParamInjector());
		registerInjector(RoutingContext.class, new RoutingContextParamInjector());
		registerInjector(Payload.class, new PayloadParamInjector());
		registerInjector(PaginationContext.class, new PaginationContextParamInjector());
		registerInjector(EventBus.class, new EventBusParamInjector());
		registerInjector(ResourceBundle.class, new ResourceBundleParamInjector(config));
		registerInjector(HttpServerRequest.class, new RequestParamInjector());
		registerInjector(HttpServerResponse.class, new ResponseParamInjector());
		registerInjector(SocketAddress.class, new SocketAddressParamInjector());
		registerInjector(HttpVersion.class, new HttpVersionParamInjector());
	}

	public <T> void registerInjector(Class<? extends T> clazz, ParamInjector<T> injector) {
		map.put(clazz, injector);
	}

	@SuppressWarnings("unchecked")
	public <T> ParamInjector<T> getInjector(Class<? extends T> clazz) {
		return (ParamInjector<T>) map.get(clazz);
	}
}
