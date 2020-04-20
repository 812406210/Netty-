/**
 * 
 */
package com.example.netty.lesson11.serviceSeparate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 请求模块
 * @author yangwj
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SocketModule {
	
	/**
	 * 请求的模块号
	 * @return
	 */
	short module();
}
