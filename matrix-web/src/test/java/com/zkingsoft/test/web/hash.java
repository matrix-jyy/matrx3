package com.zkingsoft.test.web;

import org.junit.Test;

public class hash {

		@Test
		public void testHash(){
			
			System.out.println("193.23.12.11".hashCode());
			System.out.println(("193.23.12.11".hashCode())%10);

		}
}
