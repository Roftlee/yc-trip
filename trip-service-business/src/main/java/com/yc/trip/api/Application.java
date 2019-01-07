package com.yc.trip.api;

import com.alibaba.dubbo.container.Main;

import java.io.IOException;

/**
 * 
 * @author 素闲人
 *
 */
public class Application {
	
	public static void main(String[] args) throws IOException {
		System.out.println("正在启动Dubbo Application!");
		Main.main(args);
	}

}
