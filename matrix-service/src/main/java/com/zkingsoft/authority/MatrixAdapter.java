package com.zkingsoft.authority;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zkingsoft.exception.GlobleException;

@Component("authorityAdapter")
public class MatrixAdapter implements Authority {

	@Resource(name="matrix")
	private Authority authority;

	@Override
	public boolean isBtnPermitted(String matchStr) throws IllegalArgumentException {
		if (!authority.isBtnPermitted(matchStr)) {
			throw new GlobleException("没有操作权限");
		}
		return true;
	}

	@Override
	public boolean isFnPermitted(String fnCode) {
		if (!authority.isBtnPermitted(fnCode)) {
			throw new GlobleException("没有操作权限");
		}
		return true;
	}

	@Override
	public void getLoginOut() {
		authority.getLoginOut();
	}

	@Override
	public <T> T getLoginUser() {
		return authority.getLoginUser();
	}

	/**
	 * 多个权限,满足一个就可以视为拥有该操作权限
	 * 
	 * @param matchStrs
	 * @return
	 * @throws IllegalArgumentException
	 */
	public boolean isBtnPermitteds(String... matchStrs) throws IllegalArgumentException {
		boolean permintted = false;
		for (String matchStr : matchStrs) {
			if (authority.isBtnPermitted(matchStr)) {
				permintted = true;
			}
		}
		if (!permintted) {
			throw new GlobleException("没有操作权限");
		}
		return true;
	}

	/**
	 * 多个权限,满足一个就可以视为拥有该操作权限
	 * 
	 * @param matchStrs
	 * @return
	 * @throws IllegalArgumentException
	 */
	public boolean isFnPermitteds(String... fnCode) {
		boolean permintted = false;
		for (String matchStr : fnCode) {
			if (authority.isFnPermitted(matchStr)) {
				permintted = true;
			}
		}
		if (!permintted) {
			throw new GlobleException("没有操作权限");
		}
		return true;
	}

}
